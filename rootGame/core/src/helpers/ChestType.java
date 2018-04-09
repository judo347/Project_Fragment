package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public enum ChestType {
    NORMAL("chest_normal_open", "chest_normal_closed"),
    LEGENDARY("chest_legendary_open", "chest_legendary_closed");

    private Sprite spriteOpen;
    private Sprite spriteClosed;

    ChestType(String chestOpen, String chestClosed){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/elements/chests.atlas"));

        this.spriteOpen = atlas.createSprite(chestOpen);
        this.spriteClosed = atlas.createSprite(chestClosed);

        //TextureAtlas.AtlasRegion atlasRegion = atlas.findRegion(chestOpen);
        //this.sprite = atlas.createSprite(chest);
        }

    public Sprite getSpriteOpen() {
        return spriteOpen;
    }

    public Sprite getSpriteClosed() {
        return spriteClosed;
    }
}
