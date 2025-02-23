package algorithm;

import models.Block;
import models.Board;

import java.util.List;

public class BruteForce {
    private final Board board;
    private final List<Block> blocks;
    private int iterations = 0;
    private boolean running = true;

    public BruteForce(Board board, List<Block> blocks) {
        this.board = board;
        this.blocks = blocks;
    }

    public void solve() {
        long start = System.currentTimeMillis();
        boolean solved = solveRecursive(0);
        long end = System.currentTimeMillis();

        if (solved) {
            System.out.println("Solusi Ditemukan!");
            board.printBoard();
        } else {
            System.out.println("Solusi tidak ditemukan!");
        }

        System.out.println("Waktu pencarian: " + (end - start) + "ms");
        System.out.println("Banyak kasus: " + iterations);
    }

    private boolean solveRecursive(int blockIndex) {
        if (blockIndex >= blocks.size()) {
            return true;
        }

        Block block = blocks.get(blockIndex);
        List<boolean[][]> orientations = block.getAllOrientations();

        for (boolean[][] orientation : orientations) {
            for (int row = 0; row < board.getRows(); row++) {
                for (int col = 0; col < board.getCols(); col++) {
                    if (canPlace(orientation, row, col)) {
                        place(orientation, block.getSymbol(), row, col);
                        iterations++;

                        if (solveRecursive(blockIndex + 1)) {
                            return true;
                        }
                        remove(orientation, row, col);
                    }
                }
            }
        }
        return false;
    }

    private boolean canPlace(boolean[][] shape, int startRow, int startCol) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] && (startRow + i >= board.getRows() || startCol + j >= board.getCols() || board.getCell(startRow + i, startCol + j) != 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void place(boolean[][] shape, char symbol, int startRow, int startCol) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    board.setCell(startRow + i, startCol + j, symbol);
                }
            }
        }
    }

    private void remove(boolean[][] shape, int startRow, int startCol) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j]) {
                    board.setCell(startRow + i, startCol + j, (char) 0);
                }
            }
        }
    }
}
