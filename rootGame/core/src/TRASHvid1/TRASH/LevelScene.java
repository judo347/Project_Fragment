package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.physics.box2d.World;
import dk.mk.MainGame;
import world.GameMap;

public class LevelScene implements Screen {

    private MainGame game;
    private GameMap gameMap;

    float stateTime;

    public LevelScene(MainGame game, World world) {
        this.game = game;
        gameMap = new GameMap("img/levels/level1.png", world, game);
        stateTime = 0f;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stateTime += delta;
        gameMap.render(game.getBatch(), delta);
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
