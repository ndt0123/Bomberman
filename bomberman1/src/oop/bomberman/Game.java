package oop.bomberman;

import oop.bomberman.loadLevel.loadFile;
import oop.bomberman.window.GameJPanel;

import java.awt.*;


public interface Game {
    public static final int width = GameJPanel.sizeOfObj * loadFile.widthOfMap; //chiều rộng của map game tính theo pixel
    public static final int height = GameJPanel.sizeOfObj * loadFile.heightOfMap; //chiều dài của map game tính theo pixel
    public static final int sizeOfObj = 40; //chiều dài và rộng của mỗi đối tượng trên map trừ bomberMan và enemy và các item
    public static final int SPEEDOFENEMY = 2;
    public final int DELAY = 40;
}