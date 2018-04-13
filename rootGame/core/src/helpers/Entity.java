package helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/** Should be used for all other game entities than tiles. */
public abstract class Entity {

    protected World world;
    protected EntityType entityType;
    protected BodyType bodyType;
    protected Body body;
    protected float x, y;

    public void Entity(World world, EntityType entityType, float x, float y){
        this.world = world;
        this.entityType = entityType;
        this.bodyType = entityType.getBodyType();
        this.x = x;
        this.y = y;
        this.body = entityType.createBody(world, x , y);
    }

    public abstract void render (SpriteBatch batch);

    public World getWorld() {
        return world;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public Body getBody() {
        return body;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return entityType.getWidth();
    }

    public float getHeight() {
        return entityType.getHeight();
    }

    public String getId() {
        return entityType.getId();
    }
}
