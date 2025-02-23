package models;

import java.util.ArrayList;
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

    public List<boolean[][]> getAllOrientations() {
        List<boolean[][]> orientations = new ArrayList<>();
        boolean[][] currentShape = this.shape;

        for (int i = 0; i < 4; i++) {
            orientations.add(currentShape);
            orientations.add(mirror(currentShape));
            currentShape = rotate90(currentShape);
        }

        return orientations;
    }

    private boolean[][] rotate90(boolean[][] shape) {
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] rotated = new boolean[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = shape[i][j];
            }
        }

        return rotated;
    }

    private boolean[][] mirror(boolean[][] shape) {
        int rows = shape.length;
        int cols = shape[0].length;
        boolean[][] mirrored = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mirrored[i][cols - 1 - j] = shape[i][j];
            }
        }

        return mirrored;
    }
}
