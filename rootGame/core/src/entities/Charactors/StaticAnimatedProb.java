package entities.Charactors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.Entity;
import helpers.EntityType;
import helpers.StaticAnimatedProbType;

public abstract class StaticAnimatedProb extends Entity{

    Animation[] animation;

    private StaticAnimatedProbType probType;

    private float animationTimer;
    private int currentFrame;
    private float stateTime;

    public StaticAnimatedProb(World world, Vector2 pos, EntityType entityType, StaticAnimatedProbType probType) {
        super(world, entityType, pos);

        //TODO: Why were these 3 missing?
        this.animationTimer = 0;
        this.currentFrame = 0;
        this.stateTime = 0f;

        body.getFixtureList().get(0).setSensor(true);

        this.probType = probType;
        setUpAnimation();
    }

    /** Sets up the animation for the entity. */
    public void setUpAnimation(){

        this.animation = new Animation[probType.getNumberOfFrames()];
        TextureRegion[][] vendorAnimationSpriteSheet = TextureRegion.split(probType.getTexture(), probType.getPixelWidth(), probType.getPixelHeight());

        for(int i = 0; i < probType.getNumberOfFrames(); i++){
            animation[i] = new Animation(probType.getAnimationSpeed(), vendorAnimationSpriteSheet[0][i]);
        }
    }

    @Override
    public void update(float delta) {
        stateTime += delta;
        updateFrame(delta);
    }

    private void updateFrame(float delta) {

        setAnimationTimer(getAnimationTimer() - Gdx.graphics.getDeltaTime());
        if(Math.abs(getAnimationTimer()) > probType.getAnimationTimerSwitchTime()) {
            setAnimationTimer(0);
            if (currentFrame == probType.getNumberOfFrames() - 1)
                currentFrame = 0;
            else
                currentFrame++;
        }
    }

    public void setAnimationTimer(float animationTimer) {
        this.animationTimer = animationTimer;
    }

    public float getAnimationTimer() {
        return animationTimer;
    }

    //TODO: THIS SHOULD BE MADE THE DEAULT.. AND NOT OVERWRITTEN
    @Override
    public void render(SpriteBatch batch, float delta) {
        batch.draw(getCurrentFrame(stateTime), getPos().x, getPos().y);
    }

    public TextureRegion getCurrentFrame(float stateTime){
        return (TextureRegion) animation[currentFrame].getKeyFrame(stateTime, true);
    }

    @Override
    public void dispose() {

    }
}
