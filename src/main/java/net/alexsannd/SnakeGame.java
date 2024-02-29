package net.alexsannd;

import net.alexsannd.grid.GridController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeGame extends JPanel implements ActionListener, KeyListener {

    Snake snake;
    Snake.Direction direction = Snake.Direction.RIGHT;
    Snake.Direction lastDirection = Snake.Direction.RIGHT;

    GridController gridController;
    boolean running = false;
    double time = 0;
    int timestep = 1000;

    int cellWidth = 30, cellHeight = 30, Gap = 3;

    HUD hud;
    int WIDTH = 1000, HEIGHT = 800;
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
        gridController = new GridController(WIDTH/(cellWidth+Gap), HEIGHT/(cellHeight+Gap)-3, cellWidth, cellHeight, Gap, Color.WHITE);
        WIDTH = gridController.getWidth();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        snake = new Snake(gridController);
        snake.setHead(gridController.getRows()/2, gridController.getColumns()/2);
        snake.addBodyPart();
        snake.addBodyPart();
        hud = new HUD(20, gridController.getHeight()+20, WIDTH-40, HEIGHT - gridController.getHeight()-40);
    }

    public void setUp(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        tm.start();

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
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        time += timestep;
        System.out.println("Tick tock: " + time);
        if (running) {
            if (snake.isMoveDirectionValid(direction)) {
                lastDirection = direction;
            } else {
                direction = lastDirection;
            }
            snake.move(direction);

        }

        //TODO 7
        //Efter att alla bitar av ormen har rört på sig så behöver du kontrollera om ormen har hamnat utanför spelplanen
        // och markera detta på något sätt. Enklast är att du ifall huvudet ligger utanför spelplanen bara stänger av
        // timern genom att skriva tm.stop()

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
        //TODO 8
        //Använd tangenterna för att styra åt vilket håll huvudet ska rära sig. Använd variabeln du har skapat tidigare
        //och ge denna ett värde för rätt riktning.
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}