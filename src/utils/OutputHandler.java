package utils;

import models.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputHandler {
    private final boolean solved;
    private final long time;
    private final int iterations;

    public OutputHandler(boolean solved, long time, int iterations) {
        this.solved = solved;
        this.time = time;
        this.iterations = iterations;
    }

    public boolean saveSolution(String filePath, Board board) {
        File outputDir = new File("test/output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }


        try (FileWriter writer = new FileWriter(filePath)) {
            if (solved) {
                writer.write("Solusi Ditemukan!\n");
                for (int i = 0; i < board.getRows(); i++) {
                    for (int j = 0; j < board.getCols(); j++) {
                        char cell = board.getCell(i, j);
                        writer.write(cell == 0 ? '_' : cell);
                        writer.write(' ');
                    }
                    writer.write("\n");
                }
            } else {
                writer.write("Solusi tidak ditemukan!\n");
            }

            writer.write("Waktu pencarian: " + time + "ms\n");
            writer.write("Banyak kasus: " + iterations);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
