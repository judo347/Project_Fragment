package scenes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.mk.MainGame;
import helpers.ChestType;
import helpers.GameInfo;
import sprites.*;
import sprites.elements.Chest;
import sprites.elements.Platform;

public class MainScene implements Screen, ContactListener{

    private MainGame game;

    private Texture background;

    private Player player;

    private Platform platform1;
    private Platform platform2;
    private Platform platform3;
    private Platform platform4;

    private Chest chest;

    private World world;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    float stateTime; //Will be added up every frame while performing a task // How long an animation has been running

    public MainScene(MainGame game){
        this.game = game;
        background = new Texture("img/background.png");

        //What we see on the screen
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT /2f, 0); //Pos of camera //TODO: Can we set to follow player on x-axis? maybe at another place?
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0,-9.8f), true); //Creating a world with gravity, true allows sleep = Dont calculate when nothing happens to elements.
        world.setContactListener(this); //add the contact listener to the world

        stateTime = 0f;

        //Creation of world elements
        player = new Player(world, GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 + 150);

        platform1 = new Platform(world, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2);
        platform2 = new Platform(world, GameInfo.WIDTH / 3f,GameInfo.HEIGHT / 3f);
        platform3 = new Platform(world, GameInfo.WIDTH / 3f * 2,GameInfo.HEIGHT / 3f);
        platform4 = new Platform(world, GameInfo.WIDTH / 3f * 2.5f, GameInfo.HEIGHT / 5f * 3);

        chest = new Chest(world, ChestType.LEGENDARY, platform4.getX(), platform4.getY() + 25); //platform4.getHeight() * 2
    }

    void update(float deltaTime){

        //Handling user input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.getBody().applyForce(new Vector2(-1f,0), player.getBody().getWorldCenter(), true); //2nd arg where the force is used, 3rd wake the elements and calculate

            //Update walk
            player.setWalkTimer(player.getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(player.getWalkTimer()) > player.WALK_TIMER_SWITCH_TIME){
                player.setWalkTimer(0);
                player.oneDownFrame();
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.getBody().applyForce(new Vector2(1f,0), player.getBody().getWorldCenter(), true); //Force = overtime, implulse = right away.

            //Update walk
            player.setWalkTimer(player.getWalkTimer() - Gdx.graphics.getDeltaTime());
            if(Math.abs(player.getWalkTimer()) > player.WALK_TIMER_SWITCH_TIME){
                player.setWalkTimer(0);
                player.oneUpFrame();
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(!player.isInAir)
                player.getBody().applyForce(new Vector2(0,30), player.getBody().getWorldCenter(), true);
        }

        if(!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.resetVerticalAnimation();
        }
    }

    @Override
    public void render(float delta) {

        update(delta);

        player.updatePlayer();

        stateTime += delta;

        if(player.isInAir)
            player.inAirTime += delta;

        drawElements(delta);

        debugRenderer.render(world, box2DCamera.combined); //Render what the camera sees

        //How many times to calculate physics in one second // 1/60f wil calculate physics 60 times each second // Gdx.graphics.getDeltaTime() = calculate every frame.
        // 2nd and 3rd param is collision between elements, they determine of precise they are. Higher = more precise
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public void drawElements(float deltaTime){

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        game.getBatch().begin();

        game.getBatch().draw(background,0,0);

        //TODO: This if statement should be move to player
        game.getBatch().draw((player.isInAir) ? player.getJumpSprite(stateTime) : player.getVerticalSprite(stateTime), player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2));

        game.getBatch().draw(platform1, platform1.getX() - (platform1.getWidth() / 2), platform1.getY() - (platform1.getHeight() / 2));
        game.getBatch().draw(platform2, platform2.getX() - (platform2.getWidth() / 2), platform2.getY() - (platform2.getHeight() / 2));
        game.getBatch().draw(platform3, platform3.getX() - (platform3.getWidth() / 2), platform3.getY() - (platform3.getHeight() / 2));
        game.getBatch().draw(platform4, platform4.getX() - (platform4.getWidth() / 2), platform4.getY() - (platform4.getHeight() / 2));

        game.getBatch().draw(chest.getSprite(), chest.getX() - (chest.getSprite().getWidth() / 2), chest.getY()  - (chest.getSprite().getHeight() / 2));

        game.getBatch().end();
    }

    /** Getting called when app is terminated. Used to dispose textures. This is also called when changing menus*/
    @Override
    public void dispose() {

        // Will free up memory
        background.dispose();
        player.getSprite().getTexture().dispose();
        platform1.getTexture().dispose();
        platform2.getTexture().dispose();
        platform3.getTexture().dispose();
        platform4.getTexture().dispose();
    }

    @Override
    public void beginContact(Contact contact) {

        /*
        Fixture playerFixture;

        //Get player fixture
        if(contact.getFixtureA().getUserData() == this.player.getUserData()){
            playerFixture = contact.getFixtureA();
        }else
            playerFixture = contact.getFixtureB();
        */

        if(contact.getFixtureA().getUserData() == this.player.getUserData() || contact.getFixtureB().getUserData() == this.player.getUserData()){
            if(contact.getFixtureA().getUserData() == this.platform1.getUserData() || contact.getFixtureB().getUserData() == this.platform1.getUserData())
                player.isInAir = false;
        }

        if(contact.getFixtureA().getUserData() == this.chest.getUserData() || contact.getFixtureB().getUserData() == this.chest.getUserData())
            chest.openChest();
    }

    @Override
    public void endContact(Contact contact) {
        if(contact.getFixtureA().getUserData() == this.player.getUserData() || contact.getFixtureB().getUserData() == this.player.getUserData()){
            if(contact.getFixtureA().getUserData() == this.platform1.getUserData() || contact.getFixtureB().getUserData() == this.platform1.getUserData()){
                player.isInAir = true;
                player.inAirTime = 0;
            }
        }
    }

    //NOT USED-----

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
