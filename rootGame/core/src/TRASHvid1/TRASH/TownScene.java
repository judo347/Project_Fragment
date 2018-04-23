package scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import dk.mk.MainGame;
import world.GameMap;

//TODO AssetManager

public class TownScene implements Screen{

    private MainGame game;

    private GameMap gameMap;

    float stateTime; //Will be added up every frame while performing a task // How long an animation has been running

    public TownScene(MainGame game, World world){
        this.game = game;
        gameMap = new GameMap("img/levels/town.png", world, game); //Load map //TODO Should not be a string
        stateTime = 0f;
    }

    @Override
    public void render(float delta) {

        stateTime += delta;
        gameMap.render(game.getBatch(), delta);
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
