package entities.Probs;

import Items.Consumable.ConsumableType;
import Items.Consumable;
import Items.Item;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import entities.Charactors.Player;
import helpers.ChestType;
import entities.Entity;
import helpers.EntityType;

import java.util.ArrayList;
import java.util.Random;

//TODO: Render method has to have a texture
public class Chest extends Entity {

    private ChestType chestType;
    private boolean isChestOpen;

    private ArrayList<Item> inventory;

    public Chest(World world, Vector2 pos) {
        super(world, EntityType.CHEST, pos);
        this.chestType = ChestType.NORMAL;
        this.isChestOpen = false;
        this.inventory = new ArrayList<>();

        fillChest(); //TODO Should not be called from here, or at least not without arguments.

        body.getFixtureList().get(0).setSensor(true);
    }

    @Override
    public void update(float delta) {
        //TODO collision?
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        batch.draw(getSprite(), getPos().x, getPos().y, getWidth(), getHeight());
        if(isChestOpen){
            for(Item item : inventory)
                item.render(batch, delta);
        }
    }

    @Override
    public void dispose() {
        chestType.dispose();
    }

    /** Method for filling the chest with loot. Should propperly be handled by mapLoader or other class.
     *  And should take either items as parameter or something else. */
    private void fillChest(){

        //TODO VERY TEMP
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            int tempInt = random.nextInt(3);
            ConsumableType consumableType;
            if(tempInt == 0)
                consumableType = ConsumableType.HEALTH_POTION;
            else if(tempInt == 1)
                consumableType = ConsumableType.MANA_POTION;
            else
                consumableType = ConsumableType.EXP_POTION;
            this.inventory.add(new Consumable(world, pos, consumableType, 2));
        }
    }

    /** Gets the sprite based on the state (open/closed). */
    public Sprite getSprite() {
        return chestType.getSprite(isChestOpen);
    }

    /** Open the chest. TODO should be used in another way. */
    public void openChest(){
        this.isChestOpen = true;
        for(Item item : inventory){
            item.drop(pos);
        }
    }

    public void removeItem(Item item){
        inventory.remove(item);
    }
}
