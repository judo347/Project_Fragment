package world;

import entities.Entity;
import ui.UiManager;
import utilities.GameInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.Probs.Chest;
import helpers.*;
import entities.GroundTile;

import java.util.ArrayList;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class MapLoader {

    private World world;
    private GameMap gameMap;

    //TODO Do this differently
    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;

    private int chestCount = 0;

    /** Load an image of a level and creates to lists containing the entities and tiles.
     * @param levelImageLocation the location of the image to be loaded. TODO should be an enum or handled in another way.
     * @param world the world that contains the elements. */
    public void loadLevelImage(String levelImageLocation, World world, GameMap gameMap, UiManager uiManager){

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
        Vector2 currentPixelPos;

        //Goes through all pixels, analyses and converts the colored pixel to a entity or tile.
        for(int y = 0; y < tempPixmap.getHeight(); y++){
            for(int x  = 0; x < tempPixmap.getWidth(); x++){

                //Calculate tiles position
                currentPixelPos = new Vector2(x * GameInfo.TILE_SIZE, (tempPixmap.getHeight() - y) * GameInfo.TILE_SIZE - GameInfo.TILE_SIZE);

                //Get a color
                Color color = new Color();
                Color.argb8888ToColor(color, tempPixmap.getPixel(x, y));

                //Check if one of the two types contain an enum matching the current color
                TileType tileType = TileType.getTypeFromColor(color);
                EntityType entityType = EntityType.getTypeFromColor(color);

                //Create an element if color was found
                if(tileType == TileType.WHITE_SPACE){ //White is not an element we want to create

                    continue;

                }else if(tileType != null){ //Add tileType

                    tilesList.add(createTile(x, y, currentPixelPos, tempPixmap, tileType, color));

                }else if(entityType != null){ //Add entity

                    //Add entity
                    entitiesList.add(EntityType.getEntity(color, world, currentPixelPos, gameMap, uiManager)); //TODO null? HANDLE

                    //Add an unique id to chests
                    if(entitiesList.get(entitiesList.size()-1) instanceof Chest){
                        entitiesList.get(entitiesList.size()-1).setId(entityType.getId() + chestCount++);
                    }
                }else{
                    System.out.println("No entity matched the color: " + color.toString()); //TODO Exception? //This is where non registret colors go.
                }
            }
        }

        //DISPOSE
        tempLevelTexture.dispose();
        tempData.disposePixmap();
        tempPixmap.dispose();
    }

    private GroundTile createTile(int x, int y, Vector2 currentPixelPos, Pixmap tempPixmap, TileType tileType, Color color){

        if(x != 0 && x != tempPixmap.getWidth()-1){

            //Get color of pixels to the left and right
            Color previousColor = new Color();
            Color.argb8888ToColor(previousColor, tempPixmap.getPixel(x-1, y));
            Color nextColor = new Color();
            Color.argb8888ToColor(nextColor, tempPixmap.getPixel(x+1, y));

            //Convert the side colors into tiles
            TileType previousTileType = TileType.getTypeFromColor(previousColor);
            TileType nextTileType = TileType.getTypeFromColor(nextColor);

            if(tileType == TileType.GROUND_GRASS_MIDDLE){ //Check for side grass TODO rename/remake method

                if(previousTileType == TileType.WHITE_SPACE){ //The previous pixel is white space = left grass block
                    if(nextTileType == TileType.GROUND_GRASS_MIDDLE);
                    return new GroundTile(world, TileType.GROUND_GRASS_LEFT, currentPixelPos, true);

                }else if(nextTileType == TileType.WHITE_SPACE){ //The next pixel is white space = right grass block
                    if(previousTileType == TileType.GROUND_GRASS_MIDDLE);
                    return new GroundTile(world, TileType.GROUND_GRASS_RIGHT, currentPixelPos, true);
                }else{
                    return new GroundTile(world, TileType.getTypeFromColor(color),currentPixelPos, true); //TODO null? HANDLE
                }

            }else
                return new GroundTile(world, TileType.getTypeFromColor(color), currentPixelPos, false); //TODO null? HANDLE

        }else{
            return new GroundTile(world, TileType.getTypeFromColor(color), currentPixelPos, true); //TODO null? HANDLE
        }
    }

    public ArrayList<GroundTile> getTilesList() {
        return tilesList;
    }

    public ArrayList<Entity> getEntitiesList() {
        return entitiesList;
    }
}
