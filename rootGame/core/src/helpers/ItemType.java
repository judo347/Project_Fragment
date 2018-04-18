package helpers;

import com.badlogic.gdx.physics.box2d.*;

public enum ItemType {

    WEAPON("weapon", 0, 0),
    ARMOR("armor", 0, 0),
    CURRENCY("currency", 0, 0);

    private String id;
    private int width, height;

    private ItemType(String id, int width, int height){
        this.id = id;
        this.width = width;
        this.height = height;
    }

    /** Creates the body and fixture for an item. */
    public Body createBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(((x + getWidth() / 2) / GameInfo.PPM), (y + getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2) / GameInfo.PPM, (height / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(id);

        fixture.setSensor(true);

        shape.dispose();

        return body;
    }

    public static ItemType getItemTypeFromString(String string){
        string.toUpperCase();

        if(string == WEAPON.toString())
            return WEAPON;
        if(string == ARMOR.toString())
            return ARMOR;
        if(string == CURRENCY.toString())
            return CURRENCY;

        return null;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
