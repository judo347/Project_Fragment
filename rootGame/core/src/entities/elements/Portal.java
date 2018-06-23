package entities.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.Entity;
import helpers.EntityType;

public class Portal extends Entity{

    Animation[] animation;
    public static final float ANIMATION_SPEED = 0.5f;
    public static final float ANIMATION_TIMER_SWITCH_TIME = 0.15f;
    public static final int PORTAL_SIZE_PIXEL = 128;
    public static final int NUMBER_OF_FRAMES = 4;

    private float portalTimer;
    private int currentFrame;
    private float stateTime;

    public Portal(World world, Vector2 pos) {
        super(world, EntityType.PORTAL, pos);

        this.portalTimer = 0;
        this.currentFrame = 0;
        this.stateTime = 0f;

        setUpAnimation();
    }

    /** Sets up the animation for the entity. */
    public void setUpAnimation(){

        this.animation = new Animation[NUMBER_OF_FRAMES];
        TextureRegion[][] portalAnimationSpriteSheet = TextureRegion.split(new Texture("img/elements/portal/portalx2.png"), PORTAL_SIZE_PIXEL, PORTAL_SIZE_PIXEL);

        for(int i = 0; i < NUMBER_OF_FRAMES; i++){
            animation[i] = new Animation(ANIMATION_SPEED, portalAnimationSpriteSheet[0][i]);
        }
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        updatePortalFrame(delta);
    }

    private void updatePortalFrame(float delta) {

        setPortalTimer(getPortalTimer() - Gdx.graphics.getDeltaTime());
        if(Math.abs(getPortalTimer()) > ANIMATION_TIMER_SWITCH_TIME){
            setPortalTimer(0);
            if(currentFrame == NUMBER_OF_FRAMES - 1)
                currentFrame = 0;
            else
                currentFrame++;
        }

    }

    public void setPortalTimer(float portalTimer) {
        this.portalTimer = portalTimer;
    }

    public float getPortalTimer() {
        return portalTimer;
    }

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
