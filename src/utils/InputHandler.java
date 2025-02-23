package utils;

import models.Block;
import models.Board;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    public static File promptFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan path dari file test case: ");
        String filePath = scanner.nextLine();
        filePath = "test/" + filePath;
        File file = new File(filePath);
        return file.exists() ? file : null;
    }

    public static List<String> readFile(File file) {
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public static Board parseBoard(List<String> lines) {
        String[] dimensions = lines.getFirst().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int pieces = Integer.parseInt(dimensions[2]);

        String type = lines.get(1);
        return new Board(rows, cols, pieces, type);
    }

    public static List<Block> parseBlock(List<String> lines) {
        List<Block> blocks = new ArrayList<>();
        int i = 2;

        while (i < lines.size()) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) {
                i++;
                continue;
            }

            char symbol = line.charAt(0);

            List<String> shape = new ArrayList<>();
            while (i < lines.size()) {
                line = lines.get(i);
                if (line.trim().isEmpty()) {
                    break;
                }

                boolean isBlockLine = true;
                for (int j = 0; j < line.length(); j++) {
                    if (line.charAt(j) != ' ' && line.charAt(j) != symbol) {
                        isBlockLine = false;
                        break;
                    }
                }

                if (!isBlockLine) {
                    if (line.trim().charAt(0) != symbol) {
                        break;
                    }
                }

                shape.add(line);
                i++;
            }

            blocks.add(new Block(symbol, shape));
        }

//        // Testing: Cetak semua blok
//        System.out.println("TESTING:");
//        for (Block block : blocks) {
//            System.out.println("Blok " + block.getSymbol() + ":");
//            boolean[][] shape = block.getShape();
//            for (boolean[] row : shape) {
//                for (boolean cell : row) {
//                    System.out.print(cell ? block.getSymbol() : '_');
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        return blocks;
    }

}
