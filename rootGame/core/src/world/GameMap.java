package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import entities.elements.GroundTile;
import helpers.Entity;
import helpers.GameInfo;

import java.util.ArrayList;

public class GameMap{

    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;
    private int playerIndex;

    private World world;
    private Texture background;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    //Get from MapLoader??
    private int mapWidth;
    private int mapHeight;

    protected String mapName; //TODO SHOULD BE UPGRADED TO MapTYPE? aka enum?

    public GameMap(String mapName, World world) {
        this.entitiesList = new ArrayList<>();
        this.tilesList = new ArrayList<>();
        this.mapName = mapName;
        this.world = world;

        //What we see on the screen
        this.box2DCamera = new OrthographicCamera();
        this.box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        this.box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT /2f, 0); //Pos of camera //TODO: Can we set to follow player on x-axis? maybe at another place?
        this.debugRenderer = debugRenderer = new Box2DDebugRenderer();

        //Map loader
        MapLoader ml = new MapLoader();
        ml.loadLevelImage(mapName, this.world);
        this.entitiesList = ml.getEntitiesList();
        this.tilesList = ml.getTilesList();
        getPlayerIndex();

        background = new Texture("img/background.png");
    }

    private void getPlayerIndex(){

        for(int i = 0; i < entitiesList.size(); i++){
            if(entitiesList.get(i).getId() == "player"){
                playerIndex = i;
                i = entitiesList.size(); //todo: Does this break out of for loop or cause exception?
            }
        }
    }

    /** Updates game elements, like player movement and spirte/animation. */
    public void updateElements(float delta){
        for(Entity entity : entitiesList){
            entity.update(delta);
        }
    }

    /** Renders all elements in this map. */
    public void render (SpriteBatch batch, float delta){

        //getBox2DCamera(); was argument, not used in any way.

        updateElements(delta);


        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clears the screen

        batch.begin();
        batch.draw(background,0,0);

        for(Entity entity : entitiesList) {
            entity.render(batch);
        }

        for(GroundTile groundTile : tilesList){
            groundTile.render(batch);
        }

        //TODO: This if statement should be move to player
        //game.getBatch().draw((player.isInAir) ? player.getJumpSprite(stateTime) : player.getVerticalSprite(stateTime), player.getX() - (player.getWidth() / 2), player.getY() - (player.getHeight() / 2));

        batch.end();

        debugRenderer.render(world, box2DCamera.combined); //Render what the camera sees

        //How many times to calculate physics in one second // 1/60f wil calculate physics 60 times each second // Gdx.graphics.getDeltaTime() = calculate every frame.
        // 2nd and 3rd param is collision between elements, they determine of precise they are. Higher = more precise
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    /** Disposes used textures. */
    public void dispose(){

        for(Entity entity : entitiesList) {
            entity.dispose();
        }

        for(GroundTile groundTile : tilesList){
            groundTile.dispose();
        }
    }

    public ArrayList<Entity> getEntitiesList() {
        return entitiesList;
    }

    public ArrayList<GroundTile> getTilesList() {
        return tilesList;
    }

    public World getWorld() {
        return world;
    }

    public OrthographicCamera getBox2DCamera() {
        return box2DCamera;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }
}
