package entities.Probs;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import entities.Charactors.StaticAnimatedProb;
import helpers.EntityType;
import helpers.StaticAnimatedProbType;

public class CraftingTable extends StaticAnimatedProb{

    public CraftingTable(World world, Vector2 pos) {
        super(world, pos, EntityType.CRAFTING_TABLE, StaticAnimatedProbType.CRAFTING_TABLE);
    }
}
