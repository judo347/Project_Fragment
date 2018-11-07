package ui;

import Items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import helpers.Inventory;
import scenes.GameScene;
import utilities.GameInfo;
import utilities.ResourceManager;

import java.util.ArrayList;
import java.util.Arrays;

public class UiManager implements Disposable {

    //https://github.com/libgdx/libgdx/wiki/Table

    private ResourceManager rm;
    private GameScene gameScene;
    private Stage stage;
    private Table rootTable;
    private Skin skin;
    private boolean showUi = false;

    private final int NUMBER_OF_VERTICAL_INVENTORY_CELLS = 0;

    public enum UiType{
        INVENTORY, LEVEL_SELECTOR;
    }

    public UiManager(ResourceManager rm, GameScene gameScene) {
        this.rm = rm;
        this.gameScene = gameScene;
        this.skin = rm.skin;

        stage = new Stage(new ScreenViewport());

        //final TextButton button = new TextButton("Click Me!", skin, "default");
        //Gdx.input.setInputProcessor(stage);
    }

    /** @param uiType the UI you want shown. */
    public void showElement(UiType uiType, Inventory inventory){

        stage.clear();

        Gdx.input.setInputProcessor(stage);

        if(uiType == UiType.LEVEL_SELECTOR){
            showUi = true;
            stage.addActor(getLevelSelectorTable());
        }else if(uiType == UiType.INVENTORY){
            showUi = true;
            stage.addActor(getInventoryTable(inventory));
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

        for (GameScene.Level level : GameScene.Level.values()) {
            Button button = new TextButton(level.getName(), skin, "default"); //TODO maybe add custom style?
            button.addListener(new ChangeListener() { //TODO is maybe not working
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    removeAndHideUi();
                    gameScene.changeLevel(level);
                }
            });
            buttons.add(button);
        }

        return buttons;
    }

    private void removeAndHideUi(){
        showUi = false;
        stage.clear();
    }

    /** @return a list of the names of all levels. */
    private ArrayList<String> getAllLevels(){

        ArrayList<String> levelNames = new ArrayList<>();

        for (GameScene.Level level : GameScene.Level.values())
            levelNames.add(level.getName());

        return levelNames;
    }

    private Table getInventoryTable(Inventory givenInventory){

        UiTable inventoryTable = new UiTable(rm);

        //TODO Should getPlayer and getInventoy of that player
        Inventory inventory = new Inventory();

        Table contentTable = new Table();
        Table leftTable = new Table();
        ScrollPane rightScrollPane;



        //right table content = inventory
        rightScrollPane = getRightInventorySide(givenInventory.getItems());

        //left table content = char + frag
        //TODO

        contentTable.add(leftTable);
        contentTable.add(rightScrollPane);

        inventoryTable.setContent(contentTable);

        return inventoryTable.getTable();
    }


    /** Returns the scrollPane containing the inventory.
    private ScrollPane getRightInventorySide(){

        // https://stackoverflow.com/questions/21559131/scene2d-ui-how-to-make-a-grid-table
        Table inventoryTable = new Table();
        ScrollPane scrollPane = new ScrollPane(inventoryTable);
        int rightTableMaxWidth = Gdx.graphics.getWidth() / 4; //TODO should be adjusted to handle space between cells
        //int numberOfHorizontalCells = rightTableMaxWidth / GameInfo.ITEM_SIZE;
        int numberOfHorizontalCells = rightTableMaxWidth / GameInfo.ITEM_SIZE;
        inventoryTable.setWidth(numberOfHorizontalCells * GameInfo.ITEM_SIZE); //TODO should not be needed

        for(int i = 0; i < NUMBER_OF_VERTICAL_INVENTORY_CELLS; i++){
            for(int j = 0; j < numberOfHorizontalCells; j++){
                Actor actor = new Image(rm.consumableHpLarge); //TODO Change to something else!!
                inventoryTable.add(actor);
            }
            inventoryTable.row();
        }

        return scrollPane;
    }
    */

    /** Returns the scrollPane containing the inventory. */
    private ScrollPane getRightInventorySide(ArrayList<Item> itemsList){

        if(itemsList.size() == 0)
            return new ScrollPane(new Table()); //TODO SET SOME SORT OF MINIMUM SIZE

        // https://stackoverflow.com/questions/21559131/scene2d-ui-how-to-make-a-grid-table
        Table inventoryTable = new Table();
        ScrollPane scrollPane = new ScrollPane(inventoryTable);
        int rightTableMaxWidth = Gdx.graphics.getWidth() / 4; //TODO should be adjusted to handle space between cells
        //int numberOfHorizontalCells = rightTableMaxWidth / GameInfo.ITEM_SIZE;
        int numberOfHorizontalCells = rightTableMaxWidth / GameInfo.ITEM_SIZE;
        inventoryTable.setWidth(numberOfHorizontalCells * GameInfo.ITEM_SIZE); //TODO should not be needed
        int minimumNumberOfVerticalCells = (int)Math.ceil((double)itemsList.size() / numberOfHorizontalCells); //Calculate the minimum requred cells to display all items.
        int numberofVerticalCells = (NUMBER_OF_VERTICAL_INVENTORY_CELLS < minimumNumberOfVerticalCells) ? minimumNumberOfVerticalCells : NUMBER_OF_VERTICAL_INVENTORY_CELLS;

        ArrayList<Item> tempItems = new ArrayList(itemsList);

        for(int i = 0; i < numberofVerticalCells; i++){
            for(int j = 0; j < numberOfHorizontalCells; j++){
                Actor actor;
                if(tempItems.size() != 0){
                    actor = new Image(tempItems.get(0).getTexture());
                    tempItems.remove(0);
                }else
                    actor = new Image(rm.blackSquare16); //TODO Change to something else!!

                inventoryTable.add(actor);
            }
            inventoryTable.row();
        }

        return scrollPane;
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
