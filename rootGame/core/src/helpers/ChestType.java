package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public enum ChestType {
    NORMAL("chest_normal_open", "chest_normal_closed"),
    LEGENDARY("chest_legendary_open", "chest_legendary_closed");

    private Sprite spriteOpen;
    private Sprite spriteClosed;
    private boolean isChestOpen;

    ChestType(String chestOpen, String chestClosed){
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("img/elements/chests.atlas"));

        this.spriteOpen = atlas.createSprite(chestOpen);
        this.spriteClosed = atlas.createSprite(chestClosed);
        this.isChestOpen = false;
    }

    public Sprite getSprite(){
        return isChestOpen ? spriteOpen : spriteClosed;
    }

    public void openChest(){
        this.isChestOpen = true;
    }

    public void dispose(){
        spriteOpen.getTexture().dispose();
        spriteClosed.getTexture().dispose();
    }
}
