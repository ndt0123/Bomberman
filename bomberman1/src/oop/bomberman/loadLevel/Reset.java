package oop.bomberman.loadLevel;

import oop.bomberman.entities.Bomber;

import java.awt.*;
import java.util.ArrayList;

import static oop.bomberman.window.GameJPanel.*;

public class Reset {
    public static void reset() {
        grass = new Image[loadFile.heightOfMap][loadFile.widthOfMap];
        ballons.clear();
        bombs.clear();
        bricks.clear();
        flames.clear();
        speedItems.clear();
        flameItems = new ArrayList<>();
        bombItems = new ArrayList<>();
        walls = new ArrayList<>();
        oneals = new ArrayList<>();
    }
}
