package entities.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.ChestType;
import helpers.EntityType;
import helpers.GameObjects.GameObject;
import helpers.GameObjects.TextureObject;

public class Chest2 extends TextureObject{

    //private EntityType entityType = EntityType.CHEST;
    private ChestType chestType;
    private boolean isChestOpen;

    public Chest2(World world, Vector2 pos, ChestType chestType) {
        super(world, pos, GameObject.DEFAULT_STATIC_BODYDEF, GameObject.DEFAULT_STATIC_FIXTUREDEF, chestType.getSprite(false));
        this.chestType = chestType;
        this.isChestOpen = false;
    }

    //TODO OVERWRITE RENDER METHOD TO GETSPRITE STATE

    /** Gets the sprite based on the state (open/closed). */
    public Sprite getSprite() {
        return chestType.getSprite(isChestOpen);
    }

    /** Open the chest. TODO should be used in another way. */
    public void openChest(){
        this.isChestOpen = true;
    }

    public ChestType getChestType() {
        return chestType;
    }

    public boolean isChestOpen() {
        return isChestOpen;
    }
}
