package dk.mk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dk.mk.MainGame;
import helpers.GameInfo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//config.fullscreen = true;
		config.width = GameInfo.SCREEN_WIDTH;
		config.height = GameInfo.SCREEN_HEIGHT;
		config.foregroundFPS = 60;
		config.title = "Project Fragment";

		new LwjglApplication(new MainGame(), config);
	}
}
