package ui.elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import utilities.ResourceManager;

//https://github.com/libgdx/libgdx/wiki/Table

public class UiBorderElement {

    public enum UiBorderType{
        INVENTORY_SLOT(15, "ui/img/inventorySlot/uiInventorySlot.atlas", 4), BACKGROUND(30, "ui/img/box/box.atlas", 16);

        TextureAtlas atlas;
        int edgePadding;
        int imageSize;

        UiBorderType(int edgePadding, String atlasPath, int imageSize) {
            this.atlas = new TextureAtlas("ui/img/box/box.atlas");
            this.edgePadding = edgePadding;
            this.imageSize = imageSize;
        }
    }

    private ResourceManager rm;
    private UiBorderType type;

    private Table rootTable;
    public Table contentTable; //TODO maybe use Container?

    public UiBorderElement(ResourceManager rm, UiBorderType type){
        this.rm = rm;
        this.type = type;
        setUpTable();
    }

    private void setUpTable(){
        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setDebug(true); //TODO temp
        int textureSize = type.imageSize;

        rootTable.add(new Image(type.atlas.findRegion("upleft"))).size(textureSize,textureSize).padTop(type.edgePadding).padLeft(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion("up"))).fill().minSize(textureSize,textureSize).padTop(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion("upright"))).size(textureSize,textureSize).padTop(type.edgePadding).padRight(type.edgePadding);

        rootTable.row();

        rootTable.add(new Image(type.atlas.findRegion("left"))).minSize(textureSize,textureSize).expandY().fill().padLeft(type.edgePadding);

        contentTable = new Table();
        //rootTable.add(new Image(rm.boxMiddle)).minSize(rm.boxSize,rm.boxSize).expandY().fill();
        contentTable.setBackground(new TextureRegionDrawable(type.atlas.findRegion("middle")));
        rootTable.add(contentTable).fill();

        rootTable.add(new Image(type.atlas.findRegion("right"))).minSize(textureSize,textureSize).expandY().fill().padRight(type.edgePadding);

        rootTable.row();

        rootTable.add(new Image(type.atlas.findRegion("downleft"))).size(textureSize,textureSize).padLeft(type.edgePadding).padBottom(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion("down"))).fill().minSize(textureSize,textureSize).padBottom(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion("downright"))).size(textureSize,textureSize).padRight(type.edgePadding).padBottom(type.edgePadding);

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

    public void setContent(Actor actor){
        this.contentTable.clearChildren();
        this.contentTable.add(actor);
    }

    public Table getTable() {
        return rootTable;
    }
}
