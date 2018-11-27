package oop.bomberman.window;


import oop.bomberman.Game;
import oop.bomberman.entities.*;
import oop.bomberman.loadLevel.loadFile;
import oop.bomberman.move.MyAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;

public class GameJPanel extends JPanel implements ActionListener, Game {
    public static Image[][] grass = new Image[loadFile.heightOfMap][loadFile.widthOfMap]; //mảng 2 chiều kiểu Image để lưu toàn bộ glass background

    public static ArrayList<Ballon> ballons = new ArrayList<>(); //mảng các ballon
    public static ArrayList<Bomb> bombs = new ArrayList<>();
    public static Bomber bomber; //
    public static ArrayList<Brick> bricks = new ArrayList<>();
    public static ArrayList<Flame> flames = new ArrayList<>();
    public static ArrayList<SpeedItem> speedItems = new ArrayList<>();
    public static ArrayList<FlameItem> flameItems = new ArrayList<>();
    public static ArrayList<BombItem> bombItems = new ArrayList<>();
    public static ArrayList<Wall> walls = new ArrayList<>();
    public static ArrayList<Oneal> oneals = new ArrayList<>();
    public static Portal portal;


    public static boolean inGame;
    public static boolean victory = false;
    private Timer timer;


    public GameJPanel() {
        inGame = true;
        setFocusable(true);

        //cài đặt toàn bộ background glass
        for( int i=0; i<grass.length; i++) {
            for( int j=0; j<grass[0].length; j++) {
                ImageIcon imageIcon = new ImageIcon("Graphic\\Sprites\\Blocks\\BackgroundTile.png");
                grass[i][j] = imageIcon.getImage();
            }
        }

        //cài đặt các đối tượng trong game
        for( int i=0; i<loadFile.mapCharacter.length; i++) {
            for( int j=0; j<loadFile.mapCharacter[i].length; j++) {
                if(loadFile.mapCharacter[i][j] == '#') { //tường không thể bị phá hủy
                    Wall wall = new Wall(j*40, i*40);
                    walls.add(wall);

                } else if(loadFile.mapCharacter[i][j] == '*') { //tường có thể bị phá hủy
                    Brick brick = new Brick(j*40, i*40);
                    bricks.add(brick);

                } else if(loadFile.mapCharacter[i][j] == 'p') { //bomber
                    bomber = new Bomber(j*40, i*40, "Graphic\\Sprites\\Bomberman\\Down\\Bman_F_f00.png");

                } else if(loadFile.mapCharacter[i][j] == '1') { //ballon
                    Ballon ballon = new Ballon(j*40+5, i*40+5, "Graphic\\Sprites\\Creep\\Left\\Creep_S_f00.png");
                    ballons.add(ballon);
                } else if(loadFile.mapCharacter[i][j] == '2') { //oneal
                    Oneal oneal = new Oneal(j*40+2, i*40+2, "Graphic\\Sprites\\Creep\\Down\\Creep_F_f00.png");
                    oneals.add(oneal);
                } else if(loadFile.mapCharacter[i][j] == 'x') {
                    portal = new Portal(j*40, i*40);
                }
            }
        }

        //các Item
        Random randomItem = new Random();
        //
        int randomBombItem = randomItem.nextInt(bricks.size());
        BombItem bombItem = new BombItem(bricks.get(randomBombItem).getX()+5, bricks.get(randomBombItem).getY()+5);
        bombItems.add(bombItem);

        //
        int randomFlameItem = randomItem.nextInt(bricks.size());
        FlameItem flameItem = new FlameItem(bricks.get(randomFlameItem).getX()+5, bricks.get(randomFlameItem).getY()+5);
        flameItems.add(flameItem);

        int randomSpeedItem = randomItem.nextInt(bricks.size());
        SpeedItem speedItem = new SpeedItem(bricks.get(randomSpeedItem).getX()+5, bricks.get(randomSpeedItem).getY()+5);
        speedItems.add(speedItem);

        MyAdapter adapter = new MyAdapter(bomber);
        addKeyListener(adapter);
        timer = new Timer(DELAY, this);
        timer.start();


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //hiển thị background glass
        for( int i=0; i<grass.length; i++) {
            for( int j=0; j<grass[0].length; j++) {
                g.drawImage(grass[i][j], j*sizeOfObj, i*sizeOfObj, sizeOfObj, sizeOfObj, this);
            }
        }

        //hiển thị các đối tượng trong game
        if(victory) {
            drawVictory(g);
        }
        else if (inGame) {
            drawObject(g);
        } else {
            drawGameOver(g);
        }
    }


    private void drawObject(Graphics g) {
        //vẽ Portal
        if(ballons.isEmpty() && oneals.isEmpty()) {
            g.drawImage(portal.getImage(), portal.getX(), portal.getY(), portal.getSize(), portal.getSize(), this);
        }

        //vẽ speedItem
        for(SpeedItem speedItem : speedItems) {
            g.drawImage(speedItem.getImage(), speedItem.getX(), speedItem.getY(), speedItem.getSize(), speedItem.getSize(), this);
        }

        //vẽ flameItem
        for(FlameItem flameItem : flameItems) {
            g.drawImage(flameItem.getImage(), flameItem.getX(),flameItem.getY(), flameItem.getSize(), flameItem.getSize(), this);
        }

        //vẽ bombItem
        for(BombItem bombItem : bombItems) {
            g.drawImage(bombItem.getImage(), bombItem.getX(), bombItem.getY(), bombItem.getSize(), bombItem.getSize(), this);
        }

        //vẽ tường có thể bị phá hủy
        for(Brick brick : bricks) {
            g.drawImage(brick.getImage(), brick.getX(), brick.getY(), brick.getSize(), brick.getSize(), this);
        }
        //vẽ lửa
        for(int i=0; i<flames.size(); i++) {
            if(flames.get(i).getCurrentAction() >= 4) {
                flames.get(i).setCurrentAction(0);
            } else {
                flames.get(i).setCurrentAction(flames.get(i).getCurrentAction()+1);
            }
            ImageIcon im = new ImageIcon("Graphic\\Sprites\\Flame\\Flame_f0" + flames.get(i).getCurrentAction() + ".png");
            flames.get(i).setImage(im.getImage());
            g.drawImage(flames.get(i).getImage(), flames.get(i).getX(), flames.get(i).getY(), flames.get(i).getSize(), flames.get(i).getSize(), this);
        }
        //vẽ tường
        for (Wall wall : walls) {
            g.drawImage(wall.getImage(), wall.getX(), wall.getY(), wall.getSize(), wall.getSize(),  this);
        }

        //vẽ quái ballon chỉ di chuyển theo một hướng
        for( int i=0; i<ballons.size(); i++) {
            if(ballons.get(i).getMoveDerection()==1) {
                if(ballons.get(i).getCurrentAction() >=6) {
                    ballons.get(i).setCurrentAction(0);
                } else {
                    ballons.get(i).setCurrentAction(ballons.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Left\\Creep_S_f0" + ballons.get(i).getCurrentAction() + ".png");
                ballons.get(i).setImage(im.getImage());
                g.drawImage(ballons.get(i).getImage(), ballons.get(i).getX(), ballons.get(i).getY(), ballons.get(i).getSize(), ballons.get(i).getSize(), this);
            } else if(ballons.get(i).getMoveDerection()==0) {
                if(ballons.get(i).getCurrentAction() >=6) {
                    ballons.get(i).setCurrentAction(0);
                } else {
                    ballons.get(i).setCurrentAction(ballons.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Right\\Creep_S_f0" + ballons.get(i).getCurrentAction() + ".png");
                ballons.get(i).setImage(im.getImage());
                g.drawImage(ballons.get(i).getImage(), ballons.get(i).getX(), ballons.get(i).getY(), ballons.get(i).getSize(), ballons.get(i).getSize(), this);
            }
        }

        //vẽ bom
        for( int i=0; i<bombs.size(); i++) {
            if(bombs.get(i).getCurrentAction() >= 3) {
                bombs.get(i).setCurrentAction(1);
            } else {
                bombs.get(i).setCurrentAction(bombs.get(i).getCurrentAction()+1);
            }
            ImageIcon im = new ImageIcon("Graphic\\Sprites\\Bomb\\Bomb_f0" + bombs.get(i).getCurrentAction() + ".png");
            bombs.get(i).setImage(im.getImage());
            g.drawImage(bombs.get(i).getImage(), bombs.get(i).getX(), bombs.get(i).getY(), bombs.get(i).getSize(), bombs.get(i).getSize(), this);
        }

        //vẽ quái oneal có thể di chuyển ngẫu nhiên
        for( int i=0; i<oneals.size(); i++) {
            if(oneals.get(i).getMoveDerection()==1) {
                if(oneals.get(i).getCurrentAction()>=5) {
                    oneals.get(i).setCurrentAction(0);
                } else {
                    oneals.get(i).setCurrentAction(oneals.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Up\\Creep_B_f0" + oneals.get(i).getCurrentAction() + ".png");
                oneals.get(i).setImage(im.getImage());
                g.drawImage(oneals.get(i).getImage(), oneals.get(i).getX(), oneals.get(i).getY(), oneals.get(i).getSize(), oneals.get(i).getSize(), this);
            }
            if(oneals.get(i).getMoveDerection()==2) {
                if(oneals.get(i).getCurrentAction()>=6) {
                    oneals.get(i).setCurrentAction(0);
                } else {
                    oneals.get(i).setCurrentAction(oneals.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Right\\Creep_S_f0" + oneals.get(i).getCurrentAction() + ".png");
                oneals.get(i).setImage(im.getImage());
                g.drawImage(oneals.get(i).getImage(), oneals.get(i).getX(), oneals.get(i).getY(), oneals.get(i).getSize(), oneals.get(i).getSize(), this);
            }
            if(oneals.get(i).getMoveDerection()==3) {
                if(oneals.get(i).getCurrentAction()>=5) {
                    oneals.get(i).setCurrentAction(0);
                } else {
                    oneals.get(i).setCurrentAction(oneals.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Down\\Creep_F_f0" + oneals.get(i).getCurrentAction() + ".png");
                oneals.get(i).setImage(im.getImage());
                g.drawImage(oneals.get(i).getImage(), oneals.get(i).getX(), oneals.get(i).getY(), oneals.get(i).getSize(), oneals.get(i).getSize(), this);
            }
            if(oneals.get(i).getMoveDerection()==4) {
                if(oneals.get(i).getCurrentAction()>=6) {
                    oneals.get(i).setCurrentAction(0);
                } else {
                    oneals.get(i).setCurrentAction(oneals.get(i).getCurrentAction()+1);
                }
                ImageIcon im = new ImageIcon("Graphic\\Sprites\\Creep\\Left\\Creep_S_f0" + oneals.get(i).getCurrentAction() + ".png");
                oneals.get(i).setImage(im.getImage());
                g.drawImage(oneals.get(i).getImage(), oneals.get(i).getX(), oneals.get(i).getY(), oneals.get(i).getSize(), oneals.get(i).getSize(), this);
            }
        }

        //vẽ bomberman
        g.drawImage(bomber.getImage(), bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight(), this);
    }

    private void drawGameOver(Graphics g) {
        Font font = new Font("Helvetica", Font.BOLD, 50);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("GAME OVER!", GameJPanel.width/2-150, GameJPanel.height/2);
    }

    private void drawVictory(Graphics g) {
        Font font = new Font("Helvetica", Font.BOLD, 50);
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString("VICTORY!", GameJPanel.width/2-150, GameJPanel.height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        isVictory();
        // Update Object
        updateTime();
        Bomber.updateBomber();
        //
        Ballon.moveBallon();
        Ballon.updateBallon();
        //
        Oneal.updateOneal();
        Oneal.moveOneals();
        //
        Bomb.updateBomb();
        //
        Brick.updateBrick();
        Flame.updateFlame();
        //
        Bomber.checkCollision();
        // Repaint
        repaint();
    }

    private void inGame() {
        if (!inGame) {
            timer.stop();
        }
    }

    //update thời gian kết thúc trò chơi
    private void updateTime() {
        if(InfoJPanel.coutTime == 1000/DELAY) {
            InfoJPanel.coutTime = 0;
            InfoJPanel.time--;
        } else {
            InfoJPanel.coutTime++;
        }
        if(InfoJPanel.time == 0) {
            inGame = false;
        }
        InfoJPanel.setTime(InfoJPanel.time);
    }

    //kiểm tra xem có chiến thắng chưa
    private void isVictory() {
        if(bomber.getBound().intersects(new Rectangle(portal.getX()+19, portal.getY()+19, 2, 2))
            && ballons.isEmpty() && oneals.isEmpty()) {
            victory = true;
        }
    }

}