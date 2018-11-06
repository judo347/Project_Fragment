package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import javafx.scene.control.Tab;
import scenes.GameScene;
import utilities.GameInfo;
import utilities.ResourceManager;
import world.GameMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ui implements Disposable {

    //https://github.com/libgdx/libgdx/wiki/Table

    private ResourceManager rm;
    private Stage stage;
    private Table rootTable;
    private Skin skin;


    public Ui(ResourceManager rm) {
        this.rm = rm;
        this.skin = rm.skin;

        stage = new Stage(new ScreenViewport());
        stage.addActor(getLevelSelectorTable());
        //stage.addActor(getInventoryTable());


        //final TextButton button = new TextButton("Click Me!", skin, "default");
        //rootTable.add(button);

        //Gdx.input.setInputProcessor(stage);
    }

    private Table getLevelSelectorTable(){

        UiTable levelSelector = new UiTable(rm);
        levelSelector.getTable().left();

        //Set content
        ArrayList<Label> labels = getAllLevelLabels();
        VerticalGroup contentGroup = new VerticalGroup();
        for(Label label : labels)
            contentGroup.addActor(label);

        levelSelector.setContent(contentGroup);
        levelSelector.contentTable.top();

        return levelSelector.getTable();
    }

    /** @return a list of labels containing the names of all levels. */
    private ArrayList<Label> getAllLevelLabels(){

        ArrayList<String> levelNames = getAllLevels();
        ArrayList<Label> labels = new ArrayList<>();

        for (String levelName : levelNames) {
            Label label = new Label(levelName, skin);
            labels.add(label);
        }

        return labels;
    }

    /** @return a list of the names of all levels. */
    private ArrayList<String> getAllLevels(){

        ArrayList<String> levelNames = new ArrayList<>();

        for (GameScene.Level level : GameScene.Level.values())
            levelNames.add(level.getName());

        return levelNames;
    }

    private Table getInventoryTable(){

        UiTable inventoryTable = new UiTable(rm);

        //levelSelector.setContent(); //TODO ADD CONTENT

        return inventoryTable.getTable();
    }

    public void render(){
        stage.act();
        stage.draw();
    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
