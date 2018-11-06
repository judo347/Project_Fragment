package ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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



    public Ui(ResourceManager rm) {
        this.rm = rm;

        stage = new Stage(new ScreenViewport());
        stage.addActor(getLevelSelectorTable());
        //stage.addActor(getInventoryTable());

        Skin skin = rm.skin;

        final TextButton button = new TextButton("Click Me!", skin, "default");
        //rootTable.add(button);

        //Gdx.input.setInputProcessor(stage);
    }

    private Table getLevelSelectorTable(){

        UiTable levelSelector = new UiTable(rm);
        levelSelector.getTable().left();

        //levelSelector.setContent(); //TODO ADD CONTENT
        System.out.println(getAllLevels());

        return levelSelector.getTable();
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
