import algorithm.BruteForce;
import models.Block;
import models.Board;
import utils.InputHandler;
import utils.OutputHandler;

import java.io.File;
import java.util.List;
import java.util.Scanner;

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
        OutputHandler output = bf.solve();

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nApakah ingin menyimpan solusi? (ya/tidak): ");
        String saveOption = scanner.nextLine().trim().toLowerCase();

        if (saveOption.equals("ya")) {
            System.out.print("Masukkan cukup nama file untuk menyimpan solusi (tanpa .txt): ");
            String fileName = scanner.nextLine().trim();

            String outputPath = "test/output/" + fileName + ".txt";
            boolean saved = output.saveSolution(outputPath, board);

            if (saved) {
                System.out.println("Solusi berhasil disimpan di: " + outputPath);
            } else {
                System.out.println("Gagal menyimpan solusi.");
            }
        } else {
            System.out.println("Solusi tidak disimpan.");
        }
    }
}