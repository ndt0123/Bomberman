package oop.bomberman.entities;

import oop.bomberman.loadLevel.loadFile;

import java.awt.*;
import java.util.Random;

import static oop.bomberman.window.GameJPanel.*;

public class Oneal extends Entity {
    private final  int size = 35;
    public int dx;
    public int dy;
    public final int speed = 2;
    private int moveDerection = 3; //hướng di chuyển
    private int moveDerectionBefore = 1; //hướng di chuyển ở vòng trước
    private int currentAction = 0; //quyết định hình sẽ được vẽ
    private int delayBeforeDie = 0; //thời gian chờ trước khi chết
    private boolean delay = false;

    public Oneal(int x, int y, String path) {
        super(x, y);
        initItem(path);
    }

    private void initItem(String path) {
        loadImage(path);
        getImageDimention();
    }

    public boolean isCollision(){
        for(int i=0; i<bombs.size(); i++) {
            if(this.getBound().intersects(bombs.get(i).getBound())) {
                return true;
            }
        }
        int indexI = this.getBound().y/40, indexJ = this.getBound().x/40;
        if (moveDerection == 1){
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 2){
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 3){
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 4){
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {
        Random random = new Random();
        if(moveDerection==1) {
            y-=speed;
            if(isCollision()) {
                y+=speed;
                do {
                    moveDerection = random.nextInt(4)+1;
                } while (isCollision());
            }
        } else if(moveDerection==2) {
            x+=speed;
            if(isCollision()) {
                x-=speed;
                do {
                    moveDerection = random.nextInt(4)+1;
                } while (isCollision());
            }
        } else if(moveDerection==3) {
            y+=speed;
            if(isCollision()) {
                y-=speed;
                do {
                    moveDerection = random.nextInt(4)+1;
                } while (isCollision());
            }
        } else if(moveDerection==4) {
            x-=speed;
            if(isCollision()) {
                x+=speed;
                do {
                    moveDerection = random.nextInt(4)+1;
                } while (isCollision());
            }
        }
    }

    public static void updateOneal() {
        for( int i=0; i<oneals.size(); i++) {
            for( int j=0; j<flames.size(); j++) {
                if(oneals.get(i).getBound().intersects(flames.get(j).getBound())) {
                    oneals.get(i).setDelay(true);
                }
            }
        }
        for( int i=0; i<oneals.size(); i++) {
            if(oneals.get(i).isDelay()) {
                if(oneals.get(i).getDelayBeforeDie()==5) {
                    oneals.remove(i);
                } else {
                    oneals.get(i).setDelayBeforeDie(oneals.get(i).getDelayBeforeDie()+1);
                }
            }
        }
    }

    //di chuyển oneal
    public static void moveOneals() {
        for(Oneal oneal : oneals) {
            oneal.move();
        }
    }

    public int getSize() {
        return size;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMoveDerection() {
        return moveDerection;
    }

    public void setMoveDerection(int moveDerection) {
        this.moveDerection = moveDerection;
    }

    public int getMoveDerectionBefore() {
        return moveDerectionBefore;
    }

    public void setMoveDerectionBefore(int moveDerectionBefore) {
        this.moveDerectionBefore = moveDerectionBefore;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, size, size);
    }

    public int getDelayBeforeDie() {
        return delayBeforeDie;
    }

    public void setDelayBeforeDie(int delayBeforeDie) {
        this.delayBeforeDie = delayBeforeDie;
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }
}
