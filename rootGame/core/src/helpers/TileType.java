package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum TileType {
    WHITE_SPACE(Constants.WHITE_SPACE_COLOR, null),
    GROUND_BRICK(Constants.GROUND_BRICK_COLOR, "img/tiles/brick.png"),
    GROUND_GRASS_MIDDLE(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassMiddle.png"),
    GROUND_GRASS_LEFT(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassLeft.png"),
    GROUND_GRASS_RIGHT(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassRight.png");

    //TODO THE SPRITES HAS TO BE OBJECT AND CONTAINING TILESHEETS.
    //TODO SO THE WALL WILL CHECK IF THERE IS WHITESPACE TO THE SIDE, AND CHANGE TEXUTERE BASED ON THAT.


    private Texture texture;
    private Color color;

    TileType(Color color, String pathToTile){
        this.color = color;
        if(pathToTile != null)
            this.texture = new Texture(pathToTile);
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a tileType based on the color given. Returns null if non matches. */
    public static TileType getTypeFromColor(Color color){

        if(color.equals(TileType.WHITE_SPACE.getColor()))
            return WHITE_SPACE;
        if(color.equals(TileType.GROUND_BRICK.getColor()))
            return GROUND_BRICK;
        if(color.equals(TileType.GROUND_GRASS_MIDDLE.getColor()))
            return GROUND_GRASS_MIDDLE;
        else
            return null;
    }

    /** The color used by this type. */
    private static class Constants{
        public static final Color WHITE_SPACE_COLOR = Color.valueOf("#FFFFFFFF");
        public static final Color GROUND_BRICK_COLOR = Color.valueOf("#0000FF00");
        public static final Color GROUND_GRASS_COLOR = Color.valueOf("#FF00FF00");
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
}
