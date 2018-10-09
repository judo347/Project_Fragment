package entities.Charactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.Entity;
import helpers.EntityType;
import helpers.VendorType;

public class Vendor extends Entity{

    Animation[] animation;

    private VendorType vt; //VendorType

    private float vendorTimer;
    private int currentFrame;
    private float stateTime;

    public Vendor(World world, Vector2 pos, VendorType vendorType) {
        super(world, EntityType.VENDOR, pos);

        this.vt = vendorType;
        this.vendorTimer = 0;
        this.currentFrame = 0;
        this.stateTime = 0f;

        setUpAnimation();
    }

    /** Sets up the animation for the entity. */
    public void setUpAnimation(){

        this.animation = new Animation[vt.getNumberOfFrames()];
        TextureRegion[][] vendorAnimationSpriteSheet = TextureRegion.split(vt.getTexture(), vt.getPixelWidth(), vt.getPixelHeight());

        for(int i = 0; i < vt.getNumberOfFrames(); i++){
            animation[i] = new Animation(vt.getAnimationSpeed(), vendorAnimationSpriteSheet[0][i]);
        }
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        updateFrame(delta);
    }

    private void updateFrame(float delta) {

        setVendorTimer(getVendorTimer() - Gdx.graphics.getDeltaTime());
        if(Math.abs(getVendorTimer()) > vt.getAnimationTimerSwitchTime()) {
            setVendorTimer(0);
            if (currentFrame == vt.getNumberOfFrames() - 1)
                currentFrame = 0;
            else
                currentFrame++;
        }
    }

    public void setVendorTimer(float vendorTimer) {
        this.vendorTimer = vendorTimer;
    }

    public float getVendorTimer() {
        return vendorTimer;
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
