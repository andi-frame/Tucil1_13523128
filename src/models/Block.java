package models;

import java.util.List;

public class Block {
    private final char symbol;
    private final boolean[][] shape;

    public Block(char symbol, List<String> shape) {
        this.symbol = symbol;
        this.shape = parseShape(shape);
    }

    private boolean[][] parseShape(List<String> shape) {
        int rows = shape.size();
        int cols = shape.stream().mapToInt(String::length).max().orElse(0);
        boolean[][] shapeMatrix = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = shape.get(i);
            for (int j = 0; j < line.length(); j++) {
                shapeMatrix[i][j] = (line.charAt(j) != ' ');
            }
        }
        return shapeMatrix;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean[][] getShape() {
        return shape;
    }
}
