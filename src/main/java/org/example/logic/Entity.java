package org.example.logic;

import javax.swing.*;
import java.awt.*;

public class Entity {
    protected Coordinates coord;
    protected int width;
    protected int height;
    protected Image image;

    public Entity(int x, int y, String url) {
        this.coord = new Coordinates(x, y);


        ImageIcon ii = new ImageIcon(getClass().getResource("/" + url));
        this.image = ii.getImage();

        this.width = ii.getIconWidth();
        this.height = ii.getIconHeight();

    }

    public void move(int steps, Direction direction) {
        switch (direction) {
            case LEFT -> {
                this.coord.x -= steps;
            }
            case RIGHT -> {
                this.coord.x += steps;
            }
            case UP -> {
                this.coord.y -= steps;
            }
            case DOWN -> {
                this.coord.y += steps;
            }
        }
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Rectangle getRectangle() {
        return new Rectangle(coord.x, coord.y, width, height);
    }
}
