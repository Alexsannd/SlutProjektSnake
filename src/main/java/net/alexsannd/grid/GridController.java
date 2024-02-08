package net.alexsannd.grid;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridController {
    private final List<List<GridCell>> grid = new ArrayList<>();
    private int rows, columns;
    private int cellWidth, cellHeight;

    public GridController(int rows, int columns, int cellWidth, int cellHeight){
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        for(int i = 0; i < rows; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < columns; j++) {
                grid.get(i).add(new GridCell(i * cellWidth + 5 * i, j * cellHeight + 5 * j, cellWidth, cellHeight));
            }
        }
    }
    public GridController(int rows, int columns, int cellWidth, int cellHeight, Color color){
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        for(int i = 0; i < rows; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < columns; j++) {
                grid.get(i).add(new GridCell(i * cellWidth + 5 * i, j * cellHeight + 5 * j, cellWidth, cellHeight));
            }
        }
    }
    public GridController(int rows, int columns, int cellWidth, int cellHeight, int Gap){
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        for(int i = 0; i < rows; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < columns; j++) {
                grid.get(i).add(new GridCell(i * cellWidth + Gap * i, j * cellHeight + Gap * j, cellWidth, cellHeight));
            }
        }
    }
    public GridController(int rows, int columns, int cellWidth, int cellHeight, int Gap, Color color){
        this.rows = rows;
        this.columns = columns;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        for(int i = 0; i < rows; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < columns; j++) {
                grid.get(i).add(new GridCell(i * cellWidth + Gap * i, j * cellHeight + Gap * j, cellWidth, cellHeight));
            }
        }
    }
    public void paint(Graphics g) {
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                grid.get(i).get(j).paint(g);
            }
        }
    }

    public GridCell getCell(int x, int y){
        if (x >= grid.size() || y >= grid.get(x).size() || x < 0 || y < 0)
            return null;
        return grid.get(x).get(y);
    }
    public GridCell getCell(GridCell cell){
        return grid.stream()
                .flatMap(List::stream)
                .filter(c -> c.equals(cell))
                .findFirst()
                .orElse(null);

    }

    public int getRows(){
        return rows;
    }

    public int getColumns(){
        return columns;
    }

    public int getCellWidth(){
        return cellWidth;
    }

    public int getCellHeight(){
        return cellHeight;
    }
}
