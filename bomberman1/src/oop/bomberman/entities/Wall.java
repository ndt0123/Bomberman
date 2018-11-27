package oop.bomberman.entities;

public class Wall extends Entity {

    private final  int size = 40;

    public Wall(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Blocks\\SolidBlock.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }
}
