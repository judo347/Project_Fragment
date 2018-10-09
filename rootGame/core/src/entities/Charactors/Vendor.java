package entities.Charactors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import helpers.EntityType;
import helpers.StaticAnimatedProbType;

public class Vendor extends StaticAnimatedProb{

    public Vendor(World world, Vector2 pos) {
        super(world, pos, EntityType.VENDOR, StaticAnimatedProbType.FLOATING);
    }
}
