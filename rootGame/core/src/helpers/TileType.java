package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public enum TileType {
    WHITE_SPACE(new Color(255,255,255,255), "whiteSpace"),
    GROUND_BRICK(new Color(0,0,0,255), "groundBrick"),
    GROUND_GRASS(new Color(0,255,0,255), "groundGrass");


    private Sprite sprite;
    private Color color;

    TileType(Color color, String pathToTile){

        this.color = color;

        //TODO: Load sprite/textregion from file;
        this.sprite = sprite;
    }
}
