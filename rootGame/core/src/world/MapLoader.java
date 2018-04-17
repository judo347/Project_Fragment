package world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.physics.box2d.World;
import helpers.*;
import entities.elements.GroundTile;

import java.util.ArrayList;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class MapLoader {

    //TODO Do this differently
    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;

    private int chestCount = 0;

    /** Load an image of a level and creates to lists containing the entities and tiles.
     * @param levelImageLocation the location of the image to be loaded. TODO should be an enum or handled in another way.
     * @param world the world that contains the elements. */
    public void loadLevelImage(String levelImageLocation, World world){

        entitiesList = new ArrayList<>();
        tilesList = new ArrayList<>();

        //Transforms the texture into a pixmap containing pixels.
        Texture tempLevelTexture = new Texture(levelImageLocation);
        TextureData tempData = tempLevelTexture.getTextureData();
        tempData.prepare();
        Pixmap tempPixmap = tempLevelTexture.getTextureData().consumePixmap();

        //Goes through all pixels, analyses and converts the colored pixel to a entity or tile.
        for(int y = 0; y < tempPixmap.getHeight(); y++){
            for(int x  = 0; x < tempPixmap.getWidth(); x++){

                //Get a color
                Color color = new Color();
                Color.argb8888ToColor(color, tempPixmap.getPixel(x, y));

                //Check if one of the two types contain an enum matching the current color
                TileType tileType = TileType.getTypeFromColor(color);
                EntityType entityType = EntityType.getTypeFromColor(color);

                //Create an element if color was found
                if(tileType == TileType.WHITE_SPACE){ //White is not an element we want to create
                    continue;
                }else if(tileType != null){

                    //Add tile
                    tilesList.add(new GroundTile(world, TileType.getTypeFromColor(color), x * GameInfo.TILE_SIZE, (tempPixmap.getHeight() - y) * GameInfo.TILE_SIZE - GameInfo.TILE_SIZE)); //TODO null? HANDLE

                }else if(entityType != null){

                    //Add entity
                    entitiesList.add(EntityType.getEntity(color, world, x * GameInfo.TILE_SIZE, (tempPixmap.getHeight() - y) * GameInfo.TILE_SIZE - GameInfo.TILE_SIZE)); //TODO null? HANDLE

                    //Add an unique id to chests
                    if(entitiesList.get(entitiesList.size()-1).getEntityType() == EntityType.CHEST){
                        entitiesList.get(entitiesList.size()-1).setId(entityType.getId() + chestCount++);
                    }
                }else{
                    System.out.println("No entity matched the color: " + color.toString()); //TODO Exception?
                }
            }
        }

        //DISPOSE
        tempLevelTexture.dispose();
        tempData.disposePixmap();
        tempPixmap.dispose();
    }

    public ArrayList<GroundTile> getTilesList() {
        return tilesList;
    }

    public ArrayList<Entity> getEntitiesList() {
        return entitiesList;
    }






    /* OLD
    //TODO: Could be changed to return an object based on an abstract?
    private static Sprite getSpriteFromType(TileType type, float x, float y, World world){
        if(type == TileType.GROUND_BRICK)
            return new GroundTile(world, TileType.GROUND_BRICK, x, y);

        if(type == TileType.GROUND_GRASS_MIDDLE)
            return new GroundTile(world, TileType.GROUND_GRASS_MIDDLE, x, y);

        if(type == TileType.CHEST)
            return new Chest(world, ChestType.NORMAL, x, y).getSprite();

        return type.getSprite(); //TODO: NEVER GETS ITS COORDS SET
    }*/
    /* Not used
    private static Enum<?> enumCheck(Color color){
        if(TileType.getTypeFromColor(color) != null){
            return TileType.getTypeFromColor(color);
        }
        else if(EntityType.getTypeFromColor(color) != null){
            return EntityType.getTypeFromColor(color);
        }
        else
            throw new IllegalArgumentException(); //TODO make custom? Handle!
    }*/
}
