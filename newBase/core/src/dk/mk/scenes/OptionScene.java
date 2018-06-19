package dk.mk.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import dk.mk.MainGame;
import dk.mk.helpers.GameInfo;

public class OptionScene implements Screen{

    private static final int BACK_BUTTON_X = 200, BACK_BUTTON_Y = 200;
    private static final int TEXT_FULLSCREEN_X = 0, TEXT_FULLSCREEN_Y = 0;
    private static final int TEXT_SOUND_X = 0, TEXT_SOUND_Y = 0;
    private static final int TEXT_MUSIC_X = 0, TEXT_MUSIC_Y = 0;

    private MainGame game;
    private World world;
    private Screen screen;

    private Texture backButtonActive;
    private Texture backButtonInactive;
    private Texture textFullScreen;
    private Texture textSound;
    private Texture textMusic;

    public OptionScene(MainGame game, World world, Screen screen){
        this.game = game;
        this.world = world;
        this.screen = screen;
        backButtonActive = new Texture("img/optionsMenu/backActive.png");
        backButtonInactive = new Texture("img/optionsMenu/backInactive.png");
        //textFullScreen = new Texture("img/optionsMenu/fullscreen.png");
        //textSound = new Texture("img/optionsMenu/sound.png");
        //textMusic = new Texture("img/optionsMenu/music.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.2f,0.2f,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears screen

        game.getBatch().begin();

        float backButtonX = GameInfo.WIDTH / 2 - backButtonInactive.getWidth() / 2;
        if(Gdx.input.getX() > backButtonX && Gdx.input.getX() < backButtonX + backButtonInactive.getWidth() &&
                Gdx.input.getY() < GameInfo.HEIGHT - BACK_BUTTON_Y && Gdx.input.getY() > GameInfo.HEIGHT - BACK_BUTTON_Y - backButtonActive.getHeight()){

            if(Gdx.input.isTouched()){
                //game.setScreen(new MenuScene(game, world)); //TODO: Can i return to previous screen instead of creating a new one?
                game.setScreen(screen); //TODO: Can i return to previous screen instead of creating a new one?
            }
            game.getBatch().draw(backButtonActive, backButtonX, BACK_BUTTON_Y, backButtonInactive.getWidth(), backButtonInactive.getHeight());
        } else {
            game.getBatch().draw(backButtonInactive, backButtonX, BACK_BUTTON_Y, backButtonInactive.getWidth(), backButtonInactive.getHeight());
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
        backButtonActive.dispose();
        backButtonInactive.dispose();
        //textFullScreen.dispose();
        //textMusic.dispose();
        //textSound.dispose();
    }
}
