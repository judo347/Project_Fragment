package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class ResourceManager {

    public AssetManager assetManager;

    //UI
    public Skin skin;

        //BOX
    //public TextureAtlas boxAtlas;
    public TextureAtlas boxAtlas;
    public TextureRegion boxTopLeft;
    public TextureRegion boxTop;
    public TextureRegion boxTopRight;
    public TextureRegion boxRight;
    public TextureRegion boxDownRight;
    public TextureRegion boxDown;
    public TextureRegion boxDownLeft;
    public TextureRegion boxLeft;
    public TextureRegion boxMiddle;
    public int boxSize;

    public Texture texuture; //TODO textures

    public Sound dead, jump; //TODO sounds
    
    public BitmapFont bitmap; //TODDO bitmaps

    /** loads all necessary assets. */
    public ResourceManager(){

        assetManager = new AssetManager();
        //TODO json reader

        //AssetManager loading section
        assetManager.load("ui/img/box/box.atlas", TextureAtlas.class);
        //TODO more..

        assetManager.finishLoading();

        //Set fields
        setUiBox();
        skin = new Skin(Gdx.files.internal("ui/skin/uiskin.json"));
        //TODO more..
    }

    private void setUiBox(){
        boxAtlas = assetManager.get("ui/img/box/box.atlas");
        boxTopLeft = boxAtlas.findRegion("upleft");
        boxTop = boxAtlas.findRegion("up");
        boxTopRight = boxAtlas.findRegion("upright");
        boxRight = boxAtlas.findRegion("right");
        boxDownRight = boxAtlas.findRegion("downright");
        boxDown = boxAtlas.findRegion("down");
        boxDownLeft = boxAtlas.findRegion("downleft");
        boxLeft = boxAtlas.findRegion("left");
        boxMiddle = boxAtlas.findRegion("middle");
        boxSize = boxDown.getRegionHeight();
        System.out.println(boxSize);
    }



    /** Disposes all needed assets. */
    public void dispose(){
        assetManager.dispose();
        boxAtlas.dispose();
        skin.dispose();
        //TODO add
    }
}
