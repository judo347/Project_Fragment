package entities.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ChestType;
import helpers.Entity;
import helpers.EntityType;

//TODO: Render method has to have a texture
//TODO Draw method that does all. Like texture, x and y.
public class Chest extends Entity {

    private ChestType chestType;
    private boolean isChestOpen;

    public Chest(World world, float x, float y) {
        super(world, EntityType.CHEST, x, y);
        this.chestType = ChestType.NORMAL;
        this.isChestOpen = false;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getSprite(), x, y, getWidth(), getHeight());
    }

    /** Gets the sprite based on the state (open/closed). */
    public Sprite getSprite() {
        return isChestOpen ? chestType.getSpriteOpen() : chestType.getSpriteClosed();
    }

    /** Open the chest*/
    public void openChest(){
        this.isChestOpen = true;
    }
}
