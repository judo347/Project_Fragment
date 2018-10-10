package Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.ItemType;

public class Consumanble extends Item {

    public enum ConsumableType{

        HEALTH_POTION(""), //TODO
        MANA_POTION(""), //TODO
        EXP_POTION(""); //TODO

        private String texturePath;

        ConsumableType(String texturePath) {
            this.texturePath = texturePath;
        }
    }

    private ConsumableType consumableType;

    public Consumanble(World world, Vector2 pos, ConsumableType consumableType, int sizeOfPotion) { //TODO implement sizeOfPotion
        super(world, pos, ItemType.CONSUMABLE, consumableType.texturePath);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {

    }
}
