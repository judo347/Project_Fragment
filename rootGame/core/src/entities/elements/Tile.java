package entities.elements;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.EntityType;
import helpers.GameObjects.GameObject;
import helpers.GameObjects.TextureObject;

public class Tile extends TextureObject {

    private EntityType entityType;

    public Tile(World world, Vector2 pos, EntityType entityType) {
        super(world, pos, GameObject.DEFAULT_STATIC_BODYDEF, GameObject.DEFAULT_STATIC_FIXTUREDEF, new TextureRegion(entityType.getTexture()));
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
