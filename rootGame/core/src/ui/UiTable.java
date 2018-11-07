package ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import utilities.ResourceManager;

//https://github.com/libgdx/libgdx/wiki/Table

public class UiTable{

    ResourceManager rm;

    Table rootTable;
    Table contentTable; //TODO maybe use Container?

    private static final int edgePadding = 30;

    public UiTable(ResourceManager rm){
        this.rm = rm;
        setUpTable();
    }

    private void setUpTable(){
        rootTable = new Table();
        rootTable.setFillParent(true);
        //rootTable.setDebug(true); //TODO temp DEBUGGER!!!!!!!!!!!!!!!!!!

        rootTable.add(new Image(rm.boxTopLeft)).size(rm.boxSize,rm.boxSize).padTop(edgePadding).padLeft(edgePadding);
        rootTable.add(new Image(rm.boxTop)).fill().minSize(rm.boxSize,rm.boxSize).padTop(edgePadding);
        rootTable.add(new Image(rm.boxTopRight)).size(rm.boxSize,rm.boxSize).padTop(edgePadding).padRight(edgePadding);

        rootTable.row();

        rootTable.add(new Image(rm.boxLeft)).minSize(rm.boxSize,rm.boxSize).expandY().fill().padLeft(edgePadding);

        contentTable = new Table();
        //rootTable.add(new Image(rm.boxMiddle)).minSize(rm.boxSize,rm.boxSize).expandY().fill();
        contentTable.setBackground(new TextureRegionDrawable(rm.boxMiddle));
        rootTable.add(contentTable).fill();

        rootTable.add(new Image(rm.boxRight)).minSize(rm.boxSize,rm.boxSize).expandY().fill().padRight(edgePadding);

        rootTable.row();

        rootTable.add(new Image(rm.boxDownLeft)).size(rm.boxSize,rm.boxSize).padLeft(edgePadding).padBottom(edgePadding);
        rootTable.add(new Image(rm.boxDown)).fill().minSize(rm.boxSize,rm.boxSize).padBottom(edgePadding);
        rootTable.add(new Image(rm.boxDownRight)).size(rm.boxSize,rm.boxSize).padRight(edgePadding).padBottom(edgePadding);

        //System.out.println("Table is set up!"); //TODO TEMP
    }

    //TODO
    public void resize(){
        //TODO
    }

    //TODO
    public void setContent(WidgetGroup widget){ //TODO maybe container?
        this.contentTable.clearChildren();
        this.contentTable.add(widget);
    }

    public Table getTable() {
        return rootTable;
    }
}
