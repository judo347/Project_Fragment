package dk.mk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import scenes.MainScene;
import scenes.MenuScene;

public class MainGame extends Game {

	SpriteBatch batch; //You only have one sprite batch in a game!
    World world;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,-9.8f), true); //Creating a world with gravity, true allows sleep = Dont calculate when nothing happens to elements.
		setScreen(new MenuScene(this, world));
	}

	/** Gets called every frame. */
	@Override
	public void render () {
		super.render(); //Will pass render method to all other calls that implements screen.
	}

    public SpriteBatch getBatch() {
        return this.batch;
    }
}
