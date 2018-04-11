package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import sprites.elements.GroundTile;

import java.util.ArrayList;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class WorldGenerator {

    private static final int TILE_SIZE = 32;

    public static ArrayList<Sprite> generateSpriteArray(String levelTileSheetLocation, World world){

        //TODO THE SPRITES HAS TO BE OBJECT AND CONTAINING TILESHEETS.
        //TODO SO THE WALL WILL CHECK IF THERE IS WHITESPACE TO THE SIDE, AND CHANGE TEXUTERE BASED ON THAT.

        ArrayList<Sprite> spriteArray = new ArrayList<>();


        Texture tempTexture = new Texture(levelTileSheetLocation);

        TextureRegion[][] levelTextureArray = TextureRegion.split(tempTexture, 1, 1);

        int elementsWide = 40;
        int elementsHigh = 25;

        TextureData tempData = tempTexture.getTextureData();
        tempData.prepare();
        Pixmap tempPixmap = tempTexture.getTextureData().consumePixmap();

        for(int y = 0; y < tempPixmap.getHeight(); y++){
            for(int x  = 0; x < tempPixmap.getWidth(); x++){

                Color color = new Color();
                Color.argb8888ToColor(color, tempPixmap.getPixel(x, y));

                TileType tileType = TileType.getTypeFromColor(color);

                if(tileType != tileType.WHITE_SPACE){
                    Sprite tempSprite = tileType.getSprite();
                    //Sprite tempSprite = getSpriteFromType(tileType, x, y, world);
                    tempSprite.setPosition(x * TILE_SIZE, (tempPixmap.getHeight() - y) * TILE_SIZE - TILE_SIZE);
                    spriteArray.add(tempSprite);
                }
            }
        }

        return spriteArray;
    }

    //TODO: Could be changed to return an object based on an abstract?
    private static Sprite getSpriteFromType(TileType type, float x, float y, World world){
        if(type == TileType.GROUND_BRICK){
            return new GroundTile(world, x, y);
        }

        return type.getSprite();
    }

}
