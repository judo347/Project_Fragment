package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import dk.mk.MainGame;
import entities.elements.GroundTile;
import helpers.Entity;
import helpers.GameInfo;
import world.GameMap;

//TODO AssetManager

public class TownScene implements Screen{

    private MainGame game;
    private Texture background;



    private GameMap gameMap;

    float stateTime; //Will be added up every frame while performing a task // How long an animation has been running

    public TownScene(MainGame game, World world){
        this.game = game;
        gameMap = new GameMap("img/levels/town.png", world); //Load map //TODO Should not be a string
        stateTime = 0f;

        background = new Texture("img/background.png");

        //world.setContactListener(this); //add the contact listener to the world
    }

    @Override
    public void render(float delta) {

        stateTime += delta;

        //Update gameMap elements
        gameMap.updateElements(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        game.getBatch().begin();

        game.getBatch().draw(background,0,0);

        //Render stuff in gameMap
        gameMap.render(gameMap.getBox2DCamera(), game.getBatch());

        //TODO: This if statement should be move to player
        //game.getBatch().draw((player.isInAir) ? player.getJumpSprite(stateTime) : player.getVerticalSprite(stateTime), player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2));

        game.getBatch().end();


        gameMap.getDebugRenderer().render(gameMap.getWorld(), gameMap.getBox2DCamera().combined); //Render what the camera sees

        //How many times to calculate physics in one second // 1/60f wil calculate physics 60 times each second // Gdx.graphics.getDeltaTime() = calculate every frame.
        // 2nd and 3rd param is collision between elements, they determine of precise they are. Higher = more precise
        gameMap.getWorld().step(Gdx.graphics.getDeltaTime(), 6, 2);
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
        gameMap.dispose();
    }
}
