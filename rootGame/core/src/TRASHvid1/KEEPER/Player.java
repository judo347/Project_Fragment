package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.EntityType;
import helpers.GameInfo;
import helpers.Entity;

public abstract class Player extends Entity {

    private String userData; //The "name" of the sprite
    private Sprite sprite;

    public boolean isInAir = false;

    Animation[] verticalMovement;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final int HERO_WIDTH_PIXEL = 32;
    public static final int HERO_HEIGHT_PIXEL = 64;
    public static final int NUMBER_OF_VERTICAL_FRAMES = 5;
    public static final float WALK_TIMER_SWITCH_TIME = 0.15f;
    private float walkTimer;
    private int currentWalkFrame;
    private int standFrame = 2;
    public float inAirTime;
    private int currentJumpFame;

    Animation[] jumpMovement;
    public static final int NUMBER_OF_JUMP_FRAMES = 2;

    public Player(World world, float x, float y){
        super(world, EntityType.C); //TODO
        this.sprite = new Sprite(new Texture("img/hero/hero_stand.png")); //TODO: FILE SHOULD BE CHANGED
        this.sprite.setPosition(x - this.sprite.getWidth() / 2, y - this.sprite.getWidth() / 2);
        //this.world = world;
        this.userData = "Player";
        this.walkTimer = 0;
        this.currentWalkFrame = standFrame;
        this.currentJumpFame = 0;

        //Vertical movement animation
        this.verticalMovement = new Animation[NUMBER_OF_VERTICAL_FRAMES]; //Number of images
        TextureRegion[][] heroVerticalSpriteSheet = TextureRegion.split(new Texture("img/hero/hero_vertical.png"), HERO_WIDTH_PIXEL, HERO_HEIGHT_PIXEL); //TODO: Load atlas?

        for(int i = 0; i < NUMBER_OF_VERTICAL_FRAMES; i++)
            verticalMovement[i] = new Animation(ANIMATION_SPEED, heroVerticalSpriteSheet[0][i]); //TODO HANDLE EXCEPTION?

        //Jump animation
        this.jumpMovement = new Animation[NUMBER_OF_JUMP_FRAMES];
        TextureRegion[][] heroJumpSpriteSheet = TextureRegion.split(new Texture("img/hero/hero_jump.png"), HERO_WIDTH_PIXEL, HERO_HEIGHT_PIXEL); //TODO: Load atlas?

        for(int i = 0; i < NUMBER_OF_JUMP_FRAMES; i++)
            jumpMovement[i] = new Animation(ANIMATION_SPEED, heroJumpSpriteSheet[0][i]); //TODO HANDLE EXCEPTION?

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
        //body = world.createBody(bodyDef);

        //Collision box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2) / GameInfo.PPM, (getHeight() / 2) / GameInfo.PPM);

        //Contains mass and such
        FixtureDef fixtureDef = new FixtureDef(); //
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        //Fixture fixture = body.createFixture(fixtureDef);
        //fixture.setUserData(this.userData); //Can we called on contact

        shape.dispose(); //It is no longer needed/used
    }

    /** Handles player movement */
    public void playerControls(float deltaTime){

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            getBody().applyForce(new Vector2(-1f,0), getBody().getWorldCenter(), true); //2nd arg where the force is used, 3rd wake the elements and calculate

            //Update walk
            setWalkTimer(getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(getWalkTimer()) > WALK_TIMER_SWITCH_TIME){
                setWalkTimer(0);
                oneDownFrame();
            }

        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            getBody().applyForce(new Vector2(1f,0), getBody().getWorldCenter(), true); //Force = overtime, implulse = right away.

            //Update walk
            setWalkTimer(getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(getWalkTimer()) > WALK_TIMER_SWITCH_TIME){
                setWalkTimer(0);
                oneUpFrame();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(!isInAir)
                getBody().applyForce(new Vector2(0,30), getBody().getWorldCenter(), true);
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            resetVerticalAnimation();
        }
    }

    /** Handles sprite movement with body. */
    public void updatePlayer(float deltaTime){
        //this.sprite.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        if(this.isInAir)
            inAirTime += deltaTime;
    }

    public Body getBody(){
        return null; //this.body;
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

    public TextureRegion getVerticalSprite(float stateTime) {
        return (TextureRegion)verticalMovement[currentWalkFrame].getKeyFrame(stateTime, true);
    }

    public float getWalkTimer() {
        return walkTimer;
    }

    public void setWalkTimer(float walkTimer) {
        this.walkTimer = walkTimer;
    }

    public void oneUpFrame() {
        if(currentWalkFrame < NUMBER_OF_VERTICAL_FRAMES - 1)
        currentWalkFrame++;
        else
            currentWalkFrame--;
    }

    public void oneDownFrame() {
        if(currentWalkFrame > 0)
            currentWalkFrame--;
        else
            currentWalkFrame++;
    }

    public void resetVerticalAnimation(){
        this.currentWalkFrame = standFrame;
    }

    public TextureRegion getJumpSprite(float stateTime) {
        return (inAirTime > 0.14f) ? (TextureRegion)jumpMovement[1].getKeyFrame(stateTime, true) : (TextureRegion)jumpMovement[0].getKeyFrame(stateTime, true);
    }
}
