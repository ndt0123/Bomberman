package oop.bomberman.entities;

public class SpeedItem extends Entity {

    private final  int size = 30;
    public static int countTimeToLoseSpeedItem = 0;

    public SpeedItem(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Powerups\\SpeedPowerup.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }
}
