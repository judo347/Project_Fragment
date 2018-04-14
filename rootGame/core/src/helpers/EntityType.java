package helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import entities.Player;
import entities.elements.Chest;

public enum EntityType {

    PLAYER("player", 32, 64, BodyType.DynamicBody, Constants.PLAYER_COLOR),
    CHEST("chest", 40, 30, BodyType.StaticBody, Constants.CHEST_COLOR);

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

    /** Creates the body and fixture for a entity. */
    public Body createBody(World world, float x, float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(((x + getWidth() / 2) / GameInfo.PPM), (y + getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2) / GameInfo.PPM, (height / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(id);

        if(this == CHEST){
            fixture.setSensor(true);
        }

        shape.dispose();

        return body;
    }

    /* TODO: This could and maybe should be used to map a color to a type. Usefull?
    private static HashMap<String, EntityType>() entityTypes;

    static {
        e
    } */

    /** Returns a new entity based on the color. Will return null if color is not matching a type.
     * @param color the color to search for.
     * @param world the world to place entity in.
     * @param x coordinate
     * @param y coordinate
     * @return an entity object matching the color given. Will return null if color is not matching a type. */
    public static Entity getEntity(Color color, World world, int x, int y){

        switch (getTypeFromColor(color)){
            case PLAYER:    return new Player(world, x, y);
            case CHEST:     return new Chest(world, x, y); //TODO SHOULD BE ABLE TO HANDLE MORE THAN ONE COLOR
        }

        return null;
    }

    /** Takes a color and returns a matching type. Returns null of non matches.
     *  @param color a color to be matched.
     *  @return a entityType based on the color given. Returns null if non matches. */
    public static EntityType getTypeFromColor(Color color){

        if(color.equals(EntityType.PLAYER.getColor())){
            System.out.println("Type = PLAYER");
            return PLAYER;
        }
        if(color.equals(EntityType.CHEST.getColor())){
            System.out.println("Type = chest");
            return CHEST;
        }
        else
            return null;
    }

    /** The color used by this type. */
    private static class Constants{
        public static final Color PLAYER_COLOR = Color.valueOf("#26ffff00");
        public static final Color CHEST_COLOR = Color.valueOf("#D800FFFF");
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
}
