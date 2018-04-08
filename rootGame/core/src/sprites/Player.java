package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Player extends Sprite {

    private World world; //We create the world in the place where we need physics
    private Body body; //The body of the player //THIS IS WHAT WE CONTROL AND MOVE

    public Player(World world, float x, float y){
        super(new Texture("img/hero.png"));
        setPosition( x - getWidth() / 2, y - getWidth() / 2);
        this.world = world;
        createBody();
    }

    void createBody(){

        BodyDef bodyDef = new BodyDef(); //Dynamic, static or kinamatic, sets the body relative to where the player is.

        //Static = not affected by gravity or any other force.
        //Kinematic = not affected by gravity but IS affected by other forces.
        //Dynamic = affected by gravity and other forces
        bodyDef.type = BodyDef.BodyType.DynamicBody; //TODO Dynamic

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
        fixture.setUserData("Player"); //Can we called on contact

        shape.dispose(); //It is no longer needed/used
    }

    /** Handles sprite movement with body. */
    public void updatePlayer(){
        this.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody(){
        return this.body;
    }
}
