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
import world.GameMap;

import java.util.ArrayList;
import java.util.Random;

//TODO: Render method has to have a texture
public class Chest extends Entity {

    private ChestType chestType;
    private boolean isChestOpen;

    private GameMap gameMap;

    private ArrayList<Item> inventory;

    public Chest(World world, Vector2 pos, GameMap gameMap) {
        super(world, EntityType.CHEST, pos);
        this.gameMap = gameMap;
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
        if(isChestOpen){ //TODO REMOVE
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

    /** Opens the chest. All items are dropped and added to the gameMaps droppedItems
     * list (and removed from this chests inventory. */
    public void openChest(){
        this.isChestOpen = true;

        gameMap.addAllDroppedItems(inventory);
        this.inventory = new ArrayList<>();
    }

    /** Removes the given item from the chests inventory. */
    public void removeItem(Item item){
        inventory.remove(item);
    }
}
