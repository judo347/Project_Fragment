package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import helpers.GameInfo;
import org.omg.CORBA.PUBLIC_MEMBER;

public class Player {

    private World world; //We create the world in the place where we need physics
    private Body body; //The body of the player //THIS IS WHAT WE CONTROL AND MOVE
    private String userData; //The "name" of the sprite
    private Sprite sprite;

    public boolean isInAir = false;

    Animation[] verticalMovement;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int HERO_WIDTH_PIXEL = 32;
    public static final int HERO_HEIGHT_PIXEL = 64;
    public static final int NUMBER_OF_FRAMES = 5;
    public static final float WALK_TIMER_SWITCH_TIME = 0.15f;
    private float walkTimer;
    private int currentFrame;

    public Player(World world, float x, float y){
        this.sprite = new Sprite(new Texture("img/hero/hero_stand.png")); //TODO: FILE SHOULD BE CHANGED
        this.sprite.setPosition(x - this.sprite.getWidth() / 2, y - this.sprite.getWidth() / 2);
        this.world = world;
        this.userData = "Player";
        this.walkTimer = 0;
        this.currentFrame = 2;

        this.verticalMovement = new Animation[NUMBER_OF_FRAMES]; //Number of images
        TextureRegion[][] heroVerticalSpriteSheet = TextureRegion.split(new Texture("img/hero/hero_vertical.png"), HERO_WIDTH_PIXEL, HERO_HEIGHT_PIXEL);

        for(int i = 0; i < NUMBER_OF_FRAMES; i++)
            verticalMovement[i] = new Animation(ANIMATION_SPEED, heroVerticalSpriteSheet[0][i]); //TODO HANDLE EXCEPTION?

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
        fixture.setUserData(this.userData); //Can we called on contact

        shape.dispose(); //It is no longer needed/used
    }

    /** Handles sprite movement with body. */
    public void updatePlayer(){
        this.sprite.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    public Body getBody(){
        return this.body;
    }

    public String getUserData() {
        return userData;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getWidth(){
        return this.sprite.getWidth();
    }

    public float getHeight(){
        return this.sprite.getHeight();
    }

    public float getX(){
        return this.sprite.getX();
    }

    public float getY(){
        return this.sprite.getY();
    }

    public TextureRegion getVerticalMovement(float stateTime) {
        return (TextureRegion)verticalMovement[currentFrame].getKeyFrame(stateTime, true);
    }


    public float getWalkTimer() {
        return walkTimer;
    }

    public void setWalkTimer(float walkTimer) {
        this.walkTimer = walkTimer;
    }

    public void oneUpFrame(){
        if(currentFrame < NUMBER_OF_FRAMES - 1)
        currentFrame++;
        else
            currentFrame--;
    }

    public void oneDownFrame(){
        if(currentFrame > 0)
            currentFrame--;
        else
            currentFrame++;
    }
}
