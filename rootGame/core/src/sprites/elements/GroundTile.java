package sprites.elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;
import helpers.TileType;

public class GroundTile extends Sprite {

    private World world;
    private Body body;
    private String userData;
    private TileType tileType;

    //TODO: Should contain spriteSheet to allow check for border grass

    public GroundTile(World world, TileType tileType, float x, float y){
        super(tileType.getTexture()); //TODO: Should the tileType contain Sprite?
        this.world = world;
        this.tileType = tileType;
        this.userData = "ground";
        setPosition(x, y);
        createBody();

    }

    void createBody(){
        BodyDef bodyDef = new BodyDef(); //Dynamic, static or kinamatic, sets the body relative to where the player is.

        //Static = not affected by gravity or any other force.
        //Kinematic = not affected by gravity but IS affected by other forces.
        //Dynamic = affected by gravity and other forces
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
        fixture.setUserData(userData); //Can we called on contact
        //fixture.setSensor(true); //Detect collision but elements can pass through it.

        shape.dispose(); //It is no longer needed/used
    }

    public String getUserData() {
        return userData;
    }
}
