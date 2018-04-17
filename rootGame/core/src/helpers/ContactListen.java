package helpers;

import com.badlogic.gdx.physics.box2d.*;
import entities.Player;
import world.GameMap;


public class ContactListen implements ContactListener {

    private Player player;
    private GameMap gameMap;

    public ContactListen(Player player, GameMap gameMap) {
        this.player = player;
        this.gameMap = gameMap;
    }

    @Override
    public void beginContact(Contact contact) {


        //Chest is touched
        if(EntityType.CHEST.isMatchingDefaultId(contact.getFixtureA().getUserData().toString()) && contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){
            gameMap.openChest(contact.getFixtureA().getUserData().toString());
        }else if(EntityType.CHEST.isMatchingDefaultId(contact.getFixtureB().getUserData().toString()) && contact.getFixtureA().getUserData() == this.player.getDefaultTypeId()){
            gameMap.openChest(contact.getFixtureB().getUserData().toString());

        }



        //TODO Maybe update to check for the other object is a tile?
        if(contact.getFixtureA().getUserData() == this.player.getDefaultTypeId() || contact.getFixtureB().getUserData() == this.player.getDefaultTypeId()){
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
