package helpers.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import helpers.GameInfo;

public class TextureObject extends RenderableObject {

    protected TextureRegion textureRegion;

    /** An GameObject that always have a texture drawn at the body's position. */
    public TextureObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef, TextureRegion textureRegion) {
        super(world, pos, bodyDef, fixtureDef);
        this.textureRegion = textureRegion;
    }

    /** Render the TextureObjects texture at the body's position. */
    @Override
    public void render(SpriteBatch batch, float delta) {
        if (textureRegion != null) {
            Vector2 pos = body.getPosition();
            float width = textureRegion.getRegionWidth();
            float height = textureRegion.getRegionHeight();
            batch.draw(textureRegion, pos.x * GameInfo.PPM - (width / 2), pos.y * GameInfo.PPM - (height / 2), width, height);        }
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public void dispose(){
        this.textureRegion.getTexture().dispose();
    }
}
