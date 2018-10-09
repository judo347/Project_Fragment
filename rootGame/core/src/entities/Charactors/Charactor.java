package entities.Charactors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.Entity;
import helpers.EntityType;

public abstract class Charactor extends Entity{

    /** TODO hp and such? Animation? */

    public Charactor(World world, EntityType entityType, Vector2 pos) {
        super(world, entityType, pos);
    }
}
