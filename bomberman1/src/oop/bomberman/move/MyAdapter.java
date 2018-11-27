package oop.bomberman.move;

import oop.bomberman.entities.Bomber;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyAdapter extends KeyAdapter {
    private Bomber bomber;
    public MyAdapter(Bomber bomber) {
        this.bomber = bomber;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        bomber.keyPressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        bomber.keyReleased(e);
    }
}