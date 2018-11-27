package oop.bomberman.entities;

public class FlameItem extends Entity {

    private final  int size = 30;
    public static int countTimeToLoseFlameItem = 0;

    public FlameItem(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Powerups\\FlamePowerup.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }
}
