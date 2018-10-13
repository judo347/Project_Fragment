package Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ItemType;
import utilities.EntityDimensions;
import utilities.GameInfo;

import java.util.Random;

public abstract class Item{

    private World world;
    private Body body;
    private ItemType itemType;
    private Sprite sprite;
    private boolean hasBeenImplused; //Used when item is dropped;

    public Item(World world, Vector2 pos, ItemType itemType, Texture texture) {
        this.world = world;
        this.itemType = itemType;
        this.body = createBody(world, pos);
        this.sprite = new Sprite(texture);

        this.hasBeenImplused = false;
    }

    /** Creates the body and fixture for the item. */
    private Body createBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        //bodyDef.fixedRotation = true; //TODO Maybe add? and fix force?

        bodyDef.position.set(((pos.x + itemType.getWidth() / 2) / GameInfo.PPM), (pos.y + itemType.getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((itemType.getWidth() / 2) / GameInfo.PPM, (itemType.getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        fixtureDef.filter.maskBits = GameInfo.GROUNDTILE_CATAGORY_BITS; // Only collide with groundTile!

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(itemType.getId());

        fixture.setSensor(false);

        shape.dispose();

        return body;
    }

    public void update(float delta){
        this.sprite.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    /** The method used to render the item. */
    public void render (SpriteBatch batch, float delta){
        update(delta);
        batch.draw(this.sprite, sprite.getX() - (sprite.getWidth() / 2), sprite.getY() - (sprite.getHeight() / 2));
    }

    public abstract void dispose();

    //TODO GETTERS FOR THIS AND ITEMTYPE
    //TODO SET ID?


    public Body getBody() {
        return body;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void drop(Vector2 pos){
        if(!hasBeenImplused){

            //Pick a random upwards direction
            //Vector2 direction = new Vector2(0.00001f, 0);
            Vector2 direction = new Vector2(0, -0.00003f);
            Random random = new Random();
            direction.rotate(random.nextInt(180));

            System.out.println(direction.toString());

            hasBeenImplused = true;
            body.applyLinearImpulse(direction, new Vector2(pos.x, pos.y), true);
        }
    }
}
