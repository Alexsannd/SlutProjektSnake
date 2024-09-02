package net.alexsannd

import java.awt.Color
import java.awt.Font
import java.awt.Graphics

class HUD(private val x: Int, private val y: Int, private val width: Int, private val height: Int) {
    private var score = 0
    private var highScore = 0
    private var level = 1

    fun paint(g: Graphics) {
        g.color = Color(50, 50, 50)
        g.fillRect(x, y, width, height)
        g.color = Color.WHITE
        g.font = Font("Arial", Font.BOLD, 20)
        g.drawString("Score: $score", x + 10, y + 20)
        g.drawString("High Score: $highScore", x + 10, y + 40)
        g.drawString("Level: $level", x + 10, y + 60)
    }

    fun setScore(score: Int) {
        this.score = score
        if (this.score > highScore) {
            setHighScore(this.score)
        }
    }

    fun addScore(score: Int) {
        this.score += score
        if (this.score > highScore) {
            setHighScore(this.score)
        }
    }

    fun setHighScore(highScore: Int) {
        this.highScore = highScore
    }

    fun setLevel(level: Int) {
        this.level = level
    }
}
