package world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import helpers.*;
import entities.elements.Chest;
import entities.elements.GroundTile;

import java.util.ArrayList;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class MapLoader {

    //TODO Do this differently
    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;

    /** @param levelImageLocation TODO should be an enum TODO TODO*/
    public void loadLevelImage(String levelImageLocation, ArrayList<Entity> entitiesList, ArrayList<GroundTile> tilesList, World world){

        Texture tempLevelTexture = new Texture(levelImageLocation);
        TextureRegion[][] levelTextureArray = TextureRegion.split(tempLevelTexture, 1, 1);

        //TODO HAS TO BE CALCULATED FROM TEXTURE
        int elementsWide = 40;
        int elementsHigh = 25;
        System.out.println(tempLevelTexture.getWidth() + " " + tempLevelTexture.getHeight());

        //TODO COMMENTS!!
        TextureData tempData = tempLevelTexture.getTextureData();
        tempData.prepare();
        Pixmap tempPixmap = tempLevelTexture.getTextureData().consumePixmap();

        for(int y = 0; y < tempPixmap.getHeight(); y++){
            for(int x  = 0; x < tempPixmap.getWidth(); x++){

                Color color = new Color();
                Color.argb8888ToColor(color, tempPixmap.getPixel(x, y));

                System.out.println(color.toString());


                TileType tileType = TileType.getTypeFromColor(color);
                EntityType entityType = EntityType.getTypeFromColor(color);

                if(tileType == TileType.WHITE_SPACE){
                    continue;
                }else if(tileType != null){
                    tilesList.add(new GroundTile(world, TileType.getTypeFromColor(color), x * GameInfo.TILE_SIZE, (tempPixmap.getHeight() - y) * GameInfo.TILE_SIZE - GameInfo.TILE_SIZE)); //TODO null? HANDLE
                    System.out.println("Added Ground tile!");
                }else if(entityType != null){
                    //TODO DOES NOT WORK
                    //entitiesList.add(EntityType.getTypeFromColor(color).getEntity(color, world, x, y)); //TODO null? HANDLE
                    entitiesList.add(EntityType.getEntity(color, world, x, y)); //TODO null? HANDLE
                    System.out.println("Added Entity!");
                }else{
                    System.out.println("No entity matched the color: " + color.toString()); //TODO Exception?
                }

                /*
                Enum<?> pixelType = enumCheck(color);

                if(pixelType.equals(TileType.class)){ //TODO DOES THIS WORK?

                    tilesList.add(new GroundTile(world, TileType.getTypeFromColor(color), x, y)); //TODO null? HANDLE
                    System.out.println("Added Ground tile!");

                }else if(pixelType.equals(EntityType.class)) {//TODO DOES THIS WORK?

                    entitiesList.add(EntityType.getTypeFromColor(color).getEntity(color, world, x, y)); //TODO null? HANDLE
                    System.out.println("Added Entity!");

                }else
                    System.out.println("No entity matched the color: " + color.toString()); //TODO Exception?
                */
            }
        }

        this.entitiesList = entitiesList;
        this.tilesList = tilesList;

    }

    private static Enum<?> enumCheck(Color color){
        if(TileType.getTypeFromColor(color) != null){
            System.out.println("TileType"); //TODO TEMP
            return TileType.getTypeFromColor(color);
        }
        else if(EntityType.getTypeFromColor(color) != null){
            System.out.println("TileType"); //TODO TEMP
            return EntityType.getTypeFromColor(color);
        }
        else
            throw new IllegalArgumentException(); //TODO make custom? Handle!
    }

    public ArrayList<Entity> getEntitiesList() {
        return entitiesList;
    }

    public ArrayList<GroundTile> getTilesList() {
        return tilesList;
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

}
