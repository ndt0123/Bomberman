package oop.bomberman.window;

import oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public static final int width = GameJPanel.width + 6;
    public static final int height = InfoJPanel.heightOfInfoJPanel + GameJPanel.height + 29;


    private Game game;
    public Window() {
        setSize(width, height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Bomberman");

        //add hai thành phần InforJPanel và GameJPanel
        InfoJPanel infoJPanel = new InfoJPanel();
        add(infoJPanel, BorderLayout.PAGE_START);
        GameJPanel gameJPanel = new GameJPanel();
        add(gameJPanel);

        setVisible(true);

    }

    public static void main(String[] args) {
        Window window = new Window();
    }
}