package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import entities.elements.GroundTile;
import helpers.Entity;

import java.util.ArrayList;

public class GameMap{

    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;
    private int playerIndex;

    //Get from MapLoader??
    private int mapWidth;
    private int mapHeight;

    protected String mapName; //TODO SHOULD BE UPGRADED TO MapTYPE? aka enum?

    public GameMap(String mapName, World world) {
        this.entitiesList = new ArrayList<>();
        this.tilesList = new ArrayList<>();
        this.mapName = mapName;
        MapLoader ml = new MapLoader();
        ml.loadLevelImage(mapName, world);
        this.entitiesList = ml.getEntitiesList();
        this.tilesList = ml.getTilesList();
        getPlayerIndex();
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
    public void render (OrthographicCamera camera, SpriteBatch batch){
        for(Entity entity : entitiesList) {
            entity.render(batch);
        }

        for(GroundTile groundTile : tilesList){
            groundTile.render(batch);
        }
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
}
