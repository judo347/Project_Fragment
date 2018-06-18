package helpers.GameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import helpers.GameInfo;

public class AnimatedObject extends RenderableObject {

    private Animation[] animation;
    private Texture texture;

    private final int NUMBER_OF_FRAMES;
    private final float ANIMATION_SPEED;

    private boolean isAnimationDirectionForward;
    private int currentFrame;
    private float stateTime;

    private final int FRAME_WIDTH;
    private final int FRAME_HEIGHT;

    /** The texture has to be images vertically placed besides each other. */
    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int frameWidth, int frameHeight, int numberOfFrames) {
        this( world, pos, bodyDef, fixtureDef, texture, frameWidth, frameHeight, numberOfFrames, 0.2f);
    }

    public AnimatedObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, Texture texture, int frameWidth, int frameHeight, int numberOfFrames, float animationSpeed) {
        super(world, pos, bodyDef, fixtureDef);

        this.FRAME_HEIGHT = frameHeight;
        this.FRAME_WIDTH = frameWidth;

        this.NUMBER_OF_FRAMES = numberOfFrames;
        this.ANIMATION_SPEED = animationSpeed;

        this.isAnimationDirectionForward = true;
        this.texture = texture;
        this.currentFrame = 0;
        this.stateTime = 0f;

        setUpAnimation();
    }

    /** Sets up the animation for the the object. */
    private void setUpAnimation(){

        this.animation = new Animation[NUMBER_OF_FRAMES];
        TextureRegion[][] resourceTextureSheet = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);

        for(int i = 0; i < NUMBER_OF_FRAMES; i++){
            animation[i] = new Animation(ANIMATION_SPEED, resourceTextureSheet[0][i]);
        }
    }

    /** Render the AnimationObject */
    @Override
    public void render(SpriteBatch batch, float delta){
        stateTime += delta;
        updateFrame();

        Vector2 pos = body.getPosition();
        float width = getCurrentFrame().getRegionWidth();
        float height = getCurrentFrame().getRegionHeight();

        batch.draw(getCurrentFrame(), pos.x * GameInfo.PPM - (width / 2), pos.y * GameInfo.PPM - (height / 2), width, height);
    }

    @Override
    public void dispose() {

    }

    /** Updates the current frame number */
    private void updateFrame() {

        if(stateTime > ANIMATION_SPEED){ //Has enough time passed to switch frame?

            if(isAnimationDirectionForward){

                if(currentFrame+1 == NUMBER_OF_FRAMES){ //Have we reached the end of animation?
                    currentFrame--;
                    isAnimationDirectionForward = false;
                }else
                    currentFrame++;

            }else{ //Going backwards in frames
                if(currentFrame == 0){ //Have we reached the end of animation?
                    currentFrame++;
                    isAnimationDirectionForward = true;
                }else
                    currentFrame--;
            }

            stateTime -= ANIMATION_SPEED;
        }
    }

    private TextureRegion getCurrentFrame(){
        return (TextureRegion) animation[currentFrame].getKeyFrame(stateTime, true); //TODO THIS MIGHT BE A BUG
    }
}
