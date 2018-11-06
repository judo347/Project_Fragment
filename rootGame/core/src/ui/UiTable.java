package ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import utilities.ResourceManager;

public class UiTable{

    Table rootTable;
    ResourceManager rm;

    Table contentTable;

    public UiTable(ResourceManager rm){
        this.rm = rm;
        setUpTable();
    }

    private void setUpTable(){
        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setDebug(true); //TODO temp

        rootTable.add(new Image(rm.boxTopLeft)).size(rm.boxSize,rm.boxSize);
        rootTable.add(new Image(rm.boxTop)).minSize(rm.boxSize,rm.boxSize);
        rootTable.add(new Image(rm.boxTopRight)).size(rm.boxSize,rm.boxSize);

        rootTable.row();

        rootTable.add(new Image(rm.boxLeft)).minSize(rm.boxSize,rm.boxSize).expandY().fill();
        rootTable.add(new Image(rm.boxMiddle)).minSize(rm.boxSize,rm.boxSize).expandY().fill();
        rootTable.add(new Image(rm.boxRight)).minSize(rm.boxSize,rm.boxSize).expandY().fill();

        rootTable.row();

        rootTable.add(new Image(rm.boxDownLeft)).size(rm.boxSize,rm.boxSize);
        rootTable.add(new Image(rm.boxDown)).minSize(rm.boxSize,rm.boxSize);
        rootTable.add(new Image(rm.boxDownRight)).size(rm.boxSize,rm.boxSize);

        System.out.println("Table is set up!"); //TODO TEMP
    }

    //TODO
    public void resize(){
        //TODO
    }

    //TODO
    public void setContent(Object obj){
        this.contentTable.clearChildren();
        this.contentTable.add((Actor) obj);
    }

    public Table getTable() {
        return rootTable;
    }
}
