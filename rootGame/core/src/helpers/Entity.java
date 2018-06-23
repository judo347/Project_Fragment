package helpers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/** Should be used for all other game entities than tiles. */
public abstract class Entity {

    protected World world;
    protected EntityType entityType;
    protected BodyType bodyType;
    protected Body body;
    protected Vector2 pos;
    protected String id;

    public Entity(World world, EntityType entityType, Vector2 pos) {
        this.world = world;
        this.entityType = entityType;
        this.bodyType = entityType.getBodyType();
        this.pos = pos;
        this.body = entityType.createBody(world, pos);
        this.id = (String)body.getFixtureList().get(0).getUserData();
    }

    public abstract void update(float delta);

    /** The method used to render the entity. */
    public abstract void render (SpriteBatch batch);

    public abstract void dispose();

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

    public Vector2 getPos() {
        return pos;
    }

    public float getWidth() {
        return entityType.getWidth();
    }

    public float getHeight() {
        return entityType.getHeight();
    }

    public String getDefaultTypeId() {
        return entityType.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.body.getFixtureList().get(0).setUserData(id); //TODO this line might not be needed
    }
}
