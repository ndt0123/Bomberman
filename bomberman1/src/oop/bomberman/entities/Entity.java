package oop.bomberman.entities;

import javax.swing.*;
import java.awt.*;

public class Entity {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
    }

    public void loadImage(String fileName) {
        ImageIcon ii = new ImageIcon(fileName);
        image = ii.getImage();
    }
    public void getImageDimention() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, width, height);
    }
}
