package oop.bomberman.entities;

import java.awt.*;
import static oop.bomberman.window.GameJPanel.*;

public class Brick extends Entity{
    private final int size = 40;

    public Brick(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Blocks\\ExplodableBlock.png");
        getImageDimention();
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof Brick && o != null) {
            Brick br = (Brick) o;
            if(x==br.x && y==br.y) {
                return true;
            }
        }
        return false;
    }

    //xóa brick khi va chạm với flame
    public static void updateBrick() {
        for( int i=0; i<bricks.size(); i++) {
            Rectangle brickBound = new Rectangle(bricks.get(i).getX()+15, bricks.get(i).getY()+15, 10, 10);
            for( int j=0; j<flames.size(); j++) {
                if(brickBound.intersects(flames.get(j).getBound())) {
                    bricks.remove(i);
                    break;
                }
            }
        }
    }
}
