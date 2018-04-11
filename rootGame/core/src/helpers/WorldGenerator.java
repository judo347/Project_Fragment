package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.nio.ByteBuffer;
import java.util.ArrayList;

//https://stackoverflow.com/questions/24034352/libgdx-change-color-of-texture-at-runtime

/** This class handles world generation. */
public class WorldGenerator {

    private static final int TILE_SIZE = 32;

    public static ArrayList<Sprite> generateSpriteArray(String levelTileSheetLocation){

        //TODO: THIS WORKS, YOU HAVE TO go through the levelTextureArray and create sprites based on the TileType enum.
        //TODO: add them all to an arrayList<Sprite> and return that one to the class. He will drawn the array.

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
                    tileType.getSprite().setPosition(x * TILE_SIZE, y * TILE_SIZE);
                    spriteArray.add(tileType.getSprite());
                }
            }
        }


        //Sprite whut = new Sprite(levelTextureArray[24][0], levelTextureArray[0][0].getRegionX(),levelTextureArray[0][0].getRegionY(), levelTextureArray[0][0].getRegionWidth(), levelTextureArray[0][0].getRegionHeight());

        return spriteArray;

        //Might need arrayList of sprites or other type
    }
}
