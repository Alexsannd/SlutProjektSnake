package net.alexsannd;

import net.alexsannd.grid.GridController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point> bodyParts;
    private final GridController gridController;

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
    public void moveUp() {
        Point head = bodyParts.get(0);
        int x = (int) head.getX();
        int y = (int) head.getY() - 1;
        if (y < 0) {
            y = gridController.getColumns() - 1;
        }
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.WHITE);
        bodyParts.remove(0);
        bodyParts.add(0, new Point(x, y));
        gridController.getCell(x, y).setColor(Color.GREEN);
    }
    public void moveDown() {
        Point head = bodyParts.get(0);
        int x = (int) head.getX();
        int y = (int) head.getY() + 1;
        if (y >= gridController.getColumns()) {
            y = 0;
        }
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.WHITE);
        bodyParts.remove(0);
        bodyParts.add(0, new Point(x, y));
        gridController.getCell(x, y).setColor(Color.GREEN);
    }
    public void moveLeft() {
        Point head = bodyParts.get(0);
        int x = (int) head.getX() - 1;
        int y = (int) head.getY();
        if (x < 0) {
            x = gridController.getRows() - 1;
        }
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.WHITE);
        bodyParts.remove(0);
        bodyParts.add(0, new Point(x, y));
        gridController.getCell(x, y).setColor(Color.GREEN);
    }
    public void moveRight() {
        Point head = bodyParts.get(0);
        int x = (int) head.getX() + 1;
        int y = (int) head.getY();
        if (x >= gridController.getRows()) {
            x = 0;
        }
        gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.WHITE);
        bodyParts.remove(0);
        bodyParts.add(0, new Point(x, y));
        gridController.getCell(x, y).setColor(Color.GREEN);
    }

}