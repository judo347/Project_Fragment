package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import dk.mk.MainGame;
import helpers.GameInfo;

public class MenuScene implements Screen {

    private static final int PLAY_BUTTON_Y = 360;
    private static final int OPTIONS_BUTTON_Y = 280;
    private static final int OPTIONS_BUTTON_HEIGHT = 48;
    private static final int OPTIONS_BUTTON_WIDTH = 160;
    private static final int EXIT_BUTTON_Y = 200;
    private static final int EXIT_BUTTON_HEIGHT = 48;
    private static final int EXIT_BUTTON_WIDTH = 160;

    private MainGame game;
    private World world;

    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture optionsButtonActive;
    private Texture optionsButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    public MenuScene(MainGame game, World world) {
        this.game = game;
        this.world = world;
        playButtonActive = new Texture("img/menu/playActive.png");
        playButtonInactive = new Texture("img/menu/playInactive.png");
        optionsButtonActive = new Texture("img/menu/optionsActive.png");
        optionsButtonInactive = new Texture("img/menu/optionsInactive.png");
        exitButtonActive = new Texture("img/menu/exitActive.png");
        exitButtonInactive = new Texture("img/menu/exitInactive.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f,0.2f,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears screen



        //Mouse input
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //TODO
        }

        game.getBatch().begin();

        float playButtonX = GameInfo.SCREEN_WIDTH / 2 - playButtonInactive.getWidth() / 2;
        if(Gdx.input.getX() > playButtonX && Gdx.input.getX() < playButtonX + playButtonActive.getWidth() &&
                Gdx.input.getY() < GameInfo.SCREEN_HEIGHT - PLAY_BUTTON_Y && Gdx.input.getY() > GameInfo.SCREEN_HEIGHT - PLAY_BUTTON_Y - playButtonActive.getHeight()){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new GameScene(game)); //TODO HERE
            }
            game.getBatch().draw(playButtonActive, playButtonX, PLAY_BUTTON_Y);
        } else {
            game.getBatch().draw(playButtonInactive, playButtonX, PLAY_BUTTON_Y);
        }


        float optionsButtonX = GameInfo.SCREEN_WIDTH / 2 - OPTIONS_BUTTON_WIDTH / 2;
        if(Gdx.input.getX() > optionsButtonX && Gdx.input.getX() < optionsButtonX + OPTIONS_BUTTON_WIDTH &&
                Gdx.input.getY() < GameInfo.SCREEN_HEIGHT - OPTIONS_BUTTON_Y && Gdx.input.getY() > GameInfo.SCREEN_HEIGHT - OPTIONS_BUTTON_Y - optionsButtonActive.getHeight()){

            if(Gdx.input.isTouched()){
                //game.setScreen(new OptionScene(game, world));
                game.setScreen(new OptionScene(game, world, this));
            }
            game.getBatch().draw(optionsButtonActive, optionsButtonX, OPTIONS_BUTTON_Y, OPTIONS_BUTTON_WIDTH, OPTIONS_BUTTON_HEIGHT);
        } else {
            game.getBatch().draw(optionsButtonInactive, optionsButtonX, OPTIONS_BUTTON_Y, OPTIONS_BUTTON_WIDTH, OPTIONS_BUTTON_HEIGHT);
        }

        float exitButtonX = GameInfo.SCREEN_WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if(Gdx.input.getX() > exitButtonX && Gdx.input.getX() < exitButtonX + EXIT_BUTTON_WIDTH &&
                Gdx.input.getY() < GameInfo.SCREEN_HEIGHT - EXIT_BUTTON_Y && Gdx.input.getY() > GameInfo.SCREEN_HEIGHT - EXIT_BUTTON_Y - exitButtonActive.getHeight()){

            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
            game.getBatch().draw(exitButtonActive, exitButtonX, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        } else {
            game.getBatch().draw(exitButtonInactive, exitButtonX, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
        }


        game.getBatch().end();
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
        playButtonActive.dispose();
        playButtonInactive.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
    }
}
