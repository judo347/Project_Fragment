package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.FloatingVendor2;
import entities.elements.Chest2;
import entities.elements.Portal2;
import helpers.GameObjects.RenderableObject;
import world.GameMap;

public enum EntityType {

    WHITE_SPACE( "#FFFFFFFF", null, false),
    GROUND( "#0000FF00", "img/tiles/tiles.png", true),
    PLAYER("#26ffff00", null, false),
    CHEST_NORMAL("D800FFFF", null, false),
    CHEST_LEGENDARY("D800FFFF", null, false), //TODO ADD UNIQUE COLOR
    PORTAL("#00ffffae", null, false),
    FVENDOR("#006effff", null, false);

    //TODO THE SPRITES HAS TO BE OBJECT AND CONTAINING TILESHEETS.
    //TODO SO THE WALL WILL CHECK IF THERE IS WHITESPACE TO THE SIDE, AND CHANGE TEXUTERE BASED ON THAT.


    private Texture texture;
    private Color color;
    private boolean isDirectionalTile;

    EntityType(String color, String pathToTile, boolean isDirectionalTile){
        this.color = Color.valueOf(color);
        this.isDirectionalTile = isDirectionalTile;

        if(pathToTile != null)
            this.texture = new Texture(pathToTile);
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a tileType based on the color given. Returns null if non matches. */
    public static EntityType getTypeFromColor(Color color){

        for (EntityType type : EntityType.values())
            if (type.color.equals(color)) return type;

        return null;
    }

    public static RenderableObject getEntity(Color color, World world, Vector2 pos, GameMap gameMap){

        switch (getTypeFromColor(color)){
            case WHITE_SPACE:       return null; //SHOULD NOT GET CALLED
            case GROUND:            return null; //SHOULD NOT GET CALLED
            //case PLAYER:            return new Player(gameMap, world, x, y);
            case CHEST_NORMAL:      return new Chest2(world, pos, ChestType.NORMAL);
            case CHEST_LEGENDARY:   return new Chest2(world, pos, ChestType.LEGENDARY);
            case PORTAL:            return new Portal2(world, pos);
            case FVENDOR:           return new FloatingVendor2(world, pos);
        }

        return null;
    }

    /** An enum describing the tiles placement related to its surroundings */
    public enum TextureDirection {
        LEFT, MIDDLE, RIGHT, UNDER
    }

    public Sprite getSprite() {
        return new Sprite(texture);
    }

    public Color getColor() {
        return color;
    }

    public Texture getTexture() {
        return texture;
    }

    public void dispose(){
        texture.dispose();
    }

    public boolean isDirectionalTile() {
        return isDirectionalTile;
    }
}
