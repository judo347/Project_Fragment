package helpers;

import com.badlogic.gdx.physics.box2d.*;


public class ContactListen implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        System.out.println(contact.getFixtureA().getUserData());
    }

    @Override
    public void endContact(com.badlogic.gdx.physics.box2d.Contact contact) {

    }

    @Override
    public void preSolve(com.badlogic.gdx.physics.box2d.Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(com.badlogic.gdx.physics.box2d.Contact contact, ContactImpulse impulse) {

    }
}
