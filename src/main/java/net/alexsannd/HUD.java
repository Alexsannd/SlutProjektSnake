package net.alexsannd;

import java.awt.*;

public class HUD {
    private int score;
    private int highScore;
    private int level;
    private int width;
    private int height;
    private int x;
    private int y;

    public HUD(int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.score = 0;
        this.highScore = 0;
        this.level = 1;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(50, 50, 50));
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, x + 10, y + 20);
        g.drawString("High Score: " + highScore, x + 10, y + 40);
        g.drawString("Level: " + level, x + 10, y + 60);
    }

    public void setScore(int score) {
        this.score = score;
        if (this.score > highScore) {
            highScore = this.score;
        }
    }
    public void addScore(int score) {
        this.score += score;
        if (this.score > highScore) {
            highScore = this.score;
        }

    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
