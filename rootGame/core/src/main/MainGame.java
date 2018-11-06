package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import scenes.MenuScene;
import ui.PlayAround;
import ui.Ui;

public class MainGame extends Game {

	SpriteBatch batch; //You only have one sprite batch in a game!
    Ui ui;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ui = new Ui();
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
