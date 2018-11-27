package oop.bomberman.entities;

public class Portal extends Entity {
    private final  int size = 40;

    public Portal(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Blocks\\Portal.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }


}
