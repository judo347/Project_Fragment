package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import dk.mk.MainGame;
import helpers.GameInfo;

public class MenuScene implements Screen {

    private static final int PLAY_BUTTON_Y = 260;
    private static final int EXIT_BUTTON_Y = 200;
    private static final int EXIT_BUTTON_HEIGHT = 48;
    private static final int EXIT_BUTTON_WIDTH = 160;

    private MainGame game;

    private Texture playButtonActive;
    private Texture playButtonInactive;
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    public MenuScene(MainGame game) {
        this.game = game;
        playButtonActive = new Texture("img/menu/playActive.png");
        playButtonInactive = new Texture("img/menu/playInactive.png");
        exitButtonActive = new Texture("img/menu/exitActive.png");
        exitButtonInactive = new Texture("img/menu/exitInactive.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears screen



        //Mouse input
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            //TODO
        }

        game.getBatch().begin();

        float playButtonX = GameInfo.WIDTH / 2 - playButtonInactive.getWidth() / 2;
        if(Gdx.input.getX() > playButtonX && Gdx.input.getX() < playButtonX + playButtonActive.getWidth() &&
                Gdx.input.getY() < GameInfo.HEIGHT - PLAY_BUTTON_Y && Gdx.input.getY() > GameInfo.HEIGHT - PLAY_BUTTON_Y - playButtonActive.getHeight()){

            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new MainScene(game));
            }
            game.getBatch().draw(playButtonActive, playButtonX, PLAY_BUTTON_Y);
        } else {
            game.getBatch().draw(playButtonInactive, playButtonX, PLAY_BUTTON_Y);
        }


        float exitButtonX = GameInfo.WIDTH / 2 - EXIT_BUTTON_WIDTH / 2;
        if(Gdx.input.getX() > exitButtonX && Gdx.input.getX() < exitButtonX + EXIT_BUTTON_WIDTH &&
                Gdx.input.getY() < GameInfo.HEIGHT - EXIT_BUTTON_Y && Gdx.input.getY() > GameInfo.HEIGHT - EXIT_BUTTON_Y - exitButtonActive.getHeight()){

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
