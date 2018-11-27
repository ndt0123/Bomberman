package oop.bomberman.entities;

import oop.bomberman.loadLevel.loadFile;
import oop.bomberman.window.GameJPanel;

import javax.swing.*;
import static oop.bomberman.window.GameJPanel.*;

import static oop.bomberman.window.GameJPanel.bomber;

public class Bomb extends Entity{
    private final  int size = 30;
    private int currentAction = 1;
    private int countTimesToBreak = 0;
    private final int timeBreak = 3000;
    public static int effectArea;

    public Bomb(int x, int y) {
        super(x, y);
        initItem();
    }

    private void initItem() {
        loadImage("Graphic\\Sprites\\Bomb\\Bomb_f01.png");
        getImageDimention();
    }

    //cho bom nổ và hiển thị flame
    public static void updateBomb() {
        if(Bomber.getFlameItem) {
            Bomb.effectArea = 5;
        } else {
            Bomb.effectArea = 3;
        }
        for( int i=0; i<bombs.size(); i++) {
            if(bombs.get(i).getcountTimesToBreak() == bombs.get(i).getTimeBreak()/DELAY) {

                int indexI = bombs.get(i).getY()/40;
                int indexJ = bombs.get(i).getX()/40;

                int sizeUp = Bomb.effectArea;
                int sizeRight = Bomb.effectArea;
                int sizeDown = Bomb.effectArea;
                int sizeLeft = Bomb.effectArea;

                //flame ở trên
                for( int j=1; j<Bomb.effectArea; j++) {
                    if(loadFile.mapCharacter[indexI-j][indexJ] == '#') {
                        sizeUp = j;
                        break;
                    } else if(loadFile.mapCharacter[indexI-j][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, (indexI-j)*40))) {
                        sizeUp = j+1;
                        break;
                    }
                }
                for(int j=0; j<sizeUp; j++) {
                    if(j==sizeUp-1) {
                        Flame flame1 = new Flame(indexJ*40+5, (indexI-j)*40+10);
                        flames.add(flame1);
                    } else {
                        Flame flame1 = new Flame(indexJ*40+5, (indexI-j)*40+10);
                        Flame flame2 = new Flame(indexJ*40+5, (indexI-j)*40+10-20);
                        flames.add(flame1);
                        flames.add(flame2);
                    }
                }

                //flame bên dưới
                for( int j=1; j<Bomb.effectArea; j++) {
                    if(loadFile.mapCharacter[indexI+j][indexJ] == '#') {
                        sizeDown = j;
                        break;
                    } else if(loadFile.mapCharacter[indexI+j][indexJ] == '*' && bricks.contains(new Brick(indexJ*40, (indexI+j)*40))) {
                        sizeDown = j+1;
                        break;
                    }
                }
                for(int j=0; j<sizeDown; j++) {
                    if(j==sizeDown-1) {
                        Flame flame1 = new Flame(indexJ*40+5, (indexI+j)*40);
                        flames.add(flame1);
                    } else {
                        Flame flame1 = new Flame(indexJ*40+5, (indexI+j)*40);
                        Flame flame2 = new Flame(indexJ*40+5, (indexI+j)*40+20);
                        flames.add(flame1);
                        flames.add(flame2);
                    }
                }

                //flame bên phải
                for( int j=1; j<Bomb.effectArea; j++) {
                    if(loadFile.mapCharacter[indexI][indexJ+j] == '#') {
                        sizeRight = j;
                        break;
                    } else if(loadFile.mapCharacter[indexI][indexJ+j] == '*' && bricks.contains(new Brick((indexJ+j)*40, indexI*40))) {
                        sizeRight = j+1;
                        break;
                    }
                }
                for(int j=0; j<sizeRight; j++) {
                    if(j==sizeRight-1) {
                        Flame flame1 = new Flame((indexJ+j)*40, indexI*40+5);
                        flames.add(flame1);
                    } else {
                        Flame flame1 = new Flame((indexJ+j)*40, indexI*40+5);
                        Flame flame2 = new Flame((indexJ+j)*40+20, indexI*40+5);
                        flames.add(flame1);
                        flames.add(flame2);
                    }
                }

                //flame bên trái
                for( int j=1; j<Bomb.effectArea; j++) {
                    if(loadFile.mapCharacter[indexI][indexJ-j] == '#') {
                        sizeLeft = j;
                        break;
                    } else if(loadFile.mapCharacter[indexI][indexJ-j] == '*' && bricks.contains(new Brick((indexJ-j)*40, indexI*40))) {
                        sizeLeft = j+1;
                        break;
                    }
                }
                for(int j=0; j<sizeLeft; j++) {
                    if(j==sizeLeft-1) {
                        Flame flame1 = new Flame((indexJ-j)*40+10, indexI*40+5);
                        flames.add(flame1);
                    } else {
                        Flame flame1 = new Flame((indexJ-j)*40+10, indexI*40+5);
                        Flame flame2 = new Flame((indexJ-j)*40+10-20, indexI*40+5);
                        flames.add(flame1);
                        flames.add(flame2);
                    }
                }
                bombs.remove(i);
            } else {
                bombs.get(i).setcountTimesToBreak(bombs.get(i).getcountTimesToBreak()+1);
            }
        }
    }

    public int getTimeBreak() {
        return timeBreak;
    }

    public int getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(int currentAction) {
        this.currentAction = currentAction;
    }

    public int getcountTimesToBreak() {
        return countTimesToBreak;
    }

    public void setcountTimesToBreak( int countTimesToBreak) {
        this.countTimesToBreak = countTimesToBreak;
    }

    public int getSize() {
        return size;
    }

    public int getEffectArea() {
        return effectArea;
    }

    public void setEffectArea(int effectArea) {
        this.effectArea = effectArea;
    }


}
