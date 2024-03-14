package net.alexsannd;

import net.alexsannd.grid.Food;
import net.alexsannd.grid.GridController;
import net.alexsannd.grid.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    Snake snake;
    Snake.Direction direction = Snake.Direction.RIGHT;
    Snake.Direction lastDirection = Snake.Direction.RIGHT;

    GridController gridController;
    boolean running = false, gameOver = false;
    double time = 0;
    List<Food> food = new ArrayList<>();
    int timestep = 250;

    int cellWidth = 30, cellHeight = 30, Gap = 3;

    HUD hud;
    int WIDTH = 1000, HEIGHT = 600;
    JFrame window;
    Timer tm = new Timer(timestep, this);

    public static void main(String[] args) {
        JFrame window = new JFrame("Slutprojekt - Programmering 1 - Snake");
        SnakeGame game = new SnakeGame(window);
        window.setContentPane(game);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        game.setUp();

    }

    public SnakeGame(JFrame window){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        this.window = window;
        gridController = new GridController(WIDTH/(cellWidth+Gap), HEIGHT/(cellHeight+Gap)-3, cellWidth, cellHeight, Gap, Color.black);
        WIDTH = gridController.getWidth();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        snake = new Snake(gridController);
        resetSnake();
        hud = new HUD(20, gridController.getHeight(), WIDTH-40, HEIGHT - gridController.getHeight()-20);
    }

    public void setUp(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        tm.start();

    }
    void resetSnake(){
        snake.reset();
        snake.setHead(gridController.getRows()/2, gridController.getColumns()/2);
    }

    public int randomNumber(int max){
        return (int)(Math.random()*max+1);
    }

    @Override
    protected void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        gridController.paint(g);
        hud.paint(g);

        if (gameOver) {
            displayGameOver(g);
        }
    }
    public void displayGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        // Set the color and font for the game over message
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));

        // Calculate the x and y coordinates to center the game over message
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - metrics.stringWidth("GAME OVER!")) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        // Draw the game over message
        g.drawString("GAME OVER!", x, y);

        // Set the color and font for the restart instruction
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));

        // Calculate the x and y coordinates to center the restart instruction
        metrics = g.getFontMetrics(g.getFont());
        x = (getWidth() - metrics.stringWidth("Press any button to restart")) / 2;
        y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent() + 60;

        // Draw the restart instruction
        g.drawString("Press any button to restart", x, y);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        time += timestep;
        if (running) {
            if (snake.isMoveDirectionValid(direction)) {
                lastDirection = direction;
            } else {
                direction = lastDirection;
            }

            if (snake.checkCollision() || !snake.move(direction)) {
                System.out.println("Game Over!");
                gameOver = true;
                running = false;
                tm.stop();
            }
            if (food.isEmpty()) {
                int x = randomNumber(gridController.getRows()-1);
                int y = randomNumber(gridController.getColumns()-1);
                Food f = new Food(x, y, Food.FoodType.APPLE, gridController);
                food.add(f);
            }
            for (Food f : food) {
                if (f.getPosition().equals(snake.getHead())) {
                    snake.addBodyPart();
                    hud.addScore(f.getPoints());
                    food.remove(f);
                    break;
                }
            }

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        System.out.println("Key Pressed!");
        running = true;
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                System.out.println("UP");
                direction = Snake.Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                direction = Snake.Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                direction = Snake.Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                direction = Snake.Direction.RIGHT;
                break;
        }
        if (gameOver) {
            gameOver = false;
            running = false;
            resetSnake();
            hud.setScore(0);
            tm.start();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}