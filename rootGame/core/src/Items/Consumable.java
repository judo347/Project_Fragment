package Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.ItemType;

public class Consumable extends Item {

    public enum ConsumableType{

        //TODO Unique textures
        HEALTH_POTION("img/items/consumables/flask_hp.png", "img/items/consumables/flask_hp.png", "img/items/consumables/flask_hp.png"),
        MANA_POTION("img/items/consumables/flask_mana.png", "img/items/consumables/flask_mana.png","img/items/consumables/flask_mana.png"),
        EXP_POTION("img/items/consumables/flask_exp.png", "img/items/consumables/flask_exp.png", "img/items/consumables/flask_exp.png");

        private Texture smallTexture;
        private Texture mediumTexture;
        private Texture largeTexture;

        ConsumableType(String texturePathSmall, String texturePathMedium, String texturePathLarge) {
            this.smallTexture = new Texture(texturePathSmall);
            this.mediumTexture = new Texture(texturePathMedium);
            this.largeTexture = new Texture(texturePathLarge);
        }

        public Texture getTexture(int sizeOfPotion) {

            if(sizeOfPotion > 0 && sizeOfPotion < 4){
                switch (sizeOfPotion){
                    case 1 : return smallTexture;
                    case 2 : return mediumTexture;
                    case 3 : return largeTexture;
                }
            }

            return null;
        }
    }

    private ConsumableType consumableType;
    private final int potionSize;

    public Consumable(World world, Vector2 pos, ConsumableType consumableType, int sizeOfPotion) { //TODO implement sizeOfPotion
        super(world, pos, ItemType.CONSUMABLE, consumableType.getTexture(sizeOfPotion));
        this.potionSize = sizeOfPotion;
        this.consumableType = consumableType;
    }

    @Override
    public void dispose() {

    }

    public ConsumableType getConsumableType() {
        return consumableType;
    }

    public int getPotionSize() {
        return potionSize;
    }
}
