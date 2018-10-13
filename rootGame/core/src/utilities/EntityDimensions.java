package utilities;

public class EntityDimensions {

    public static final Dimensions PLAYER_DIMENSIONS = new Dimensions(32, 64);
    public static final Dimensions CHEST_DIMENSIONS = new Dimensions(40, 30);
    public static final Dimensions PORTAL_DIMENSIONS = new Dimensions(128, 128);
    public static final Dimensions VENDOR_DIMENSIONS = new Dimensions(32, 64);
    public static final Dimensions CRAFTING_TABLE_DIMENSIONS = new Dimensions(128, 96);

    public static final int ITEM_PIXEL_SIZE = 16;


    public static class Dimensions{

        private final int WIDTH;
        private final int HEIGHT;

        public Dimensions(int width, int height) {
            this.WIDTH = width;
            this.HEIGHT = height;
        }

        public int getWIDTH() {
            return WIDTH;
        }

        public int getHEIGHT() {
            return HEIGHT;
        }
    }
}
