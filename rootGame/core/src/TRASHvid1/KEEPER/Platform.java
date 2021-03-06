package entities.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Platform extends Sprite{

    private World world;
    private Body body;
    private String userData; //The "name" of the sprite

    public Platform(World world, float x, float y) {
        super(new Texture("img/ground.png"));
        this.world = world;
        this.userData = "Platform";
        //setPosition( x, y);
        setPosition( x + getWidth() / 2, y + getHeight() / 2);
        createBody();
    }

    void createBody(){
        BodyDef bodyDef = new BodyDef(); //Dynamic, static or kinamatic, sets the body relative to where the player is.

        //Static = not affected by gravity or any other force.
        //Kinematic = not affected by gravity but IS affected by other forces.
        //Dynamic = affected by gravity and other forces
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);

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

    public String getUserData() {
        return userData;
    }

}
