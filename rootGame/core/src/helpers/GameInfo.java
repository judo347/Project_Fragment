package helpers;

public class GameInfo {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 800;
    public static final int TILE_SIZE = 32;
    public static final float PPM = (float)TILE_SIZE; //TILE_SIZE = 1unit? //PPM pixels per min //THIS ONE HAS TO BE A FLOAT!
    public static final float ZOOM = PPM/3f;
}
