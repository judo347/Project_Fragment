package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import scenes.MenuScene;
import ui.PlayAround;

public class MainGame extends Game {

	SpriteBatch batch; //You only have one sprite batch in a game!

	Stage stage;
	Table table;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScene(this));

		stage = new Stage();

		//Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		table.setDebug(true);

		//table.add(new Dialog("hello", new Window.WindowStyle()));
		//table.add(new Dialog("hello", new Window.WindowStyle()));
        Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        final TextButton button = new TextButton("Click Me!", skin, "default");
	    table.add(button);
	}

	/** Gets called every frame. */
	@Override
	public void render () {
		super.render(); //Will pass render method to all other calls that implements screen.

		stage.act();
		stage.draw();
	}

    public SpriteBatch getBatch() {
        return this.batch;
    }
}
