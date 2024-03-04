package net.alexsannd.grid;

import java.awt.*;

public class GridCell {
    private final Point position;
    private int WIDTH, HEIGHT;
    private boolean isOccupied;
    private Color color;


    public GridCell(int xPos, int yPos, int WIDTH, int HEIGHT, Color color){
        this.position = new Point(xPos, yPos);
        this.isOccupied = false;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, WIDTH, HEIGHT);
    }
    public void setColor(Color color){
        this.color = color;
    }
    public int getXPos(){
        return position.x;
    }

    public int getYPos(){
        return position.y;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setOccupied(boolean occupied){
        isOccupied = occupied;
    }
}
