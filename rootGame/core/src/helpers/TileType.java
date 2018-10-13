package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public enum TileType {
    WHITE_SPACE(Constants.WHITE_SPACE_COLOR, null),
    GROUND_BRICK(Constants.GROUND_BRICK_COLOR, "img/tiles/ground0.png"),
    GROUND_GRASS_MIDDLE(Constants.GROUND_GRASS_COLOR, "img/tiles/surface0.png"),
    GROUND_GRASS_LEFT(Constants.GROUND_GRASS_COLOR, "img/tiles/surfaceLeft.png"),
    GROUND_GRASS_RIGHT(Constants.GROUND_GRASS_COLOR, "img/tiles/surfaceRight.png");
    /* Old textures
    GROUND_BRICK(Constants.GROUND_BRICK_COLOR, "img/tiles/brick.png"),
    GROUND_GRASS_MIDDLE(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassMiddle.png"),
    GROUND_GRASS_LEFT(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassLeft.png"),
    GROUND_GRASS_RIGHT(Constants.GROUND_GRASS_COLOR, "img/tiles/brickGrassRight.png"); */

    //TODO THE SPRITES HAS TO BE OBJECT AND CONTAINING TILESHEETS.
    //TODO SO THE WALL WILL CHECK IF THERE IS WHITESPACE TO THE SIDE, AND CHANGE TEXUTERE BASED ON THAT.


    private Texture texture;
    private Color color;
    private String pathToTile;

    TileType(Color color, String pathToTile){
        this.color = color;
        this.pathToTile = pathToTile;
        if(pathToTile != null)
            this.texture = new Texture(pathToTile);
    }

    public Texture getPseudoRandomTexture(){

        Random random = new Random();
        int number = random.nextInt(4);

        if(this == GROUND_BRICK || this == GROUND_GRASS_MIDDLE){
            System.out.println("FOUND BEFORE" + pathToTile);
            //pathToTile.replace("0.png", number + ".png");
            pathToTile = pathToTile.substring(0, pathToTile.length() - 5);
            pathToTile = pathToTile + number + ".png";

            System.out.println("FOUND AFTERR" + pathToTile);
            return new Texture(pathToTile);
        }


        System.out.println(pathToTile);
        return new Texture(pathToTile);
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a tileType based on the color given. Returns null if non matches. */
    public static TileType getTypeFromColor(Color color){

        for (TileType type : TileType.values())
            if (type.color.equals(color)) return type;

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
