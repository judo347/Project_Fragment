package entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import utilities.GameInfo;
import helpers.TileType;

public class GroundTile extends Sprite {

    private World world;
    private Body body;
    private TileType tileType;
    private boolean hasBody;
    public static final String id = "gorund";

    //TODO: Should contain spriteSheet to allow check for border grass

    public GroundTile(World world, TileType tileType, Vector2 pos, boolean hasBody){
        //super(tileType.getTexture()); //TODO: Should the tileType contain Sprite?
        super(tileType.getPseudoRandomTexture()); //TODO: Should the tileType contain Sprite?
        this.world = world;
        this.tileType = tileType;
        this.hasBody = hasBody;
        setPosition(pos.x, pos.y);
        if(hasBody) //Dummy tiles //Could also be tileType != TileType.BRICK
            createBody();



    }

    void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(((getX() + getWidth() / 2) / GameInfo.PPM), (getY() + getHeight() / 2) / GameInfo.PPM);

        //Add the body to the world
        body = world.createBody(bodyDef);

        //Collision box
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2) / GameInfo.PPM, (getHeight() / 2) / GameInfo.PPM);

        //Contains mass and such
        FixtureDef fixtureDef = new FixtureDef(); //
        fixtureDef.shape = shape;
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = GameInfo.GROUNDTILE_CATAGORY_BITS;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(id); //Can we called on contact
        //fixture.setSensor(true); //Detect collision but elements can pass through it.

        shape.dispose(); //It is no longer needed/used
    }

    public void render(SpriteBatch batch){
        batch.draw(getTexture(), getX(), getY(), GameInfo.TILE_SIZE, GameInfo.TILE_SIZE);
    }

    public void dispose(){
        tileType.dispose();
    }

    public String getId() {
        return id;
    }
}
