package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.Entity;
import helpers.EntityType;

public class FloatingVendor extends Entity{

    Animation[] animation;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final float ANIMATION_TIMER_SWITCH_TIME = 0.15f;
    public static final int FVENDOR_WIDTH_PIXEL = 32;
    public static final int FVENDOR_HEIGHT_PIXEL = 64;
    public static final int NUMBER_OF_FRAMES = 2;

    private float fvendorTimer;
    private int currentFrame;
    private float stateTime;

    public FloatingVendor(World world, Vector2 pos) {
        super(world, EntityType.FVENDOR, pos);

        this.fvendorTimer = 0;
        this.currentFrame = 0;
        this.stateTime = 0f;

        setUpAnimation();
    }

    /** Sets up the animation for the entity. */
    public void setUpAnimation(){

        this.animation = new Animation[NUMBER_OF_FRAMES];
        TextureRegion[][] fvendorAnimationSpriteSheet = TextureRegion.split(new Texture("img/The Floating Vendor.png"), FVENDOR_WIDTH_PIXEL, FVENDOR_HEIGHT_PIXEL);

        for(int i = 0; i < NUMBER_OF_FRAMES; i++){
            animation[i] = new Animation(ANIMATION_SPEED, fvendorAnimationSpriteSheet[0][i]);
        }
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        updatePortalFrame(delta);
    }

    private void updatePortalFrame(float delta) {

        setFvendorTimer(getFvendorTimer() - Gdx.graphics.getDeltaTime());
        if(Math.abs(getFvendorTimer()) > ANIMATION_TIMER_SWITCH_TIME) {
            setFvendorTimer(0);
            if (currentFrame == NUMBER_OF_FRAMES - 1)
                currentFrame = 0;
            else
                currentFrame++;
        }
    }

    public void setFvendorTimer(float fvendorTimer) {
        this.fvendorTimer = fvendorTimer;
    }

    public float getFvendorTimer() {
        return fvendorTimer;
    }

    //TODO: THIS SHOULD BE MADE THE DEAULT.. AND NOT OVERWRITTEN
    @Override
    public void render(SpriteBatch batch) {
        batch.draw(getCurrentFrame(stateTime), getPos().x, getPos().y);
    }

    public TextureRegion getCurrentFrame(float stateTime){
        return (TextureRegion) animation[currentFrame].getKeyFrame(stateTime, true);
    }

    @Override
    public void dispose() {

    }
}
