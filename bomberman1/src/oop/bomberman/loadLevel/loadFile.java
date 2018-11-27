package oop.bomberman.loadLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class loadFile {

    public static char[][] mapCharacter = loadMap();
    public static int level = level();
    public static int widthOfMap = width();
    public static int heightOfMap = height();

    public static int height() {
        int height=0;
        try {
            File file = new File("Level1.txt");
            Scanner scn = new Scanner(file);
            scn.nextInt();
            height = scn.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return height;
    }

    public static int width() {
        int width=0;
        try {
            File file = new File("Level1.txt");
            Scanner scn = new Scanner(file);
            scn.nextInt();
            scn.nextInt();
            width = scn.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return width;
    }

    public static int level() {
        int l=0;
        try {
            File file = new File("Level1.txt");
            Scanner scn = new Scanner(file);
            l = scn.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return l;
    }

    public static char[][] loadMap() {
        try {
            File file = new File("Level1.txt");
            Scanner scn = new Scanner(file);
            int level = scn.nextInt();
            int height = scn.nextInt();
            int width = scn.nextInt();
            scn.nextLine();
            char[][] m = new char[height][width];
            for( int i=0; i<height; i++) {
                String line = scn.nextLine();
                for( int j=0; j<line.length(); j++) {
                    m[i][j] = line.charAt(j);
                }
            }
            return m;
        } catch (FileNotFoundException e) {
            return new char[0][0];
        }
    }

//    public static void main(String[] args) {
//
//        for( int i=0; i<mapCharacter.length; i++) {
//            for( int j=0; j<mapCharacter[0].length; j++) {
//                System.out.print(mapCharacter[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println(level);
//        System.out.println(heightOfMap);
//        System.out.println(widthOfMap);
//    }

}
