package world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import dk.mk.MainGame;
import entities.Player;
import entities.elements.Chest;
import entities.elements.GroundTile;
import entities.elements.Portal;
import helpers.ContactListen;
import helpers.Entity;
import helpers.EntityType;
import helpers.GameInfo;
import scenes.GameScene;

import java.util.ArrayList;

public class GameMap{

    private MainGame mainGame;
    private GameScene gameScene;
    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;
    private int playerIndex;
    private int portalIndex;

    private World world;
    private Texture background;
    //private ContactListen contactListener;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    //Get from MapLoader??
    private int mapWidth;
    private int mapHeight;

    //TODO Gdx.input.setInputProcessor() use this to get user input (controlers)

    //TODO: CAMRERA FOLLOWING PLAYER https://stackoverflow.com/questions/27240552/sprite-not-following-body-fixture


    protected String mapName; //TODO SHOULD BE UPGRADED TO MapTYPE? aka enum?

    public GameMap(String mapName, World world, MainGame mainGame, GameScene gameScene) {
        this.entitiesList = new ArrayList<>();
        this.tilesList = new ArrayList<>();
        this.mapName = mapName;
        this.world = world;
        this.mainGame = mainGame;
        this.gameScene = gameScene;

        //What we see on the screen
        this.box2DCamera = new OrthographicCamera();
        this.box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);
        this.box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT /2f, 0); //Pos of camera //TODO: Can we set to follow player on x-axis? maybe at another place?
        this.debugRenderer = new Box2DDebugRenderer();

        //Map loader
        MapLoader ml = new MapLoader();
        ml.loadLevelImage(mapName, this.world, this);
        this.entitiesList = ml.getEntitiesList();
        this.tilesList = ml.getTilesList();
        this.playerIndex = getPlayerIndex();
        this.portalIndex = getPortalIndex();


        world.setContactListener(new ContactListen(getPlayer(), getPortal(), this));
        background = new Texture("img/background.png");
    }

    public boolean isPlayerTouchingPortal(){

        float playerX = entitiesList.get(playerIndex).getX();
        float playerY = entitiesList.get(playerIndex).getY();
        int playerWidth = EntityType.PLAYER.getWidth();
        int playerHeight = EntityType.PLAYER.getHeight();

        float portalX = entitiesList.get(portalIndex).getX();
        float portalY = entitiesList.get(portalIndex).getY();
        int portalSize = Portal.PORTAL_SIZE_PIXEL;

        if(playerX + playerWidth/2 > portalX && playerX - playerWidth/2 < portalX + portalSize &&
                playerY < portalY + portalSize && playerY > portalY)
            return true;
        else
            return false;
    }

    private int getPortalIndex(){

        for(int i = 0; i < entitiesList.size(); i++){
            if(entitiesList.get(i).getDefaultTypeId() == "portal"){
                return i;
            }
        }

        return -1; //no player exists //TODO exception!!
    }

    private int getPlayerIndex(){

        for(int i = 0; i < entitiesList.size(); i++){
            if(entitiesList.get(i).getDefaultTypeId() == "player"){
                return i;
            }
        }

        return -1; //no player exists //TODO exception!!
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

        //TODO DEBUG RENDERER
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

    public Player getPlayer(){
        return (Player) entitiesList.get(playerIndex);
    }

    /** Returns the portal object from the entity list. Can be null. */
    public Portal getPortal(){
        return (Portal) entitiesList.get(portalIndex);
    }

    /** Opens a chest based on the unique id. Used by the contactListen class. */
    public void openChest(String chestId){

        for(Entity entity : entitiesList){
            if(entity.getId() == chestId){
                Chest chest = (Chest) entity;
                chest.openChest();
            }

        }
    }

    public void setScreenLevel(){
        //this.mainGame.setScreen(new LevelScene(mainGame, world));
        this.gameScene.changeLevel(GameScene.Level.LEVEL1);
    }
}
