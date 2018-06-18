package entities.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.GameInfo;
import helpers.EntityType;

public class DirectionalTile extends Tile {

    private EntityType.TextureDirection tileDirection;

    public DirectionalTile(World world, Vector2 pos, EntityType gameType, EntityType.TextureDirection tileDirection) {
        super(world, pos, gameType);

        this.tileDirection = tileDirection;
        setDirectionalTextures();
    }

    /** Set the texture to match the position */
    private void setDirectionalTextures(){

        TextureRegion[] regions = new TextureRegion[4];

        regions[0] = new TextureRegion(textureRegion, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE); //Right
        regions[1] = new TextureRegion(textureRegion, GameInfo.TILE_SIZE, 0, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE); //Under
        regions[2] = new TextureRegion(textureRegion, 0, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE); //Middle
        regions[3] = new TextureRegion(textureRegion, 0, 0, GameInfo.TILE_SIZE, GameInfo.TILE_SIZE); //Left

        switch (tileDirection){
            case RIGHT: this.textureRegion = regions[0];
                break;
            case UNDER: this.textureRegion = regions[1];
                break;
            case MIDDLE: this.textureRegion = regions[2];
                break;
            case LEFT: this.textureRegion = regions[3];
                break;
        }
    }
}
