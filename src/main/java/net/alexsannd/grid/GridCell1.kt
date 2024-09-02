package net.alexsannd.grid

import java.awt.Color
import java.awt.Graphics
import java.awt.Point

class GridCell(xPos: Int, yPos: Int, private val WIDTH: Int, private val HEIGHT: Int, private var color: Color) {
    private val position = Point(xPos, yPos)
    var isOccupied: Boolean = false

    fun paint(g: Graphics) {
        g.color = color
        g.fillRect(position.x, position.y, WIDTH, HEIGHT)
    }

    fun setColor(color: Color) {
        this.color = color
    }

    val xPos: Int
        get() = position.x

    val yPos: Int
        get() = position.y
}
