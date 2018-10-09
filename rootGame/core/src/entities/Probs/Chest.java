package entities.Probs;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ChestType;
import entities.Entity;
import helpers.EntityType;

//TODO: Render method has to have a texture
public class Chest extends Entity {

    private ChestType chestType;
    private boolean isChestOpen;


    public Chest(World world, Vector2 pos) {
        super(world, EntityType.CHEST, pos);
        this.chestType = ChestType.NORMAL;
        this.isChestOpen = false;

        body.getFixtureList().get(0).setSensor(true);
    }

    @Override
    public void update(float delta) {
        //TODO collision?
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getSprite(), getPos().x, getPos().y, getWidth(), getHeight());
    }

    @Override
    public void dispose() {
        chestType.dispose();
    }

    /** Gets the sprite based on the state (open/closed). */
    public Sprite getSprite() {
        return chestType.getSprite(isChestOpen);
    }

    /** Open the chest. TODO should be used in another way. */
    public void openChest(){
        this.isChestOpen = true;
    }
}
