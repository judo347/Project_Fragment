package helpers;

public enum ItemType {

    FRAGMENT("fragment"),
    CONSUMABLE("consumable"),
    EQUIPPABLE("equippable");

    private static final int ITEM_PIXEL_SIZE = 16;
    
    private String id;
    private int width, height;

    ItemType(String id){
        this.id = id;
        this.width = ITEM_PIXEL_SIZE;
        this.height = ITEM_PIXEL_SIZE;
    }

    public static ItemType getItemTypeFromString(String string){
        string.toLowerCase();

        if(string == FRAGMENT.toString())
            return FRAGMENT;
        if(string == CONSUMABLE.toString())
            return CONSUMABLE;
        if(string == EQUIPPABLE.toString())
            return EQUIPPABLE;

        return null;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
