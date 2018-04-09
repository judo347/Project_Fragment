package sprites.elements;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ChestType;
import helpers.GameInfo;

//TODO Draw method that does all. Like texture, x and y.
public class Chest {

    private World world;
    private Body body;
    private ChestType type;
    private float x;
    private float y;
    private String userData = "Chest";

    private boolean isChestOpen;

    public Chest(World world, ChestType type, float x, float y) {
        this.world = world;
        this.type = type;
        this.isChestOpen = false;
        this.x = x;
        this.y = y;
        getSprite().setPosition(x, y);
        createBody();
    }

    void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / GameInfo.PPM, y / GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getSprite().getWidth() / 2) / GameInfo.PPM, (getSprite().getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(userData);
        fixture.setSensor(true); //Allows pass through and contact

        shape.dispose();
    }

    public Sprite getSprite(){
        return isChestOpen ? type.getSpriteOpen() : type.getSpriteClosed();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getUserData() {
        return userData;
    }

    public void openChest(){
        this.isChestOpen = true;
    }
}
