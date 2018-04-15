package helpers;

import com.badlogic.gdx.physics.box2d.*;
import entities.Player;


public class ContactListen implements ContactListener {

    private Player player;

    public ContactListen(Player player) {
        this.player = player;
    }

    @Override
    public void beginContact(Contact contact) {

        System.out.println(contact.getFixtureA().getUserData() + " : " + contact.getFixtureB().getUserData());

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
