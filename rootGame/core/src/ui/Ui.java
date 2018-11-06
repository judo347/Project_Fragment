package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;

public class Ui implements Disposable {


    private Stage stage;
    private Table table;


    public Ui() {
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true); //TODO for debugging
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("ui/skin/uiskin.json"));
        final TextButton button = new TextButton("Click Me!", skin, "default");
        table.add(button);

        //Gdx.input.setInputProcessor(stage);
    }

    public void render(){
        stage.act();
        stage.draw();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
