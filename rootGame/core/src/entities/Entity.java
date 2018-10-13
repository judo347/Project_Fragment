package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.EntityType;
import utilities.EntityDimensions;
import utilities.GameInfo;

/** Should be used for all other game entities than tiles. */
public abstract class Entity {

    protected World world;
    protected Vector2 pos;
    protected Body body;
    protected EntityType entityType;

    public Entity(World world, EntityType entityType, Vector2 pos) {
        this.world = world;
        this.entityType = entityType;
        this.pos = pos;
        if(entityType == EntityType.PLAYER)
            this.body = createPlayerBody(world, pos);
        else
            this.body = createBody(world, pos);
    }

    private Body createPlayerBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = entityType.getBodyType();

        bodyDef.fixedRotation = true;

        bodyDef.position.set(((pos.x + entityType.getWidth() / 2) / GameInfo.PPM), (pos.y + entityType.getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((entityType.getWidth() / 2) / GameInfo.PPM, (entityType.getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = createPlayerFixtureDef();

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(entityType.getId());

        shape.dispose();

        return body;
    }

    private FixtureDef createPlayerFixtureDef(){
        float cornerSize = 0.005f;
        float width = (entityType.getWidth() / 2) / GameInfo.PPM;
        float height = (entityType.getHeight() / 2) / GameInfo.PPM;
        float heightShort = ((entityType.getHeight() / 2) / GameInfo.PPM) - cornerSize;
        PolygonShape shape = new PolygonShape();
        shape.set(new Vector2[] {
                new Vector2(-width, height),
                new Vector2(width, height),
                new Vector2(width, -heightShort),
                new Vector2(0, -height),
                new Vector2(-width, -heightShort),
        });

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        //fixtureDef.friction = 0;
        //fixtureDef.restitution = 0;

        return fixtureDef;
    }

    /** Creates the body and fixture for an entity. */
    public Body createBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = entityType.getBodyType();

        if(entityType == EntityType.PLAYER){
            bodyDef.fixedRotation = true;
        }
        bodyDef.position.set(((pos.x + entityType.getWidth() / 2) / GameInfo.PPM), (pos.y + entityType.getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((entityType.getWidth() / 2) / GameInfo.PPM, (entityType.getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(entityType.getId());

        shape.dispose();

        return body;
    }

    public abstract void update(float delta);

    /** The method used to render the entity. */
    public abstract void render (SpriteBatch batch, float delta);

    public abstract void dispose();

    public World getWorld() {
        return world;
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

    public void setId(String id) {
        this.body.getFixtureList().get(0).setUserData(id);
    }
}
