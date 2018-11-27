package oop.bomberman.entities;

import static oop.bomberman.window.GameJPanel.*;

public class Flame extends Entity {

    private final  int size = 30;
    private int currentAction = 0;
    private int countTimesToBreak = 0;
    private final int timeBreak = 1000;

    public Flame(int x, int y, String path) {
        super(x, y);
        loadImage(path);
        getImageDimention();
    }


    public Flame(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Flame\\Flame_f00.png");
        getImageDimention();
    }

    //xóa lửa sau khi hiển thị 1 giây
    public static void updateFlame() {
        for( int i=0; i<flames.size(); i++) {
            if(flames.get(i).getCountTimesToBreak() == flames.get(i).getTimeBreak()/DELAY) {
                flames.remove(i);
            } else {
                flames.get(i).setCountTimesToBreak(flames.get(i).getCountTimesToBreak()+1);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getCountTimesToBreak() {
        return countTimesToBreak;
    }

    public void setCountTimesToBreak(int countTimesToBreak) {
        this.countTimesToBreak = countTimesToBreak;
    }

    public int getTimeBreak() {
        return timeBreak;
    }
}
