package helpers;

import entities.Entity;
import entities.Probs.CraftingTable;
import utilities.EntityDimensions;
import utilities.GameInfo;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import entities.Charactors.Vendor;
import entities.Charactors.Player;
import entities.Probs.Chest;
import entities.Probs.Portal;
import world.GameMap;

public enum EntityType {

    PLAYER("player", EntityDimensions.PLAYER_DIMENSIONS.getWIDTH(), EntityDimensions.PLAYER_DIMENSIONS.getHEIGHT(), BodyType.DynamicBody, Constants.PLAYER_COLOR),
    CHEST("chest", EntityDimensions.CHEST_DIMENSIONS.getWIDTH(), EntityDimensions.CHEST_DIMENSIONS.getHEIGHT(), BodyType.StaticBody, Constants.CHEST_COLOR),
    PORTAL("portal", EntityDimensions.PORTAL_DIMENSIONS.getWIDTH(), EntityDimensions.PORTAL_DIMENSIONS.getHEIGHT(), BodyType.StaticBody, Constants.PORTAL_COLOR),
    VENDOR("vendor", EntityDimensions.VENDOR_DIMENSIONS.getWIDTH(), EntityDimensions.VENDOR_DIMENSIONS.getHEIGHT(), BodyType.StaticBody, Constants.FVENDOR_COLOR),
    CRAFTING_TABLE("crafting table", EntityDimensions.CRAFTING_TABLE_DIMENSIONS.getWIDTH(), EntityDimensions.CRAFTING_TABLE_DIMENSIONS.getHEIGHT(), BodyType.StaticBody, Constants.CRAFTING_TABLE_COLOR);

    private String id;
    private int width, height;
    private BodyType bodyType;
    private Color color;


    EntityType(String id, int width, int height, BodyType bodyType, Color color) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.bodyType = bodyType;
        this.color = color;
    }

    /** Returns a new entity based on the color. Will return null if color is not matching a type.
     * @param color the color to search for.
     * @param world the world to place entity in.
     * @param pos the coordinates
     * @return an entity object matching the color given. Will return null if color is not matching a type. */
    public static Entity getEntity(Color color, World world, Vector2 pos, GameMap gameMap){

        switch (getTypeFromColor(color)){
            case PLAYER:            return new Player(gameMap, world, pos);
            case CHEST:             return new Chest(world, pos, gameMap); //TODO SHOULD BE ABLE TO HANDLE MORE THAN ONE COLOR
            case PORTAL:            return new Portal(world, pos);
            case VENDOR:            return new Vendor(world, pos);
            case CRAFTING_TABLE:    return new CraftingTable(world, pos);
        }

        return null;
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a entityType based on the color given. Returns null if non matches. */
    public static EntityType getTypeFromColor(Color color){

        if(color.equals(EntityType.PLAYER.getColor()))
            return PLAYER;
        if(color.equals(EntityType.CHEST.getColor()))
            return CHEST;
        if(color.equals(EntityType.PORTAL.getColor()))
            return PORTAL;
        if(color.equals(EntityType.VENDOR.getColor()))
            return VENDOR;
        if(color.equals(EntityType.CRAFTING_TABLE.getColor()))
            return CRAFTING_TABLE;
        else
            return null;
    }

    /** The color used by this type. */
    private static class Constants{
        public static final Color PLAYER_COLOR = Color.valueOf("#26ffff00");
        public static final Color CHEST_COLOR = Color.valueOf("#D800FFFF");
        public static final Color PORTAL_COLOR = Color.valueOf("#00ffffae");
        public static final Color FVENDOR_COLOR = Color.valueOf("#006effff"); //TODO
        public static final Color CRAFTING_TABLE_COLOR = Color.valueOf("#00dcffff");

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

    public Color getColor() {
        return color;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    /** Take a string and tries to match it with the default id. */
    public boolean isMatchingDefaultId(String diffId){
        return diffId.startsWith(id);
    }
}
