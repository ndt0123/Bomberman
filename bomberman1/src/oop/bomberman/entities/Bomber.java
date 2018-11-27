package oop.bomberman.entities;


import oop.bomberman.Game;
import oop.bomberman.loadLevel.Reset;
import oop.bomberman.loadLevel.loadFile;
import oop.bomberman.window.GameJPanel;
import oop.bomberman.window.InfoJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static oop.bomberman.window.GameJPanel.*;


public class Bomber extends Entity implements Game {
    private final  int width = 35;
    private final int height = 60;
    public int dx;
    public int dy;
    public static int speed = 3;
    public static int moveDerection = 3; //hướng di chuyển
    public static int moveDerectionBefore = 3; //hướng di chuyển ở vòng trước
    public static int currentAction = 0; //quyết định hình sẽ được vẽ
    public static int delayBeforeDie = 0; //thời gian chờ sau khi bomber bị chết
    public static boolean delay = false;
    public static boolean getBombItem = false; //biến lưu xem bomber có ăn được bombItem khômg
    public static boolean getSpeedItem = false; //biến lưu xem bomber có ăn được speedItem khômg
    public static boolean getFlameItem = false; //biến lưu xem bomber có ăn được flameItem khômg


    public Bomber(int x, int y, String path) {
        super(x, y);
        initItem(path);
    }

    private void initItem(String path) {
        loadImage(path);
        getImageDimention();
    }


    public void move() {
        if (!isCollision()) {
            x += dx;
            y += dy;
            if (isCollision()){
                x -= dx;
                y -= dy;
            }
        }
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    public void keyPressed(KeyEvent e) {
        if(Bomber.getSpeedItem) {
            Bomber.speed = 5;
        } else {
            Bomber.speed = 3;
        }
        int key = e.getKeyCode();
        ImageIcon im;
        switch (key) {
            case KeyEvent.VK_UP:
                //xử lý đồ họa chuyển động
                moveDerectionBefore = moveDerection;
                if(moveDerectionBefore != 1) { //nếu trước đó không đang di chuyển lên trên
                    currentAction = 0;
                } else { //nếu trước đó đang di chuyển lên trên
                    if(currentAction>=7) {
                        currentAction = 0;
                    }
                    currentAction++;
                }
                im = new ImageIcon("Graphic\\Sprites\\Bomberman\\Up\\Bman_B_f0" + currentAction + ".png");
                bomber.setImage(im.getImage());
                moveDerection = 1;
                dy = -speed;
                break;

            case KeyEvent.VK_DOWN:
                //xử lý đồ họa khi chuyển động đi xuống
                moveDerectionBefore = moveDerection;
                if(moveDerectionBefore != 3) { //nếu trước đó không đang di chuyển xuống dưới
                    currentAction = 0;
                } else { //nếu trước đó đang di chuyển xuống dưới
                    if(currentAction>=7) {
                        currentAction = 0;
                    }
                    currentAction++;
                }
                im = new ImageIcon("Graphic\\Sprites\\Bomberman\\Down\\Bman_F_f0" + currentAction + ".png");
                bomber.setImage(im.getImage());
                moveDerection = 3;
                dy = speed;
                break;

            case KeyEvent.VK_LEFT:
                //chuyển động
                moveDerectionBefore = moveDerection;
                if(moveDerectionBefore != 4) { //nếu trước đó không đang di chuyển sang trái
                    currentAction = 0;
                } else { //nếu trước đó đang di chuyển sang trái
                    if(currentAction>=7) {
                        currentAction = 0;
                    }
                    currentAction++;
                }
                im = new ImageIcon("Graphic\\Sprites\\Bomberman\\Left\\Bman_F_f0" + currentAction + ".png");
                bomber.setImage(im.getImage());
                moveDerection = 4;
                dx = -speed;
                break;

            case KeyEvent.VK_RIGHT:
                //chuyển động
                moveDerectionBefore = moveDerection;
                if(moveDerectionBefore != 2) { //nếu trước đó không đang di chuyển sang phải
                    currentAction = 0;
                } else { //nếu trước đó đang di chuyển sang phải
                    if(currentAction>=7) {
                        currentAction = 0;
                    }
                    currentAction++;
                }
                im = new ImageIcon("Graphic\\Sprites\\Bomberman\\Right\\Bman_F_f0" + currentAction + ".png");
                bomber.setImage(im.getImage());
                moveDerection = 2;
                dx = speed;
                break;
            case KeyEvent.VK_SPACE:
                if( (bombs.isEmpty() && !getBombItem)
                    || (bombs.size()<2 && getBombItem)) {
                    Bomb bomb = new Bomb(((getBound().x+10)/40)*40+5, ((getBound().y+10)/40)*40+5);
                    GameJPanel.bombs.add(bomb);
                }
                break;
            case KeyEvent.VK_ENTER:
                if(victory) {
                    //next level
                } else if(!inGame) {
                    //chơi lại
                  //  Reset.reset();
                  //  new GameJPanel();
                  //  inGame = true;
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                dy = 0;
                break;
            case KeyEvent.VK_DOWN:
                dy = 0;
                break;
            case KeyEvent.VK_LEFT:
                dx = 0;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 0;
                break;
        }
    }

    //xử lý va chạm của bomber
    public static void checkCollision() {
        Rectangle bomberBound = bomber.getBound();
        //xủ lí khi va chạm với enemy ballon
        for( int i=0; i<ballons.size(); i++) {
            if(bomberBound.intersects(ballons.get(i).getX()+12, ballons.get(i).getY()+12, 6, 6)) {
                inGame = false;
            }
        }
        //xử lí khi bomber va chạm với enemy oneal
        for( int i=0; i<oneals.size(); i++) {
            if(bomberBound.intersects(new Rectangle(oneals.get(i).getX()+15, oneals.get(i).getY()+15, 5, 5))) {
                inGame = false;
            }
        }
        //xử lí khi bomber va chạm với flame
        for( int i=0; i<flames.size(); i++) {
            if(flames.get(i).getBound().intersects(bomberBound.x+15, bomberBound.y+15, 10, 10)) {
                Bomber.delay = true;
            }
        }
        if(Bomber.delay) {
            Bomber.delayBeforeDie++;
        }
        if(Bomber.delayBeforeDie==5) {
            inGame = false;
        }

        //xử lí khi bomber ăn được bombItem
        if(!bombItems.isEmpty()) {
            for(BombItem bombItem: bombItems) {
                if(bomberBound.intersects(bombItem.getBound())) {
                    getBombItem = true;
                    bombItems.remove(bombItem);
                    break;
                }
            }
        }
        if(getBombItem) {
            if(BombItem.countTimeToLoseBombItem==50000/DELAY) {
                getBombItem = false;
            } else {
                BombItem.countTimeToLoseBombItem++;
            }
        }


        //xử lí kho bomber ăn được FlameItem
        if(!flameItems.isEmpty()) {
            for(FlameItem flameItem: flameItems) {
                if(bomberBound.intersects(flameItem.getBound())) {
                    getFlameItem = true;
                    flameItems.remove(flameItem);
                    break;
                }
            }
        }
        if(getFlameItem) {
            if(FlameItem.countTimeToLoseFlameItem>=50000/DELAY) {
                getFlameItem = false;
            } else {
                FlameItem.countTimeToLoseFlameItem++;
            }
        }

        //xử lí khi bomber ăn được SpeedItem
        if(!speedItems.isEmpty()) {
            for(SpeedItem speedItem : speedItems) {
                if(bomberBound.intersects(speedItem.getBound())) {
                    getSpeedItem = true;
                    speedItems.remove(speedItem);
                    break;
                }
            }
        }
        if(getSpeedItem) {
            if(SpeedItem.countTimeToLoseSpeedItem==50000/DELAY) {
                getSpeedItem=false;
            } else {
                SpeedItem.countTimeToLoseSpeedItem++;
            }
        }

    }

    //lấy hình chữ nhật để xét va chạm
    public Rectangle getBound() {
        return new Rectangle(x+2, y+30, 31, 30);
    }

    public boolean isCollision(){
        int indexI = getBound().y/40, indexJ = getBound().x/40;
        if (moveDerection == 1){
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)) )
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 2){
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)) )
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if(bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*'  && bricks.contains(new Brick(indexJ*40, indexI*40)) )
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 3){
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*'  && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if(bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexJ++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*'  && bricks.contains(new Brick(indexJ*40, indexI*40)) )
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }else if (moveDerection == 4){
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)))
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if(bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
            indexI++;
            if( (loadFile.mapCharacter[indexI][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, indexI*40)) )
                    || loadFile.mapCharacter[indexI][indexJ] == '#') {
                if( bomber.getBound().intersects(new Rectangle(indexJ*40, indexI*40, 40, 40))) {
                    return true;
                }
            }
        }
        return false;
    }

    //di chuyển bomber
    public static void updateBomber() {
        bomber.move();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}