package net.alexsannd;

import java.awt.*;

public class Coordinate {

    //Instansvariabler (klassvariabler, objektvariabler)
    int xPos, yPos;
    Color color;

    //Konstruktor
    public Coordinate(int xPos, int yPos, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    public void move(int dx, int dy){
        xPos += dx;
        yPos += dy;
    }

    public void printCoordinate(){
        System.out.println("xPos: " + xPos);
        System.out.println("yPos: " + yPos);
        System.out.println("Color: " + color);
    }
}