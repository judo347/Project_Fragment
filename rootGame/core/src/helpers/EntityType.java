package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import entities.FloatingVendor;
import entities.Player;
import entities.elements.Chest;
import entities.elements.Portal;
import world.GameMap;

public enum EntityType {

    PLAYER("player", 32, 64, BodyType.DynamicBody, Constants.PLAYER_COLOR),
    CHEST("chest", 40, 30, BodyType.StaticBody, Constants.CHEST_COLOR),
    PORTAL("portal", 128, 128, BodyType.StaticBody, Constants.PORTAL_COLOR),
    FVENDOR("fvendor", 32, 64, BodyType.StaticBody, Constants.FVENDOR_COLOR);

    private String id;
    private int width, height;
    private BodyType bodyType;
    private Color color;


    private EntityType(String id, int width, int height, BodyType bodyType, Color color) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.bodyType = bodyType;
        this.color = color;
    }

    /** Creates the body and fixture for an entity. */
    public Body createBody(World world, Vector2 pos){

        if(this == PLAYER)
            return createPlayerBody(world, pos);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;

        bodyDef.position.set(((pos.x + getWidth() / 2) / GameInfo.PPM), (pos.y + getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2) / GameInfo.PPM, (height / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(id);

        if(this == CHEST || this == PORTAL || this == FVENDOR){
            fixture.setSensor(true);
        }

        shape.dispose();

        return body;
    }

    /** Creates the body for the player. */
    private Body createPlayerBody(World world, Vector2 pos){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;

        bodyDef.fixedRotation = true;
        bodyDef.position.set(((pos.x + getWidth() / 2) / GameInfo.PPM), (pos.y + getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2) / GameInfo.PPM, (height / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(id);

        if(this == CHEST || this == PORTAL || this == FVENDOR){
            fixture.setSensor(true);
        }

        shape.dispose();

        return body;
    }

    /** Returns a new entity based on the color. Will return null if color is not matching a type.
     * @param color the color to search for.
     * @param world the world to place entity in.
     * @param pos the coordinates
     * @return an entity object matching the color given. Will return null if color is not matching a type. */
    public static Entity getEntity(Color color, World world, Vector2 pos, GameMap gameMap){

        switch (getTypeFromColor(color)){
            case PLAYER:    return new Player(gameMap, world, pos);
            case CHEST:     return new Chest(world, pos); //TODO SHOULD BE ABLE TO HANDLE MORE THAN ONE COLOR
            case PORTAL:    return new Portal(world, pos);
            case FVENDOR:   return new FloatingVendor(world, pos);
        }

        return null;
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a entityType based on the color given. Returns null if non matches. */
    public static EntityType getTypeFromColor(Color color){

        if(color.equals(EntityType.PLAYER.getColor()))
            return PLAYER;
        if(color.equals(EntityType.CHEST.getColor()))
            return CHEST;
        if(color.equals(EntityType.PORTAL.getColor()))
            return PORTAL;
        if(color.equals(EntityType.FVENDOR.getColor()))
            return FVENDOR;
        else
            return null;
    }

    /** The color used by this type. */
    private static class Constants{
        public static final Color PLAYER_COLOR = Color.valueOf("#26ffff00");
        public static final Color CHEST_COLOR = Color.valueOf("#D800FFFF");
        public static final Color PORTAL_COLOR = Color.valueOf("#00ffffae");
        public static final Color FVENDOR_COLOR = Color.valueOf("#006effff"); //TODO

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

    public BodyType getBodyType() {
        return bodyType;
    }

    public Color getColor() {
        return color;
    }

    /** Take a string and tries to match it with the default id. */
    public boolean isMatchingDefaultId(String diffId){
        return diffId.startsWith(id);
    }
}
