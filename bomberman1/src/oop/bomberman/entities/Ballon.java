package oop.bomberman.entities;

import oop.bomberman.loadLevel.loadFile;

import java.awt.*;

import static oop.bomberman.window.GameJPanel.*;

public class Ballon extends Entity {
    private final  int size = 30;
    public int dx;
    public int dy;
    public final int speed = 2;
    private int moveDerection = 0; //hướng di chuyển: 0-phải; 1-trái
    private int moveDerectionBefore = 1; //hướng di chuyển ở vòng trước
    private int currentAction = 0; //quyết định hình sẽ được vẽ
    private int delayBeforeDie = 0; //thời gian chờ trước khi chết
    private boolean delay = false;

    public Ballon(int x, int y, String path) {
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
        int indexI = this.getBound().y/40;
        int indexJ = this.getBound().x/40;
        if(moveDerection==0) {
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ]=='*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ]=='#') {
                if(this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }

        if(moveDerection==1) {
            if( (loadFile.mapCharacter[indexI][indexJ]=='*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ]=='#') {
                if(this.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {
        if(moveDerection==0) {
            x+=speed;
            if(isCollision()) {
                moveDerection = 1;
            }
        } else if(moveDerection==1) {
            x-=speed;
            if(isCollision()) {
                moveDerection = 0;
            }
        }
    }

    public static void updateBallon() {
        for( int i=0; i<ballons.size(); i++) {
            for( int j=0; j<flames.size(); j++) {
                if(ballons.get(i).getBound().intersects(flames.get(j).getBound())) {
                    ballons.get(i).setDelay(true);
                }
            }
        }
        for( int i=0; i<ballons.size(); i++) {
            if(ballons.get(i).isDelay()) {
                if(ballons.get(i).getDelayBeforeDie()==5) {
                    ballons.remove(i);
                } else {
                    ballons.get(i).setDelayBeforeDie(ballons.get(i).getDelayBeforeDie()+1);
                }
            }
        }
    }

    //di chuyển ballon
    public static void moveBallon() {
        for( Ballon ballon : ballons) {
            ballon.move();
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
