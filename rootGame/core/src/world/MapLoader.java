package world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.elements.DirectionalTile;
import entities.elements.Tile;
import helpers.*;
import helpers.GameObjects.RenderableObject;

import java.util.ArrayList;

import static helpers.GameInfo.PPM;
import static helpers.GameInfo.TILE_SIZE;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class MapLoader {

    private World world;
    private GameMap gameMap;

    //TODO Do this differently
    private ArrayList<RenderableObject> entitiesList;
    private ArrayList<Tile> tilesList;

    /** Load an image of a level and creates to lists containing the entities and tiles.
     * @param levelImageLocation the location of the image to be loaded. TODO should be an enum or handled in another way.
     * @param world the world that contains the elements. */
    public void loadLevelImage(String levelImageLocation, World world, GameMap gameMap){

        this.world = world;
        this.gameMap = gameMap;

        entitiesList = new ArrayList<>();
        tilesList = new ArrayList<>();

        //Transforms the texture into a pixmap containing pixels.
        Texture tempLevelTexture = new Texture(levelImageLocation);
        TextureData tempData = tempLevelTexture.getTextureData();
        tempData.prepare();
        Pixmap tempPixmap = tempLevelTexture.getTextureData().consumePixmap();

        //Current position
        Vector2 currentTilePos;

        //Goes through all pixels, analyses and converts the colored pixel to a entity or tile.
        for(int y = 0; y < tempPixmap.getHeight(); y++){
            for(int x  = 0; x < tempPixmap.getWidth(); x++){

                //Calculate tiles position
                //currentTilePos = new Vector2(x * GameInfo.TILE_SIZE, (tempPixmap.getHeight() - y) * GameInfo.TILE_SIZE - GameInfo.TILE_SIZE);
                currentTilePos = new Vector2(x + (TILE_SIZE/2) / PPM, tempPixmap.getHeight() - (y + 1)+ (TILE_SIZE/2) / PPM);

                //Get a color
                Color color = new Color();
                Color.argb8888ToColor(color, tempPixmap.getPixel(x, y));

                //Check if one of the two types contain an enum matching the current color
                EntityType entityType = EntityType.getTypeFromColor(color);

                //Create an element if color was found
                if(entityType == EntityType.WHITE_SPACE){ //White is not an element we want to create

                    continue;

                }else if(entityType == EntityType.GROUND){ //Add entityType

                    tilesList.add(createTile(x, y, currentTilePos, tempPixmap, entityType, color));

                }else{ //Add entity

                    //Add entity
                    entitiesList.add(EntityType.getEntity(color, world, currentTilePos, gameMap)); //TODO null? HANDLE
                }
            }
        }

        //DISPOSE
        tempLevelTexture.dispose();
        tempData.disposePixmap();
        tempPixmap.dispose();
    }

    private Tile createTile(int x, int y, Vector2 currentTilePos, Pixmap tempPixmap, EntityType currentEntityType, Color color){

        if(x != 0 && x != tempPixmap.getWidth()-1){ //Border check? Todo?

            if(!currentEntityType.isDirectionalTile()){

                System.out.println(currentEntityType);
                return new Tile(world, currentTilePos, currentEntityType);

            }else{ //Directional tile

                //Get surrounding tiles
                Color previousColor = new Color();
                Color.argb8888ToColor(previousColor, tempPixmap.getPixel(x - 1, y));
                EntityType leftEntityType = EntityType.getTypeFromColor(previousColor);
                Color nextColor = new Color();
                Color.argb8888ToColor(nextColor, tempPixmap.getPixel(x + 1, y));
                EntityType rightEntityType = EntityType.getTypeFromColor(nextColor);
                Color aboveColor = new Color();
                Color.argb8888ToColor(aboveColor, tempPixmap.getPixel(x, y - 1));
                EntityType aboveEntityType = EntityType.getTypeFromColor(aboveColor);

                EntityType.TextureDirection direction = createDirectionalTile(currentEntityType, aboveEntityType, leftEntityType, rightEntityType);
                return new DirectionalTile(world, currentTilePos, currentEntityType, direction);
            }

            /*
            //Get color of pixels to the left and right
            Color previousColor = new Color();
            Color.argb8888ToColor(previousColor, tempPixmap.getPixel(x-1, y));
            Color nextColor = new Color();
            Color.argb8888ToColor(nextColor, tempPixmap.getPixel(x+1, y));
            Color aboveColor = new Color();
            Color.argb8888ToColor(aboveColor, tempPixmap.getPixel(x, y-1));


            //Convert the side colors into tiles
            EntityType previousTileType = EntityType.getTypeFromColor(previousColor);
            EntityType nextTileType = EntityType.getTypeFromColor(nextColor);
            EntityType aboveTileType = EntityType.getTypeFromColor(aboveColor);

            if(currentEntityType == EntityType.GROUND_GRASS_MIDDLE){ //Check for side grass TODO rename/remake method

                if(aboveTileType == EntityType.GROUND_GRASS_MIDDLE){ //

                    return new GroundTile(world, EntityType.GROUND_BRICK, currentTilePos);

                }else if(previousTileType == EntityType.WHITE_SPACE){ //The previous pixel is white space = left grass block
                    if(nextTileType == EntityType.GROUND_GRASS_MIDDLE);
                    return new GroundTile(world, EntityType.GROUND_GRASS_LEFT, currentTilePos);

                }else if(nextTileType == EntityType.WHITE_SPACE){ //The next pixel is white space = right grass block
                    if(previousTileType == EntityType.GROUND_GRASS_MIDDLE);
                    return new GroundTile(world, EntityType.GROUND_GRASS_RIGHT, currentTilePos);
                }else{
                    return new GroundTile(world, EntityType.getTypeFromColor(color),currentTilePos); //TODO null? HANDLE
                }

            }else
                return new GroundTile(world, EntityType.getTypeFromColor(color), currentTilePos); //TODO null? HANDLE
        */
        }else{
            return new DirectionalTile(world, currentTilePos, currentEntityType, EntityType.TextureDirection.MIDDLE);
        }
    }

    private EntityType.TextureDirection createDirectionalTile(EntityType currentEntityType, EntityType above, EntityType left, EntityType right) {

        //Surrounding checks
        boolean isTileAboveTheSame = currentEntityType == above;//Is the block above the same as this?
        boolean isTileLeftTheSame = currentEntityType == left;//Is the block to the left the same?
        boolean isTileRightTheSame = currentEntityType == right;//Is the block to the right the same?

        if (isTileAboveTheSame) { //Under block
            return EntityType.TextureDirection.UNDER;
        } else if (above == EntityType.WHITE_SPACE) { //Above free

            if (isTileLeftTheSame && isTileRightTheSame) { //Both sides free
                return EntityType.TextureDirection.MIDDLE;
            } else if(left == EntityType.WHITE_SPACE) { //Left is free
                if(right == EntityType.WHITE_SPACE) //Is right also free
                    return EntityType.TextureDirection.MIDDLE;
                else
                    return EntityType.TextureDirection.LEFT;
            } else if(right == EntityType.WHITE_SPACE) { //Right is free
                return EntityType.TextureDirection.RIGHT;
            } else {
                return EntityType.TextureDirection.MIDDLE;
            }
        } else {
            return EntityType.TextureDirection.MIDDLE;
        }
    }

    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }

    public ArrayList<RenderableObject> getEntitiesList() {
        return entitiesList;
    }
}
