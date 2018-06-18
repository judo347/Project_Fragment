package helpers.GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/** Used for renderable object. */
public abstract class RenderableObject extends GameObject {
    public RenderableObject(World world, Vector2 pos, BodyDef bodyDef, FixtureDef fixtureDef) {
        super(world, pos, bodyDef, fixtureDef);
    }

    public abstract void render(SpriteBatch batch, float delta);
    public abstract void dispose();
}
