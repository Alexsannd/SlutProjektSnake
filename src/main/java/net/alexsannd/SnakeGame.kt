package net.alexsannd

import net.alexsannd.grid.Food
import net.alexsannd.grid.GridController
import net.alexsannd.grid.Snake
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.Timer

class SnakeGame(window: JFrame) : JPanel(), ActionListener, KeyListener {
    var snake: Snake
    var direction: Snake.Direction = Snake.Direction.RIGHT
    var lastDirection: Snake.Direction = Snake.Direction.RIGHT

    var gridController: GridController
    var running: Boolean = false
    var gameOver: Boolean = false
    var gameStart: Boolean = false
    var time: Double = 0.0
    var food: MutableList<Food> = ArrayList()
    var timestep: Int = 150

    //150 is standard speed
    //Make a speed multiplayer to change speed
    var cellWidth: Int = 30
    var cellHeight: Int = 30
    var Gap: Int = 3

    var hud: HUD
    var WIDTH: Int = 1000
    var HEIGHT: Int = 600
    var window: JFrame
    var tm: Timer = Timer(timestep, this)

    init {
        preferredSize = Dimension(WIDTH, HEIGHT)
        isFocusable = true
        requestFocus()
        this.window = window
        gridController = GridController(
            WIDTH / (cellWidth + Gap),
            HEIGHT / (cellHeight + Gap) - 3,
            cellWidth,
            cellHeight,
            Gap,
            Color.black
        )
        WIDTH = gridController.width
        preferredSize = Dimension(WIDTH, HEIGHT)
        snake = Snake(gridController)
        resetSnake()
        hud = HUD(20, gridController.height, WIDTH - 40, HEIGHT - gridController.height - 20)
        gameStart = true
    }

    fun setUp() {
        addKeyListener(this)
        isFocusable = true
        focusTraversalKeysEnabled = false
        tm.start()
    }

    fun resetSnake() {
        snake.reset()
        snake.setHead(gridController.rows / 2, gridController.columns / 2)
        snake.addBodyPart(gridController.rows / 2, gridController.columns / 2 + 1)
    }

    fun randomNumber(max: Int): Int {
        return (Math.random() * max + 1).toInt()
    }

    override fun paintComponent(g: Graphics) {
        g.color = Color.BLACK
        g.fillRect(0, 0, WIDTH, HEIGHT)

        gridController.paint(g)
        hud.paint(g)

        if (gameOver) {
            displayGameOver(g)
        }
    }

    fun displayGameOver(g: Graphics) {
        g.color = Color(0, 0, 0, 150)
        g.fillRect(0, 0, width, height)

        // Set the color and font for the game over message
        g.color = Color.RED
        g.font = Font("Arial", Font.BOLD, 50)

        // Calculate the x and y coordinates to center the game over message
        var metrics = g.getFontMetrics(g.font)
        var x = (width - metrics.stringWidth("GAME OVER!")) / 2
        var y = ((height - metrics.height) / 2) + metrics.ascent

        // Draw the game over message
        g.drawString("GAME OVER!", x, y)

        // Set the color and font for the restart instruction
        g.color = Color.WHITE
        g.font = Font("Arial", Font.PLAIN, 20)

        // Calculate the x and y coordinates to center the restart instruction
        metrics = g.getFontMetrics(g.font)
        x = (width - metrics.stringWidth("Press any button to restart")) / 2
        y = ((height - metrics.height) / 2) + metrics.ascent + 60

        // Draw the restart instruction
        g.drawString("Press any button to restart", x, y)
    }

    override fun actionPerformed(actionEvent: ActionEvent) {
        time += timestep.toDouble()
        if (!running) return
        if (snake.isMoveDirectionValid(direction)) {
            lastDirection = direction
        } else {
            direction = lastDirection
        }

        if (snake.checkCollision() || !snake.move(direction)) {
            println("Game Over!")
            gameOver = true
            running = false
            tm.stop()
        }
        if (food.isEmpty()) {
            val x = randomNumber(gridController.rows - 1)
            val y = randomNumber(gridController.columns - 1)
            val f = Food(x, y, Food.FoodType.APPLE, gridController)
            food.add(f)
        }
        for (f in food) {
            if (f.position == snake.head) {
                snake.addBodyPart()
                hud.addScore(f.points)
                food.remove(f)
                break
            }
        }

        repaint()
    }

    override fun keyTyped(keyEvent: KeyEvent) {
    }

    override fun keyPressed(keyEvent: KeyEvent) {
        println("Key Pressed!")
        if (gameStart) {
            gameStart = false
            running = true
        }
        when (keyEvent.keyCode) {
            KeyEvent.VK_UP -> {
                println("UP")
                direction = Snake.Direction.UP
            }

            KeyEvent.VK_DOWN -> {
                println("DOWN")
                direction = Snake.Direction.DOWN
            }

            KeyEvent.VK_LEFT -> {
                println("LEFT")
                direction = Snake.Direction.LEFT
            }

            KeyEvent.VK_RIGHT -> {
                println("RIGHT")
                direction = Snake.Direction.RIGHT
            }

            KeyEvent.VK_ESCAPE -> if (running) {
                running = false
            } else {
                running = true
                direction = lastDirection
            }
        }
        if (gameOver) {
            gameOver = false
            gameStart = true
            running = false
            resetSnake()
            hud.setScore(0)
            tm.start()
        }
        repaint()
    }

    override fun keyReleased(keyEvent: KeyEvent) {
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val window = JFrame("Slutprojekt - Programmering 1 - Snake")
            val game = SnakeGame(window)
            window.contentPane = game
            window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            window.isResizable = false
            window.pack()
            window.setLocationRelativeTo(null)
            window.isVisible = true
            game.setUp()
        }
    }
}