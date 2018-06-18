package helpers;

import com.badlogic.gdx.physics.box2d.*;
import entities.Player;
import entities.elements.Portal;
import world.GameMap;


public class ContactListen implements ContactListener {

    private Player player;
    private Portal portal;
    private GameMap gameMap;

    public ContactListen(Player player, Portal portal, GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
        this.portal = portal;
    }

    /** TODO Should every map not have a portal??  */
    public ContactListen(Player player, GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
        this.portal = null;

    }

    @Override
    public void beginContact(Contact contact) {


        //Chest is touched
        if(EntityType.CHEST.isMatchingDefaultId(contact.getFixtureA().getUserData().toString()) && contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){
            gameMap.openChest(contact.getFixtureA().getUserData().toString());
        }else if(EntityType.CHEST.isMatchingDefaultId(contact.getFixtureB().getUserData().toString()) && contact.getFixtureA().getUserData() == this.player.getDefaultTypeId()){
            gameMap.openChest(contact.getFixtureB().getUserData().toString());
        }

        //Portal is touched
        if(EntityType.PORTAL.isMatchingDefaultId(contact.getFixtureA().getUserData().toString()) && contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){
            //todo do something
        }else if(EntityType.PORTAL.isMatchingDefaultId(contact.getFixtureB().getUserData().toString()) && contact.getFixtureA().getUserData() == this.player.getDefaultTypeId()){
            //todo do the same thing
        }

        //Floating vendor is touched
        if(EntityType.FVENDOR.isMatchingDefaultId(contact.getFixtureA().getUserData().toString()) && contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){
            //todo do something
        }else if(EntityType.FVENDOR.isMatchingDefaultId(contact.getFixtureB().getUserData().toString()) && contact.getFixtureA().getUserData() == this.player.getDefaultTypeId()){
            //todo do the same thing
        }


        //TODO Maybe update to check for the other object is a tile?
        if(contact.getFixtureA().getUserData() == this.player.getDefaultTypeId() || contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){

        }

        if(contact.getFixtureA().getUserData() == this.player.getDefaultTypeId() || contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){

            //GROUND //TODO not working. Maybe check if player x,y is at portal at e press
            if(contact.getFixtureA().getUserData() == "ground" || contact.getFixtureB().getUserData() == "ground")
                this.player.isInAir = false;
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
