package entities.elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import helpers.GameInfo;
import helpers.GameObjects.AnimatedObject;

public class Portal2 extends AnimatedObject{

    private static final int PORTAL_SIZE_PIXEL = 128;
    private static final int NUMBER_OF_FRAMES = 4;

    public Portal2(World world, Vector2 pos) {
        super(world, pos, DEFAULT_STATIC_BODYDEF, createPortalFixtureDef(), new Texture("img/elements/portal/portalx2.png"), PORTAL_SIZE_PIXEL, PORTAL_SIZE_PIXEL, NUMBER_OF_FRAMES, 0.5f);
    }

    /** The default fixturedef for players */
    private static FixtureDef createPortalFixtureDef(){

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((PORTAL_SIZE_PIXEL / 2) / GameInfo.PPM, (PORTAL_SIZE_PIXEL / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0;

        return fixtureDef;

    }
}
