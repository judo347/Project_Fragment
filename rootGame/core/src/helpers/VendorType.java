package helpers;

import com.badlogic.gdx.graphics.Texture;

public enum VendorType {
    FLOATING(32, 64, 2, 0.5f, 0.15f, "The Floating Vendor.png");


    private int pixelWidth;
    private int pixelHeight;
    private int numberOfFrames; //TODO Could be detected with/from an atlas file or other means.
    private float animationSpeed;
    private float animationTimerSwitchTime;

    private Texture texture;

    VendorType(int pixelWidth, int pixelHeight, int numberOfFrames, float animationSpeed,
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
