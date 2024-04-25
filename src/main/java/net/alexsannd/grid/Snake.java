package net.alexsannd.grid;

import net.alexsannd.grid.GridController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point> bodyParts;

    private final GridController gridController;
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    public Snake(GridController gridController) {
        bodyParts = new ArrayList<>();
        this.gridController = gridController;
    }

    public void addBodyPart() {
        // Define the directions
        bodyParts.add(new Point(bodyParts.get(bodyParts.size() - 1).x, bodyParts.get(bodyParts.size() - 1).y));

    }public void addBodyPart(int x, int y) {
        // Define the directions
        bodyParts.add(new Point(x, y));
        gridController.getCell(x, y).setColor(Color.GREEN);

    }
    public void setHead(int x, int y) {
        if (!bodyParts.isEmpty()) {
            bodyParts.set(0, new Point(x, y));
        } else {
            bodyParts.add(new Point(x, y));
        }
        gridController.getCell(x, y).setColor(Color.GREEN);
    }
    public Point getHead(){
        return bodyParts.get(0);
    }

    public List<Point> getBodyParts() {
        return bodyParts;
    }

    public boolean isMoveDirectionValid(Direction direction) {
        // Get the current head location
        Point head = bodyParts.get(0);

        // Calculate the new head location based on the direction
        switch (direction) {
            case UP:
                head = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                head = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                head = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                head = new Point(head.x + 1, head.y);
                break;
        }

        Point finalHead = head;
        if (bodyParts.size() > 1) return !bodyParts.get(1).equals(finalHead);
        return true;
    }
    public boolean checkCollision(){
        Point head = bodyParts.get(0);
        // Check if the head collides with the body
        for(int i = 1; i < bodyParts.size(); i++){
            if (head.equals(bodyParts.get(i))){
                return true;
            }
        }
        return false;

    }

    public boolean move(Direction direction) {
        // Create a new list to hold the new locations of the body parts
        List<Point> newBodyPartsLocations = new ArrayList<>(bodyParts);

        // Get the current head location
        Point head = bodyParts.get(0);

        // Calculate the new head location based on the direction
        switch (direction) {
            case UP:
                head = new Point(head.x, head.y - 1);
                break;
            case DOWN:
                head = new Point(head.x, head.y + 1);
                break;
            case LEFT:
                head = new Point(head.x - 1, head.y);
                break;
            case RIGHT:
                head = new Point(head.x + 1, head.y);
                break;
        }
        if (head.x < 0 || head.y < 0 || head.x >= gridController.getRows() || head.y >= gridController.getColumns()){
            return false;
        }

        // Add the new head location to the start of the new locations list
        newBodyPartsLocations.add(0, head);

        // Remove the last body part (as it has now moved up to the previous body part's location)
        newBodyPartsLocations.remove(newBodyPartsLocations.size() - 1);

        // Make the last body part white
        gridController.getCell(bodyParts.get(bodyParts.size() - 1).x, bodyParts.get(bodyParts.size() - 1).y).setColor(gridController.getColor());

        // Update the body parts list
        bodyParts.clear();
        bodyParts.addAll(newBodyPartsLocations);

        // Make the first body part green
        //gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.GREEN);

        updateSnakePartImages();

        return true;
    }
    public void updateSnakePartImages(){
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.GREEN);
        for (int i = 0; i < bodyParts.size(); i++) {
            if (!(i+2 < bodyParts.size())) continue;
            if ((bodyParts.get(i).x == bodyParts.get(i+2).x-1 && bodyParts.get(i).y == bodyParts.get(i+2).y+1) || (bodyParts.get(i).x == bodyParts.get(i+2).x+1 && bodyParts.get(i).y == bodyParts.get(i+2).y+1) || (bodyParts.get(i).x == bodyParts.get(i+2).x+1 && bodyParts.get(i).y == bodyParts.get(i+2).y-1) || (bodyParts.get(i).x == bodyParts.get(i+2).x-1 && bodyParts.get(i).y == bodyParts.get(i+2).y-1)){
                gridController.getCell(bodyParts.get(i+1).x, bodyParts.get(i+1).y).setColor(Color.YELLOW);
            }
            else if (bodyParts.get(i).x == bodyParts.get(i+2).x && bodyParts.get(i).y == bodyParts.get(i+2).y){
                gridController.getCell(bodyParts.get(i+1).x, bodyParts.get(i+1).y).setColor(Color.RED);
            }
            else {
                gridController.getCell(bodyParts.get(i+1).x, bodyParts.get(i+1).y).setColor(Color.GREEN);
            }

        }
    }

    public void reset() {
        for (Point bodyPart : bodyParts) {
            gridController.getCell(bodyPart.x, bodyPart.y).setColor(gridController.getColor());
        }
        bodyParts.clear();
    }
}