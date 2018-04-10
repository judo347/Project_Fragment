package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/** This class handles world generation. */
public class WorldGenerator {

    private static final int TILE_SIZE = 32;

    public static Sprite generateSpriteArray(String levelTileSheetLocation){

        //TODO: THIS WORKS, YOU HAVE TO go through the levelTextureArray and create sprites based on the TileType enum.
        //TODO: add them all to an arrayList<Sprite> and return that one to the class. He will drawn the array.

        TextureRegion[][] levelTextureArray = TextureRegion.split(new Texture(levelTileSheetLocation), 1, 1);

        int elementsWide = 40;
        int elementsHigh = 25;

        Sprite whut = new Sprite(levelTextureArray[24][0], levelTextureArray[0][0].getRegionX(),levelTextureArray[0][0].getRegionY(), levelTextureArray[0][0].getRegionWidth(), levelTextureArray[0][0].getRegionHeight());

        return whut;

        //Might need arrayList of sprites or other type
    }
}
