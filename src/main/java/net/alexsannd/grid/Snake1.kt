package net.alexsannd.grid

import java.awt.Color
import java.awt.Point

class Snake(private val gridController: GridController) {
    private val bodyParts: MutableList<Point> = ArrayList()

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    fun addBodyPart() {
        // Define the directions
        bodyParts.add(Point(bodyParts[bodyParts.size - 1].x, bodyParts[bodyParts.size - 1].y))
    }

    fun addBodyPart(x: Int, y: Int) {
        // Define the directions
        bodyParts.add(Point(x, y))
        gridController.getCell(x, y)!!.setColor(Color.GREEN)
    }

    fun setHead(x: Int, y: Int) {
        if (!bodyParts.isEmpty()) {
            bodyParts[0] = Point(x, y)
        } else {
            bodyParts.add(Point(x, y))
        }
        gridController.getCell(x, y)!!.setColor(Color.GREEN)
    }

    val head: Point
        get() = bodyParts[0]

    fun getBodyParts(): List<Point> {
        return bodyParts
    }

    fun isMoveDirectionValid(direction: Direction): Boolean {
        // Get the current head location
        var head = bodyParts[0]

        // Calculate the new head location based on the direction
        head = when (direction) {
            Direction.UP -> Point(head.x, head.y - 1)
            Direction.DOWN -> Point(head.x, head.y + 1)
            Direction.LEFT -> Point(head.x - 1, head.y)
            Direction.RIGHT -> Point(head.x + 1, head.y)
        }

        val finalHead = head
        if (bodyParts.size > 1) return bodyParts[1] != finalHead
        return true
    }

    fun checkCollision(): Boolean {
        val head = bodyParts[0]
        // Check if the head collides with the body
        for (i in 1 until bodyParts.size) {
            if (head == bodyParts[i]) {
                return true
            }
        }
        return false
    }

    fun move(direction: Direction): Boolean {
        // Create a new list to hold the new locations of the body parts
        val newBodyPartsLocations: MutableList<Point> = ArrayList(bodyParts)

        // Get the current head location
        var head = bodyParts[0]

        // Calculate the new head location based on the direction
        head = when (direction) {
            Direction.UP -> Point(head.x, head.y - 1)
            Direction.DOWN -> Point(head.x, head.y + 1)
            Direction.LEFT -> Point(head.x - 1, head.y)
            Direction.RIGHT -> Point(head.x + 1, head.y)
        }
        if (head.x < 0 || head.y < 0 || head.x >= gridController.rows || head.y >= gridController.columns) {
            return false
        }

        // Add the new head location to the start of the new locations list
        newBodyPartsLocations.add(0, head)

        // Remove the last body part (as it has now moved up to the previous body part's location)
        newBodyPartsLocations.removeAt(newBodyPartsLocations.size - 1)

        // Make the last body part white
        gridController.getCell(bodyParts[bodyParts.size - 1].x, bodyParts[bodyParts.size - 1].y)!!.setColor(
            gridController.color
        )

        // Update the body parts list
        bodyParts.clear()
        bodyParts.addAll(newBodyPartsLocations)

        // Make the first body part green
        //gridController.getCell(bodyParts.get(0).x, bodyParts.get(0).y).setColor(Color.GREEN);
        updateSnakePartImages()

        return true
    }

    fun updateSnakePartImages() {
        gridController.getCell(bodyParts[0].x, bodyParts[0].y)!!.setColor(Color.GREEN)
        for (i in bodyParts.indices) {
            if (i + 2 >= bodyParts.size) continue
            if ((bodyParts[i].x == bodyParts[i + 2].x - 1 && bodyParts[i].y == bodyParts[i + 2].y + 1) || (bodyParts[i].x == bodyParts[i + 2].x + 1 && bodyParts[i].y == bodyParts[i + 2].y + 1) || (bodyParts[i].x == bodyParts[i + 2].x + 1 && bodyParts[i].y == bodyParts[i + 2].y - 1) || (bodyParts[i].x == bodyParts[i + 2].x - 1 && bodyParts[i].y == bodyParts[i + 2].y - 1)) {
                gridController.getCell(bodyParts[i + 1].x, bodyParts[i + 1].y)!!.setColor(Color.YELLOW)
            } else if (bodyParts[i].x == bodyParts[i + 2].x && bodyParts[i].y == bodyParts[i + 2].y) {
                gridController.getCell(bodyParts[i + 1].x, bodyParts[i + 1].y)!!.setColor(Color.RED)
            } else {
                gridController.getCell(bodyParts[i + 1].x, bodyParts[i + 1].y)!!.setColor(Color.GREEN)
            }
        }
    }

    fun reset() {
        for (bodyPart in bodyParts) {
            gridController.getCell(bodyPart.x, bodyPart.y)!!.setColor(gridController.color)
        }
        bodyParts.clear()
    }
}