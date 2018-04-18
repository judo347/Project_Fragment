package entities.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import helpers.ItemType;

public class Item {

    private World world;
    private String name;
    private ItemType itemType;
    private Body body;
    private float x, y;
    private Sprite sprite;

    public Item(World world, String name, ItemType itemType, Sprite sprite, float x, float y) {
        this.world = world;
        this.name = name;
        this.itemType = itemType;
        this.body = itemType.createBody(world, x, y);
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
