package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dk.mk.MainGame;
import helpers.GameInfo;
import helpers.WorldGenerator;
import sprites.Player;
import sprites.elements.Chest;
import sprites.elements.Platform;

import java.util.ArrayList;

public class TownScene implements Screen, ContactListener {

    private MainGame game;

    private Texture background;

    private Player player;

    private Platform groundPlatform1;
    private Platform groundPlatform2;
    private Platform groundPlatform3;
    private Platform groundPlatform4;
    private Platform groundPlatform5;
    private Platform groundPlatform6;
    private Platform groundPlatform7;

    private Sprite testSprite;

    private ArrayList<Sprite> elements;

    private World world;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    float stateTime; //Will be added up every frame while performing a task // How long an animation has been running

    public TownScene(MainGame game, World world){
        this.game = game;
        this.world = world;
        stateTime = 0f;

        background = new Texture("img/background.png");

        elements = WorldGenerator.generateSpriteArray("img/levels/town.png");
        //testSprite = WorldGenerator.generateSpriteArray("img/hero/hero_vertical.png");


        //What we see on the screen
        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT /2f, 0); //Pos of camera //TODO: Can we set to follow player on x-axis? maybe at another place?
        debugRenderer = new Box2DDebugRenderer();

        world.setContactListener(this); //add the contact listener to the world

        initializeGameElements();
    }

    /** Initializes all game elements. */
    void initializeGameElements(){

        player = new Player(world, GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);



        /*
        groundPlatform1 = new Platform(world, 0, 0);
        groundPlatform2 = new Platform(world, groundPlatform1.getWidth(), 0);
        groundPlatform3 = new Platform(world, groundPlatform1.getWidth() * 2, 0);
        groundPlatform4 = new Platform(world, groundPlatform1.getWidth() * 3, 0);
        groundPlatform5 = new Platform(world, groundPlatform1.getWidth() * 4, 0);
        groundPlatform6 = new Platform(world, groundPlatform1.getWidth() * 5, 0);
        groundPlatform7 = new Platform(world, groundPlatform1.getWidth() * 6, 0);
        */

        //TODO
    }

    @Override
    public void render(float delta) {

        stateTime += delta;

        player.updatePlayer(delta);
        player.playerControls(delta);

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

        for(int i = 0; i < elements.size(); i++)
            game.getBatch().draw(elements.get(i), elements.get(i).getX(),  elements.get(i).getY());

        game.getBatch().draw(testSprite,testSprite.getX(), testSprite.getY());

        //TODO: This if statement should be move to player
        game.getBatch().draw((player.isInAir) ? player.getJumpSprite(stateTime) : player.getVerticalSprite(stateTime), player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2));

        //game.getBatch().draw(groundPlatform, groundPlatform.getX() + 100, groundPlatform.getY() + 100);


        game.getBatch().end();
    }

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
    public void dispose() {
        this.player.getSprite().getTexture().dispose();

    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
