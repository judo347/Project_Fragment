package entities.Charactors;

import Items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.EntityType;
import helpers.Inventory;
import ui.UiManager;
import utilities.GameInfo;
import entities.Entity;
import world.GameMap;

public class Player extends Entity {

    private String userData; //The "name" of the sprite
    private Sprite sprite;

    public boolean isInAir = false;
    public boolean isPlayerMoving = false;
    public boolean isPlayerTouchingPortal = false;
    public static final float DEFAULT_PLAYER_FRICTION = 0.8f;
    public static final float MOVING_PLAYER_FRICTION = 0.2f;

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
    private float stateTime;

    private GameMap gameMap;
    private UiManager uiManager;

    private Inventory inventory;

    private static final float FEET_OFFSET = -0.35f;
    public static final String FEET_ID = "feet";
    private Body feet;

    Animation[] jumpMovement;
    public static final int NUMBER_OF_JUMP_FRAMES = 2;

    public Player(GameMap gameMap, UiManager uiManager, World world, Vector2 pos){
        super(world, EntityType.PLAYER, pos);

        this.gameMap = gameMap;
        this.uiManager = uiManager;

        this.feet = createFeet(world, pos);

        this.sprite = new Sprite(new Texture("img/hero/hero_stand.png"));//TODO TEMP
        this.sprite.setPosition(getPos().x + getWidth() / 2, getPos().y + getHeight() / 2);

        this.walkTimer = 0;
        this.currentWalkFrame = standFrame;
        this.currentJumpFame = 0;
        this.stateTime = 0f;

        //Not clipping ground section
        this.body.setLinearDamping(0);

        setUpAnimations();

        this.inventory = new Inventory();
    }

    /** Creates the body for the feet of the player. */
    private Body createFeet(World world, Vector2 pos){

        BodyDef bodyDefFeet = new BodyDef();
        bodyDefFeet.type = BodyDef.BodyType.DynamicBody;
        bodyDefFeet.position.set(new Vector2((pos.x + HERO_WIDTH_PIXEL / 2) / GameInfo.PPM, pos.y / GameInfo.PPM)); //TODO
        //bodyDefFeet.position.set(new Vector2(((pos.x + HERO_WIDTH_PIXEL) / GameInfo.PPM), (pos.y) / GameInfo.PPM));

        Body bodyFeet = world.createBody(bodyDefFeet);

        //Shape of box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(((HERO_WIDTH_PIXEL / 3.5f) / GameInfo.PPM), (HERO_HEIGHT_PIXEL / 12) / GameInfo.PPM); //TODO OVERFLOW?

        //FixtureDef for box
        FixtureDef fixtureDefFeet = new FixtureDef();
        fixtureDefFeet.shape = shape;
        fixtureDefFeet.density = 0;
        fixtureDefFeet.friction = 1;
        fixtureDefFeet.restitution = 0;
        fixtureDefFeet.isSensor = true;

        //Create fixture and attach it to the body
        Fixture fixtureFeet = bodyFeet.createFixture(fixtureDefFeet);

        shape.dispose();

        bodyFeet.getFixtureList().get(0).setUserData(FEET_ID);
        bodyFeet.setGravityScale(0);

        return bodyFeet;
    }

    /** Sets up the animation for the entity. */
    public void setUpAnimations(){
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

    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        updatePlayer(delta);
        playerControls(delta);

        //Make feet follow the player
        Vector2 vel = body.getLinearVelocity();
        feet.setTransform(new Vector2(body.getPosition()).add(0 ,FEET_OFFSET), 0);
        feet.setLinearVelocity(vel);
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        batch.draw((isInAir) ? getJumpSprite(stateTime) : getVerticalSprite(stateTime), getX() - (getWidth() / 2), getY() - (getHeight() / 2));
    }

    @Override
    public void dispose() {
        this.sprite.getTexture().dispose();
    }

    /** Handles player movement */
    public void playerControls(float deltaTime){

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            uiManager.hideUi();
            getBody().applyForce(new Vector2(-1f,0), getBody().getWorldCenter(), true); //2nd arg where the force is used, 3rd wake the elements and calculate

            //getBody().setLinearVelocity(new Vector2(-1f,0));

            //Update walk
            setWalkTimer(getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(getWalkTimer()) > WALK_TIMER_SWITCH_TIME){
                setWalkTimer(0);
                oneDownFrame();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            uiManager.hideUi();
            getBody().applyForce(new Vector2(1f,0), getBody().getWorldCenter(), true); //Force = overtime, implulse = right away.

            //Update walk
            setWalkTimer(getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(getWalkTimer()) > WALK_TIMER_SWITCH_TIME){
                setWalkTimer(0);
                oneUpFrame();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(!isInAir){
                getBody().applyForce(new Vector2(0,80), getBody().getWorldCenter(), true);
                isInAir = true;
                uiManager.hideUi();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            if(isPlayerTouchingPortal){
                uiManager.showElement(UiManager.UiType.LEVEL_SELECTOR);
            }
        }

        //Not moving
        if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            resetVerticalAnimation();
            body.getFixtureList().get(0).setFriction(DEFAULT_PLAYER_FRICTION);
            //System.out.println("PLAYER NOT MOVING");
            //body.setLinearVelocity(new Vector2(0,0));
        }

        /* //TODO IS THIS OK TO REMOVE?
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            if(gameMap.isPlayerTouchingPortal())
                gameMap.setScreenLevel();
                //System.out.println("Activate awesome level selector!"); //TODO Display level selector
        }*/

        if(Gdx.input.isKeyPressed(Input.Keys.I)){
            uiManager.showElement(UiManager.UiType.INVENTORY);
        }
    }

    /** Handles sprite movement with body. */
    public void updatePlayer(float deltaTime){

        this.sprite.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
        this.feet.getPosition().set(pos.x * GameInfo.PPM, pos.y * GameInfo.PPM);

        if(this.isInAir)
            inAirTime += deltaTime;
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
        this.body.getFixtureList().get(0).setFriction(MOVING_PLAYER_FRICTION);
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

    /** Adds the given item to the players inventory.
     *  @return true if item was added, false if action failed. */
    public void addItemToInventory(Item ... items){
        this.inventory.addItems(items);
    }
}
