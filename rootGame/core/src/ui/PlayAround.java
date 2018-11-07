package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * https://rskupnik.github.io/libgdx-ui-overview
 */

public class PlayAround{

    private Stage stage;
    private Skin skin; //Is what provides graphics for the actors in Scene2D.ui

    private Table table; //Will contain our controls
    private TextButton startButton;
    private TextButton quitButton;

    public PlayAround() {

        this.skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.stage = new Stage(new ScreenViewport());



        Gdx.input.setInputProcessor(stage);
    }

    private void createButton(){

        final TextButton button = new TextButton("Click Me!", skin, "default");
        button.setWidth(200);
        button.setHeight(50);

        final Dialog dialog = new Dialog("Click Message", skin);

        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.show(stage);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run() {
                        dialog.hide();
                    }
                }, 5);
            }
        });

        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage() {
        return stage;
    }
}
