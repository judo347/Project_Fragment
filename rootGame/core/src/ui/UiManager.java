package ui;

import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import helpers.Inventory;
import scenes.GameScene;
import utilities.ResourceManager;

import java.util.ArrayList;

public class UiManager implements Disposable {

    //https://github.com/libgdx/libgdx/wiki/Table

    private ResourceManager rm;
    private Stage stage;
    private Table rootTable;
    private Skin skin;
    private boolean showUi = false;

    public enum UiType{
        INVENTORY, LEVEL_SELECTOR;
    }

    public UiManager(ResourceManager rm) {
        this.rm = rm;
        this.skin = rm.skin;

        stage = new Stage(new ScreenViewport());

        //final TextButton button = new TextButton("Click Me!", skin, "default");
        //Gdx.input.setInputProcessor(stage);
    }

    /** @param uiType the UI you want shown. */
    public void showElement(UiType uiType){

        stage.clear();

        if(uiType == UiType.LEVEL_SELECTOR){
            showUi = true;
            stage.addActor(getLevelSelectorTable());
        }else if(uiType == UiType.INVENTORY){
            showUi = true;
            stage.addActor(getInventoryTable());
        }
        //TODO .. more
    }

    /** Called to hide ui. */
    public void hideUi(){
        this.showUi = false;
    }

    private Table getLevelSelectorTable(){

        UiTable levelSelector = new UiTable(rm);
        levelSelector.getTable().left();

        //Set content
        ArrayList<Button> buttons = getAllLevelLabels();
        VerticalGroup contentGroup = new VerticalGroup();
        for(Button button : buttons)
            contentGroup.addActor(button);

        contentGroup.pad(20);
        levelSelector.setContent(contentGroup);
        levelSelector.contentTable.top();

        return levelSelector.getTable();
    }

    /** @return a list of labels containing the names of all levels. */
    private ArrayList<Button> getAllLevelLabels(){

        ArrayList<String> levelNames = getAllLevels();
        ArrayList<Button> buttons = new ArrayList<>();

        for (String levelName : levelNames) {
            Button button = new TextButton(levelName, skin, "default"); //TODO maybe add custom style?
            button.addListener(new ChangeListener() { //TODO is maybe not working
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    System.out.println("WHUT");
                }
            });
            buttons.add(button);
        }

        return buttons;
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

        //TODO Should getPlayer and getInventoy of that player
        Inventory inventory = new Inventory();

        Table contentTable = new Table();
        Table leftTable = new Table();
        Table rightTable = new Table();

        //TODO

        contentTable.add(leftTable);
        contentTable.add(rightTable);

        inventoryTable.setContent(contentTable);

        return inventoryTable.getTable();
    }

    public void render(){
        if(showUi){
            stage.act();
            stage.draw();
        }
    }

    public void resize(int width, int height){
        stage.getViewport().update(width, height, true);
    }

    public boolean isShowUi() {
        return showUi;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
