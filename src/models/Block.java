package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block {
    private final char symbol;
    private final boolean[][] shape;
    private List<boolean[][]> orientationsStore;

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
        if (orientationsStore != null) {
            return orientationsStore;
        }

        Set<String> seenOrientations = new HashSet<>();
        List<boolean[][]> orientations = new ArrayList<>();
        boolean[][] currentShape = this.shape;

        for (int i = 0; i < 4; i++) {
            addUniqueOrientation(currentShape, orientations, seenOrientations);
            addUniqueOrientation(mirror(currentShape), orientations, seenOrientations);
            currentShape = rotate90(currentShape);
        }

        orientationsStore = orientations;
        return orientations;
    }

    private void addUniqueOrientation(boolean[][] shape, List<boolean[][]> orientations, Set<String> seenOrientations) {
        String shapeKey = shapeToString(shape);
        if (!seenOrientations.contains(shapeKey)) {
            orientations.add(shape);
            seenOrientations.add(shapeKey);
        }
    }

    private String shapeToString(boolean[][] shape) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : shape) {
            for (boolean cell : row) {
                sb.append(cell ? '1' : '0');
            }
            sb.append('|');
        }
        return sb.toString();
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