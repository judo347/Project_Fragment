package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import scenes.MenuScene;
import ui.Ui;
import utilities.ResourceManager;

public class MainGame extends Game {

	SpriteBatch batch; //You only have one sprite batch in a game!
    Ui ui;
    ResourceManager rm;

	
	@Override
	public void create () {
	    rm = new ResourceManager();
		batch = new SpriteBatch();
		ui = new Ui(rm);
		setScreen(new MenuScene(this));
	}

	/** Gets called every frame. */
	@Override
	public void render () {
		super.render(); //Will pass render method to all other calls that implements screen.
		ui.render();
	}

    public SpriteBatch getBatch() {
        return this.batch;
    }
}
