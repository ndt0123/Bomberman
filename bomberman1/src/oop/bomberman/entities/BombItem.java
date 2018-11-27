package oop.bomberman.entities;

public class BombItem extends Entity {

    private final  int size = 30;
    public static int countTimeToLoseBombItem = 0;

    public BombItem(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Powerups\\BombPowerup.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }
}
