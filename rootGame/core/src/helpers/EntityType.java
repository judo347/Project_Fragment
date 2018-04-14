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
    private Class loaderClass;
    private int width, height;
    private BodyType bodyType;
    private Color color;


    private EntityType(String id, int width, int height, BodyType bodyType, Color color) {
        this.id = id;
        //this.loaderClass = loaderClass;
        this.width = width;
        this.height = height;
        this.bodyType = bodyType;
        this.color = color;
    }

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

    /*
    private static HashMap<String, EntityType>() entityTypes;

    static {
        e
    } */

    public static Entity getEntity(Color color, World world, int x, int y){

        switch (getTypeFromColor(color)){
            case PLAYER:    return new Player(world, x, y);
            case CHEST:     return new Chest(world, x, y); //TODO SHOULD BE ABLE TO HANDLE MORE THAN ONE COLOR
        }

        return null;
    }

    public static EntityType getTypeFromColor(Color color){

        System.out.println("Entity GetType color = " + color.toString());

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

    private static class Constants{
        public static final Color PLAYER_COLOR = Color.valueOf("#26ffff00"); //TODO
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