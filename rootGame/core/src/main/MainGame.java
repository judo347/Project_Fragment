package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import scenes.MenuScene;

public class MainGame extends Game {

	SpriteBatch batch; //You only have one sprite batch in a game!
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScene(this));
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
