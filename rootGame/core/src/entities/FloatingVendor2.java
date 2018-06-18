package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.GameObjects.AnimatedObject;

public class FloatingVendor2 extends AnimatedObject{

    private static final float ANIMATION_SPEED = 0.5f;
    private static final int FVENDOR_WIDTH_PIXEL = 32;
    private static final int FVENDOR_HEIGHT_PIXEL = 64;
    private static final int NUMBER_OF_FRAMES = 2;

    public FloatingVendor2(World world, Vector2 pos) {
        super(world, pos, DEFAULT_STATIC_BODYDEF, DEFAULT_STATIC_FIXTUREDEF, new Texture("img/The Floating Vendor.png"), FVENDOR_WIDTH_PIXEL, FVENDOR_HEIGHT_PIXEL, NUMBER_OF_FRAMES, ANIMATION_SPEED);
    }
}
