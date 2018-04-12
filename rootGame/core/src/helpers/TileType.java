package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum TileType {
    WHITE_SPACE(Constants.WHITE_SPACE_COLOR, null),
    GROUND_BRICK(Constants.GROUND_BRICK_COLOR, "img/tiles/brick.png"),
    GROUND_GRASS_MIDDLE(Constants.GROUND_GRASS_MIDDLE_COLOR, "img/tiles/brickGrassMiddle.png"),
    CHEST(Constants.CHEST, "img/elements/chest_normal_closed.png"); //TODO take sprite sheet?

    //TODO elements type?

    //TODO THE SPRITES HAS TO BE OBJECT AND CONTAINING TILESHEETS.
    //TODO SO THE WALL WILL CHECK IF THERE IS WHITESPACE TO THE SIDE, AND CHANGE TEXUTERE BASED ON THAT.


    private Texture texture;
    private Color color;

    TileType(Color color, String pathToTile){
        this.color = color;
        if(pathToTile != null)
            this.texture = new Texture(pathToTile);
    }

    public Sprite getSprite() {
        //TODO: Handles sprite call when white type
        return new Sprite(texture);
    }

    public Color getColor() {
        return color;
    }

    public Texture getTexture() {
        return texture;
    }

    public static TileType getTypeFromColor(Color color){

        if(color.equals(TileType.WHITE_SPACE.getColor()))
            return WHITE_SPACE;
        if(color.equals(TileType.GROUND_BRICK.getColor()))
            return GROUND_BRICK;
        if(color.equals(TileType.GROUND_GRASS_MIDDLE.getColor()))
            return GROUND_GRASS_MIDDLE;
        if(color.equals(TileType.CHEST.getColor()))
            return CHEST;

        return null;
    }

    private static class Constants{
        public static final Color WHITE_SPACE_COLOR = Color.valueOf("#FFFFFFFF");
        public static final Color GROUND_BRICK_COLOR = Color.valueOf("#0000FF00");
        public static final Color GROUND_GRASS_MIDDLE_COLOR = Color.valueOf("#FF00FF00");
        public static final Color CHEST = Color.valueOf("#D800FFFF");
    }
}
