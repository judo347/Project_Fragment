package ui.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import utilities.ResourceManager;

public abstract class UiElement {

    private ResourceManager rm;

    private Table rootTable;
    private Table contentTable;

    public UiElement(ResourceManager rm){
        this.rm = rm;
        setUpTable();
    }

    abstract void setUpTable();
    abstract void resize();

}
