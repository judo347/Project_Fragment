package utilities;

import com.badlogic.gdx.physics.box2d.*;
import entities.Charactors.Player;
import entities.Probs.Portal;
import helpers.EntityType;
import world.GameMap;


public class ContactListen implements ContactListener {

    private Player player;
    private Portal portal;
    private GameMap gameMap;

    private Object contactObjectAorPlayer; //Is always the player if he is in contact
    private Object contactObjectB;

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

    private void setUserDataFromContact(Contact contact){
        if(contact.getFixtureA().getUserData() == this.player.getDefaultTypeId()){
            this.contactObjectAorPlayer = contact.getFixtureA().getUserData();
            this.contactObjectB = contact.getFixtureB().getUserData();

        } else{
            this.contactObjectB = contact.getFixtureA().getUserData();
            this.contactObjectAorPlayer = contact.getFixtureB().getUserData();
        }
    }

    @Override
    public void beginContact(Contact contact) {

        setUserDataFromContact(contact);

        //Chest is touched
        if(contactObjectAorPlayer == this.player.getDefaultTypeId() && EntityType.CHEST.isMatchingDefaultId(contactObjectB.toString())){
            gameMap.openChest(contactObjectB.toString());
        }

        //Portal is touched
        if(EntityType.PORTAL.isMatchingDefaultId(contactObjectB.toString()) && contactObjectAorPlayer == this.player.getDefaultTypeId()){
            //todo do something
        }

        //Floating vendor is touched
        if(EntityType.VENDOR.isMatchingDefaultId(contactObjectB.toString()) && contactObjectAorPlayer == this.player.getDefaultTypeId()){
            //todo do something
        }

        /*
        //Item is touched
        if()*/


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
