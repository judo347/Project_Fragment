package scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import main.MainGame;
import world.GameMap;

public class GameScene implements Screen{

    public enum Level{
        TOWN("img/levels/town.png"), LEVEL1("img/levels/level1.png"), CHEST("img/levels/chest.png");

        private final String levelPath;

        Level(String levelPath) {
            this.levelPath = levelPath;
        }
    }

    private MainGame game;
    private GameMap gameMap;
    private World world;
    private Level currentLevel;

    float stateTime;//Will be added up every frame while performing a task // How long an animation has been running

    public GameScene(MainGame game){
        this.game = game;
        this.currentLevel = Level.TOWN;
        initialize();
    }

    private void initialize(){
        this.stateTime = 0f;
        this.world = new World(new Vector2(0,-9.8f), true); //Creating a world with gravity, true allows sleep = Dont calculate when nothing happens to elements.
        this.gameMap = new GameMap(currentLevel.levelPath, world, game, this);
    }

    public void changeLevel(Level level){
        this.currentLevel = level;
        initialize();
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
