package ui.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import utilities.ResourceManager;

public class UiSlot {

    private ResourceManager rm;

    private Table rootTable;
    private Table contentTable;

    public UiSlot(ResourceManager rm) {
        this.rm = rm;
        //setUpTable();
    }
}
