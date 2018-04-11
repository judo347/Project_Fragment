package TRASHvid1;

import java.util.HashMap;

public enum TileType {

    BRICK(1, true, "Brick"),
    BRICK_GRASS_LEFT(2, true, "Brick Grass Left"),
    BRICK_GRASS_MIDDLE(3, true, "Brick Grass Middle"),
    BRICK_GRASS_RIGHT(4, true, "Brick Grass Right"),
    SKY(5, false, "Sky");

    public static final int TILE_SIZE = 16;

    private int id;
    private boolean colliable;
    private String name;
    private float damage;


    private TileType(int id, boolean colliable, String name){
        this(id, colliable, name, 0); //This calls the other contructor
    }

    private TileType(int id, boolean colliable, String name, float damage){
        this.id = id;
        this.colliable = colliable;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public boolean isColliable() {
        return colliable;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    private static HashMap<Integer, TileType> tileMap;

    static {
        for(TileType tileType : TileType.values()){ //TileType.values() gives array of all enum types
            tileMap.put(tileType.id, tileType);
        }
    }

    /** Gets a tileType given an id. */
    public static TileType getTileTypeById(int id){
        return tileMap.get(id);
    }
}
