package ui.elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import utilities.ResourceManager;
import ui.elements.UiBorderElement.UiBorderElementType.*;

public class UiBorderElement {

    enum UiBorderElementType{
        //TODO Atlas loading should come from RM
        BACKGROUND(30, new TextureAtlas("ui/img/box/box.atlas")),
        INVENTORY_SLOT(15, new TextureAtlas("ui/img/inventorySlot/uiInventorySlot.atlas"));

        private int edgePadding;
        private TextureAtlas atlas;

        UiBorderElementType(int edgePadding, TextureAtlas atlas) {
            this.edgePadding = edgePadding;
            this.atlas = atlas;
        }

        public int getEdgePadding() {
            return edgePadding;
        }

        public TextureAtlas getAtlas() {
            return atlas;
        }

        enum UiBorderImageDirection{
            TOPLEFT("topleft"), TOP("top"), TOPRIGHT("topright"),
            RIGHT("right"), DOWNRIGHT("downright"), DOWN("down"),
            DOWNLEFT("downleft"), LEFT("left"), MIDDLE("middle");

            private String atlasKeyword;

            UiBorderImageDirection(String atlasKeyword) {
                this.atlasKeyword = atlasKeyword;
            }

            public String getAtlasKeyword() {
                return atlasKeyword;
            }
        }
    }

    private ResourceManager rm;
    private UiBorderElementType type;

    private Table rootTable;
    private Table contentTable; //TODO maybe use Container?

    public UiBorderElement(ResourceManager rm, UiBorderElementType type){
        this.rm = rm;
        this.type = type;
        setUpTable();
    }

    private void setUpTable(){
        rootTable = new Table();
        rootTable.setFillParent(true);
        rootTable.setDebug(true); //TODO temp
        int textureSize = type.atlas.findRegion(UiBorderImageDirection.TOPLEFT.getAtlasKeyword()).getRegionWidth();

        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.TOPLEFT.getAtlasKeyword()))).size(textureSize,textureSize).padTop(type.edgePadding).padLeft(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.TOP.getAtlasKeyword()))).fill().minSize(textureSize,textureSize).padTop(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.TOPRIGHT.getAtlasKeyword()))).size(textureSize,textureSize).padTop(type.edgePadding).padRight(type.edgePadding);

        rootTable.row();

        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.LEFT.getAtlasKeyword()))).minSize(textureSize,textureSize).expandY().fill().padLeft(type.edgePadding);

        contentTable = new Table();
        //rootTable.add(new Image(rm.boxMiddle)).minSize(rm.boxSize,rm.boxSize).expandY().fill();
        contentTable.setBackground(new TextureRegionDrawable(type.atlas.findRegion(UiBorderImageDirection.MIDDLE.getAtlasKeyword())));
        rootTable.add(contentTable).fill();

        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.RIGHT.getAtlasKeyword()))).minSize(textureSize,textureSize).expandY().fill().padRight(type.edgePadding);

        rootTable.row();

        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.DOWNLEFT.getAtlasKeyword()))).size(textureSize,textureSize).padLeft(type.edgePadding).padBottom(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.DOWN.getAtlasKeyword()))).fill().minSize(textureSize,textureSize).padBottom(type.edgePadding);
        rootTable.add(new Image(type.atlas.findRegion(UiBorderImageDirection.DOWNRIGHT.getAtlasKeyword()))).size(textureSize,textureSize).padRight(type.edgePadding).padBottom(type.edgePadding);

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
