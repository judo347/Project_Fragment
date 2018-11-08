package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ResourceManager {

    public AssetManager assetManager;

    //UI
    public Skin skin;

        //BOX
    //public TextureAtlas boxAtlas;
    private TextureAtlas boxAtlas;
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

        //INVENTORY SLOT
    private TextureAtlas inventorySlotAtlas;
    public TextureRegion invSlotTopLeft;
    public TextureRegion invSlotTop;
    public TextureRegion invSlotTopRight;
    public TextureRegion invSlotRight;
    public TextureRegion invSlotDownRight;
    public TextureRegion invSlotDown;
    public TextureRegion invSlotDownLeft;
    public TextureRegion invSlotLeft;
    public TextureRegion invSlotMiddle;
    public Texture invSlot;
    public int inventorySlotSize;


    //ITEMS
        //Consumables //TODO Rewrite to use atlas when there is different images for sizes
    public Texture consumableHpSmall;
    public Texture consumableHpMedium;
    public Texture consumableHpLarge;
    public Texture consumableManaSmall;
    public Texture consumableManaMedium;
    public Texture consumableManaLarge;
    public Texture consumableExpSmall;
    public Texture consumableExpMedium;
    public Texture consumableExpLarge;

    //PLACEHOLDERS
    public Texture blackSquare16;

    public Texture texuture; //TODO textures

    public Sound dead, jump; //TODO sounds
    
    public BitmapFont bitmap; //TODDO bitmaps

    /** loads all necessary assets. */
    public ResourceManager(){

        assetManager = new AssetManager();
        //TODO json reader

        //AssetManager loading section
        assetManager.load("ui/img/box/box.atlas", TextureAtlas.class);
        assetManager.load("ui/img/inventorySlot/uiInventorySlot.atlas", TextureAtlas.class);
        //TODO more..

        assetManager.finishLoading();

        //Set fields
        setUiBox();
        setInventorySlots();
        setConsumables();
        setPlaceholders();
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
    }

    private void setInventorySlots(){
         inventorySlotAtlas = assetManager.get("ui/img/inventorySlot/uiInventorySlot.atlas");
         invSlotTopLeft = inventorySlotAtlas.findRegion("upleft");
         invSlotTop = inventorySlotAtlas.findRegion("up");
         invSlotTopRight = inventorySlotAtlas.findRegion("upright");
         invSlotRight = inventorySlotAtlas.findRegion("right");
         invSlotDownRight = inventorySlotAtlas.findRegion("downright");
         invSlotDown = inventorySlotAtlas.findRegion("down");
         invSlotDownLeft = inventorySlotAtlas.findRegion("downleft");
         invSlotLeft = inventorySlotAtlas.findRegion("left");
         invSlotMiddle = inventorySlotAtlas.findRegion("middle");
         inventorySlotSize = invSlotDown.getRegionHeight();
         invSlot = new Texture(Gdx.files.internal("ui/img/inventorySlot/inventorySlot.png"));
    }

    private void setConsumables(){
        consumableHpSmall = new Texture("img/items/consumables/flask_hp.png");
        consumableHpMedium = new Texture("img/items/consumables/flask_hp.png");
        consumableHpLarge = new Texture("img/items/consumables/flask_hp.png");
        consumableManaSmall = new Texture("img/items/consumables/flask_mana.png");
        consumableManaMedium = new Texture("img/items/consumables/flask_mana.png");
        consumableManaLarge = new Texture("img/items/consumables/flask_mana.png");
        consumableExpSmall = new Texture("img/items/consumables/flask_exp.png");
        consumableExpMedium = new Texture("img/items/consumables/flask_exp.png");
        consumableExpLarge = new Texture("img/items/consumables/flask_exp.png");
    }

    private void setPlaceholders(){
        blackSquare16 = new Texture("img/placeholder/blackSquare16.png");
    }

    /** Disposes all needed assets. */
    public void dispose(){
        boxAtlas.dispose();
        skin.dispose();
        assetManager.dispose();
        //TODO add
    }
}
