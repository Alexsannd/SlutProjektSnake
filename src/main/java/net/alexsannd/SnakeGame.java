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
    //TODO 1
    //Lägg till variabler för storleken på ditt rutnät. Antalet rutor i x- och y-led
    // storleken på rutorna i pixlar, storleken på mellanrummet mellan rutorna i pixlar
    //TODO 2
    //Ändra så att det blir en matematiskt uträkning som räknar ut hur brett och högt fönstret behöver vara nedan
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
        gridController = new GridController(WIDTH/(30+3), HEIGHT/(30+3), 30, 30, 3, Color.WHITE);
        snake = new Snake(gridController);
        snake.setHead(gridController.getRows()/2, gridController.getColumns()/2);
        snake.addBodyPart();
        snake.addBodyPart();
    }

    public void setUp(){
        //TODO 4
        //Lägg till bitar till ArrayListan snake som ska representera vart på spelplanen du vill att ormen ska börja.
        //Använd samma numrering på Coordinates xPos och yPos som du använde dig av när du ritade ut rutnätet.
        //Skapa även ett äpple och bestäm vart det första äpplet ska bli placerat i början av spelet.
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
        //TODO 3
        //Välj en färg på ditt rutnät genom g.setColor, använd sedan två nästlade for.loopar för att rita ut ett rutnät
        //på skärmen. Se dokumentet "Utritande av rutnät" på classroom.

        //TODO 5
        //Välj en färg för ormen och rita sedan ut hela ormen på skärmen, detta gör du genom att skapa en for-loop som
        //går igenom ArrayListan snake och ritar ut en ruta med samma formel som för rutnätet, men istället för i och j
        //så använer du dig av varje xPos och yPos för ormens delar. Du kommer märka att du har gjort rätt om ormens
        // bitar ligger precis på ditt rutnäts bitar.
        gridController.paint(g);
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

        //TODO 6
        //Med hjälp av en for-loop som börjar i slutet av ArrayListan och rör sig bakåt (mot i = 0) så vill vi gå
        // igenom ArrayListan och flytta varje bit av ormen så det ser ut som att den rör på sig. När varje bit av
        // svansen (allt som inte är ett huvud). Har flyttat på sig så är det dags att flytta på huvudet. För att göra
        // det så behöver vi ha en variabel som håller koll på åt vilket håll huvudet ska röra sig. Jag rekommednerar
        // att använda en int och låta 1,2,3 och 4 representera de fyra riktningarna som den kan röra sig åt.
        // Efter for-loopen, gör fyra if-satser som flyttar huvudet åt rätt håll beroende på värdet på variabeln.

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