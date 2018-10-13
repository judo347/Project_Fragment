package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import helpers.ItemType;
import utilities.GameInfo;

public abstract class Item{

    private World world;
    private Vector2 pos;
    private Body body;
    private ItemType itemType;
    private Sprite sprite;

    public Item(World world, Vector2 pos, ItemType itemType, Texture texture) {
        this.world = world;
        this.pos = pos;
        this.itemType = itemType;
        this.body = createBody(world, pos);
        this.sprite = new Sprite(texture);
    }

    /** Creates the body and fixture for the item. */
    private Body createBody(World world, Vector2 pos){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(((pos.x + itemType.getWidth() / 2) / GameInfo.PPM), (pos.y + itemType.getHeight() / 2) / GameInfo.PPM);

        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((itemType.getWidth() / 2) / GameInfo.PPM, (itemType.getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.setUserData(itemType.getId());

        fixture.setSensor(true);

        shape.dispose();

        return body;
    }

    public void update(float delta){
        this.sprite.setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }

    /** The method used to render the item. */
    public void render (SpriteBatch batch, float delta){
        update(delta);
        batch.draw(sprite, pos.x, pos.y, itemType.getWidth(), itemType.getHeight());
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
}
