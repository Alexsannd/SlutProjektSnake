package net.alexsannd.grid

import java.awt.Color
import java.awt.Graphics

class GridController(val rows: Int, val columns: Int, val cellWidth: Int, val cellHeight: Int, Gap: Int, color: Color) {
    private val grid: MutableList<MutableList<GridCell?>> = ArrayList()
    private var Gap = 5
    val color: Color

    init {
        this.Gap = Gap
        this.color = color
        for (i in 0 until rows) {
            grid.add(ArrayList())
            for (j in 0 until columns) {
                grid[i].add(
                    GridCell(
                        i * cellWidth + Gap * i, j * cellHeight + Gap * j,
                        cellWidth,
                        cellHeight, color
                    )
                )
            }
        }
    }

    fun paint(g: Graphics) {
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                grid[i][j]!!.paint(g)
            }
        }
    }

    fun getCell(x: Int, y: Int): GridCell? {
        if (x >= grid.size || y >= grid[x].size || x < 0 || y < 0) return null
        return grid[x][y]
    }

    fun getCell(cell: GridCell): GridCell? {
        return grid.stream()
            .flatMap { obj: List<GridCell?> -> obj.stream() }
            .filter { c: GridCell? -> c == cell }
            .findFirst()
            .orElse(null)
    }

    val width: Int
        get() = rows * cellWidth + Gap * (rows - 1)
    val height: Int
        get() = columns * cellHeight + Gap * (columns - 1)

    fun setCellColor(x: Int, y: Int, color: Color?) {
        grid[x][y]!!.setColor(color!!)
    }
}
