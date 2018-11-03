package utilities;

import Items.Consumable;
import Items.Item;
import com.badlogic.gdx.physics.box2d.*;
import entities.Charactors.Player;
import entities.Entity;
import entities.GroundTile;
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
        //If there is a player in contact - contactObjectA is the palyer.

        //Is the contact with the player?
        if(contactObjectAorPlayer == this.player.getDefaultTypeId()){

            //Chest is touched
            if(EntityType.CHEST.isMatchingDefaultId(contactObjectB.toString()))
                gameMap.openChest(contactObjectB.toString());

            //Portal is touched
            if(EntityType.PORTAL.isMatchingDefaultId(contactObjectB.toString())){
                //todo do something
            }

            //Floating vendor is touched
            if(EntityType.VENDOR.isMatchingDefaultId(contactObjectB.toString())){
                //todo do something
            }

            //Item is touched
            if(contactObjectB instanceof Item){
                System.out.println("ITEM TOUCHED");
                gameMap.addItemToPlayerPickup((Item)contactObjectB);
            }
        }







        //Feet is touching Ground
        if(contactObjectAorPlayer.toString().equals(Player.FEET_ID) && contactObjectB.toString().equals(GroundTile.id)
                || contactObjectB.toString().equals(Player.FEET_ID)  && contactObjectAorPlayer.toString().equals(GroundTile.id))
            this.player.isInAir = false;

        //System.out.println(contactObjectAorPlayer);
        //System.out.println(contactObjectB);
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
