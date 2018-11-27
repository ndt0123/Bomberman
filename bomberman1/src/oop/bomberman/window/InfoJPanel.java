package oop.bomberman.window;

import oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InfoJPanel extends JPanel {

    public static final int heightOfInfoJPanel = 40;

    private static JLabel timeLabel;
    private static JLabel pointsLabel;
    public static int time = 200;
    public static int coutTime = 0;
    private int level = 1;

    public InfoJPanel() {
        setLayout(new GridLayout());

        timeLabel = new JLabel("Time: " + time);
        timeLabel.setForeground(Color.white);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        pointsLabel = new JLabel("Level: " + level );
        pointsLabel.setForeground(Color.white);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        add(timeLabel);
        add(pointsLabel);

        setBackground(Color.black);
        setPreferredSize(new Dimension(0, heightOfInfoJPanel));


    }

    public static void setTime(int t) {
        timeLabel.setText("Time: " + t);
    }
    public int getTime(int time) {
        return time;
    }

    public void setlevel(int t) {
        pointsLabel.setText("Score: " + t);
    }

}