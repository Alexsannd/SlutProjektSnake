package net.alexsannd;

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
        Point[] directions = {new Point(0, -1), new Point(-1, 0), new Point(0, 1), new Point(1, 0)};

        // Try each direction
        Point lastPart = bodyParts.get(bodyParts.size() - 1);
        for (Point direction : directions) {
            // Calculate the new position
            Point newPosition = new Point(lastPart.x + direction.x, lastPart.y + direction.y);
            System.out.println("New position: " + newPosition);

            // Check if the new position is valid
            if (gridController.getCell(newPosition.x, newPosition.y) != null) {


                // Add the new body part
                bodyParts.add(newPosition);

                // Set the color of the new body part
                gridController.getCell(newPosition.x, newPosition.y).setColor(Color.GREEN);

                // Stop trying other directions
                break;
            }
        }

    }
    public void setHead(int x, int y) {
        if (!bodyParts.isEmpty()) {
            bodyParts.set(0, new Point(x, y));
        } else {
            bodyParts.add(new Point(x, y));
        }
        gridController.getCell(x, y).setColor(Color.GREEN);
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
        return !bodyParts.get(1).equals(finalHead);

    }

    public void move(Direction direction) {
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

        // Add the new head location to the start of the new locations list
        newBodyPartsLocations.add(0, head);

        // Remove the last body part (as it has now moved up to the previous body part's location)
        newBodyPartsLocations.remove(newBodyPartsLocations.size() - 1);

        // Make the last body part white
        gridController.getCell(bodyParts.get(bodyParts.size() - 1).x, bodyParts.get(bodyParts.size() - 1).y).setColor(Color.WHITE);

        // Update the body parts list
        bodyParts.clear();
        bodyParts.addAll(newBodyPartsLocations);

        // Make the first body part green
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.GREEN);
    }
}