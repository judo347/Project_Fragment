package world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import entities.elements.GroundTile;
import helpers.Entity;

import java.util.ArrayList;

public class GameMap {

    private ArrayList<Entity> entitiesList;
    private ArrayList<GroundTile> tilesList;

    //Get from MapLoader??
    private int mapWidth;
    private int mapHeight;

    protected String mapName; //TODO SHOULD BE UPGRADED TO MapTYPE? aka enum?

    public GameMap(String mapName, World world) {
        this.entitiesList = new ArrayList<>();
        this.tilesList = new ArrayList<>();
        MapLoader ml = new MapLoader();
        ml.loadLevelImage(mapName, entitiesList, tilesList, world);
        this.entitiesList = ml.getEntitiesList();
        this.tilesList = ml.getTilesList();
    }

    public void render (OrthographicCamera camera, SpriteBatch batch){
        for(Entity entity : entitiesList) {
            entity.render(batch);
        }

        for(GroundTile groundTile : tilesList){
            groundTile.render(batch);
        }
    }

    public ArrayList<Entity> getEntitiesList() {
        return entitiesList;
    }

    public ArrayList<GroundTile> getTilesList() {
        return tilesList;
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


}
