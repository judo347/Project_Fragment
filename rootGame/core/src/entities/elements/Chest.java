package entities.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ChestType;
import helpers.Entity;
import helpers.EntityType;

//TODO: Render method has to have a texture
public class Chest extends Entity {

    private ChestType chestType;


    public Chest(World world, float x, float y) {
        super(world, EntityType.CHEST, x, y);
        this.chestType = ChestType.NORMAL;
    }

    @Override
    public void update(float delta) {
        //TODO collision?
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getSprite(), x, y, getWidth(), getHeight());
    }

    @Override
    public void dispose() {
        System.out.println("Chest texture disposed");
        chestType.dispose();
    }

    /** Gets the sprite based on the state (open/closed). */
    public Sprite getSprite() {
        return chestType.getSprite();
    }

    /** Open the chest. TODO should be used in another way. */
    public void openChest(){
        chestType.openChest();
    }
}
