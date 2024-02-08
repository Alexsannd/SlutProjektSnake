package net.alexsannd.grid;

import java.awt.*;

public class GridCell {
    private int xPos, yPos;
    private int WIDTH, HEIGHT;
    private boolean isOccupied;
    private Color color;


    public GridCell(int xPos, int yPos, int WIDTH, int HEIGHT, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOccupied = false;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = color;
    }
    public GridCell(int xPos, int yPos, int WIDTH, int HEIGHT){
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOccupied = false;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = Color.WHITE;
    }
    public GridCell(int xPos, int yPos, int WIDTH, int HEIGHT, int Gap, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOccupied = false;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = color;
    }
    public GridCell(int xPos, int yPos, int WIDTH, int HEIGHT, int Gap){
        this.xPos = xPos;
        this.yPos = yPos;
        this.isOccupied = false;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = Color.WHITE;
    }
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, WIDTH, HEIGHT);
    }
    public void setColor(Color color){
        this.color = color;
    }
    public int getXPos(){
        return xPos;
    }

    public int getYPos(){
        return yPos;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void setOccupied(boolean occupied){
        isOccupied = occupied;
    }
}
