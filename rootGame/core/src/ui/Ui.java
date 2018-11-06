package ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import utilities.ResourceManager;

public class Ui implements Disposable {


    private Stage stage;
    private Table table;


    public Ui(ResourceManager rm) {
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        table.setDebug(true); //TODO for debugging
        stage.addActor(table);

        Skin skin = rm.skin;

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
