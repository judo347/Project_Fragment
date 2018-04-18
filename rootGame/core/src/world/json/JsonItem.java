package world.json;

import helpers.ItemType;

public class JsonItem {

    private int uniqueId;
    private String name;
    private ItemType itemType;
    private String spritePath;

    public JsonItem(int uniqueId, String name, ItemType itemType, String spritePath) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.itemType = itemType;
        this.spritePath = spritePath;
    }
}
