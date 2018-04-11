package helpers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

/** Should be used for non moving objects. Walls, merchants and chest etc. */
public abstract class GameObject {

    private World world;
    private Body body;
    private String userData;
    private float x, y;
    private float width, height;
    private Sprite sprite; //Could be tiles

    public GameObject(World world, float x, float y, Sprite sprite, String userData){ //The sprite might need to change
        this.world = world;
        this.x = x;
        this.y = y;
        this.userData = userData;
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        createBody();
    }

    private void createBody(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(((getX() + getWidth() / 2) / GameInfo.PPM), (getY() + getHeight() / 2) / GameInfo.PPM);

        //Add the body to the world
        body = world.createBody(bodyDef);

        //Collision box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2) / GameInfo.PPM, (getHeight() / 2) / GameInfo.PPM);


        //Contains mass and such
        FixtureDef fixtureDef = new FixtureDef(); //
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData("Platform"); //Can we called on contact
        //fixture.setSensor(true); //Detect collision but elements can pass through it.

        shape.dispose(); //It is no longer needed/used
    }


    public World getWorld() {
        return world;
    }

    public Body getBody() {
        return body;
    }

    public String getUserData() {
        return userData;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
