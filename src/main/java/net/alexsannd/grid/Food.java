package net.alexsannd.grid;

import java.awt.*;

public class Food {
    private int xPos, yPos;
    private Color color;
    private FoodType foodType;
    private GridController gridController;
    private int points;
    //TODO Implement duration and effects of food

    public enum FoodType {
        APPLE, BANANA, CHERRY, STRAWBERRY, BLUEBERRY, ORANGE, PEAR, PINEAPPLE, WATERMELON
    }

    public Food(int xPos, int yPos, FoodType foodType, GridController gridController){
        this.xPos = xPos;
        this.yPos = yPos;
        this.foodType = foodType;
        this.gridController = gridController;
        switch (foodType) {
            case APPLE:
                color = new Color(255, 0, 0); // RGB for Red
                points = 1;
                break;
            case BANANA:
                color = new Color(255, 255, 0); // RGB for Yellow
                points = 2;
                break;
            case CHERRY:
                color = new Color(139, 0, 0); // RGB for Dark Red
                points = 3;
                break;
            case STRAWBERRY:
                color = new Color(255, 105, 180); // RGB for Pink
                points = 4;
                break;
            case BLUEBERRY:
                color = new Color(0, 0, 255); // RGB for Blue
                points = 5;
                break;
            case ORANGE:
                color = new Color(255, 165, 0); // RGB for Orange
                points = 6;
                break;
            case PEAR:
                color = new Color(173, 255, 47); // RGB for Green Yellow'
                points = 7;
                break;
            case PINEAPPLE:
                color = new Color(218, 165, 32); // RGB for Goldenrod
                points = 8;
                break;
            case WATERMELON:
                color = new Color(60, 179, 113); // RGB for Medium Sea Green
                points = 9;
                break;
        }
        gridController.getCell(xPos, yPos).setColor(color);
    }
    public Point getPosition(){
        return new Point(xPos, yPos);
    }
    public int getPoints(){
        return points;
    }
}
