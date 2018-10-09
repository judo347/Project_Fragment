package helpers;

import com.badlogic.gdx.graphics.Texture;
import utilities.EntityDimensions;

public enum StaticAnimatedProbType {
    FLOATING(EntityDimensions.VENDOR_DIMENSIONS.getWIDTH(), EntityDimensions.VENDOR_DIMENSIONS.getHEIGHT(), 2, "The Floating Vendor.png"),
    PORTAL(EntityDimensions.PORTAL_DIMENSIONS.getWIDTH(), EntityDimensions.PORTAL_DIMENSIONS.getHEIGHT(), 4, "elements/portal/portalx2.png"),
    CRAFTING_TABLE(EntityDimensions.CRAFTING_TABLE_DIMENSIONS.getWIDTH(), EntityDimensions.CRAFTING_TABLE_DIMENSIONS.getHEIGHT(), 2, "elements/craftingTable/craftingTable.png");


    private int pixelWidth;
    private int pixelHeight;
    private int numberOfFrames; //TODO Could be detected with/from an atlas file or other means.
    private float animationSpeed;
    private float animationTimerSwitchTime;

    private Texture texture;

    StaticAnimatedProbType(int pixelWidth, int pixelHeight, int numberOfFrames, String textureName) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationSpeed = 0.5f;
        this.animationTimerSwitchTime = 0.15f;
        this.texture = new Texture("img/" + textureName);
    }

    StaticAnimatedProbType(int pixelWidth, int pixelHeight, int numberOfFrames, float animationSpeed,
                           float animationTimerSwitchTime, String textureName) {
        this.pixelWidth = pixelWidth;
        this.pixelHeight = pixelHeight;
        this.numberOfFrames = numberOfFrames;
        this.animationSpeed = animationSpeed;
        this.animationTimerSwitchTime = animationTimerSwitchTime;
        this.texture = new Texture("img/" + textureName);
    }


    public int getPixelWidth() {
        return pixelWidth;
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public float getAnimationSpeed() {
        return animationSpeed;
    }

    public float getAnimationTimerSwitchTime() {
        return animationTimerSwitchTime;
    }

    public Texture getTexture() {
        return texture;
    }
}
