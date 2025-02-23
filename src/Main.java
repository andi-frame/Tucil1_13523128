import algorithm.BruteForce;
import models.Block;
import models.Board;
import utils.InputHandler;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = InputHandler.promptFile();
        if (file == null) {
            System.out.println("File tidak ditemukan!");
            return;
        }

        List<String> lines = InputHandler.readFile(file);
        if (lines.isEmpty()) {
            System.out.println("File kosong!");
            return;
        }

        Board board = InputHandler.parseBoard(lines);
        List<Block> blocks = InputHandler.parseBlock(lines);

        BruteForce bf = new BruteForce(board, blocks);
        bf.solve();
    }
}