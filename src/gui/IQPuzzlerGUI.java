package gui;

import algorithm.BruteForce;
import models.Block;
import models.Board;
import utils.InputHandler;
import utils.OutputHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class IQPuzzlerGUI extends JFrame {
    private JPanel panelMain;
    private JTable outputArea;
    private JTextField txtInputPath;
    private JButton solveButton;
    private JTextField txtOutputPath;
    private JButton saveButton;
    private JLabel labelStatistik;
    private JLabel labelSolved;
    private JLabel labelTitle;
    private JPanel panelInput;
    private JPanel panelOutput;
    private JScrollPane scrollTable;

    private Board board;
    private List<Block> blocks;
    private boolean solved;
    private int time;
    private int iterations;

    public IQPuzzlerGUI() {
        setContentPane(panelMain);
        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        labelTitle.setFont(titleFont);
        setTitle("Andi Frame's IQ Puzzler Pro GUI");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        panelOutput.setVisible(false);
        outputArea.setDefaultRenderer(Object.class, new NewCellRenderer());
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelSolved.setText("Loading...");
                solveButton.setEnabled(false);
                saveButton.setEnabled(false);

                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        File inputFile = InputHandler.promptFileGUI(txtInputPath.getText());
                        if (inputFile == null) {
                            JOptionPane.showMessageDialog(null, "File tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                            labelSolved.setText("");
                            return null;
                        }

                        java.util.List<String> lines = InputHandler.readFile(inputFile);
                        if (lines.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "File kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                            labelSolved.setText("");
                            return null;
                        }

                        board = InputHandler.parseBoard(lines);
                        blocks = InputHandler.parseBlock(lines);

                        BruteForce bf = new BruteForce(board, blocks);
                        long startTime = System.currentTimeMillis();
                        solved = bf.solveRecursive(0);
                        long endTime = System.currentTimeMillis();
                        time = (int) (endTime - startTime);
                        iterations = bf.getIterations();

                        return null;
                    }

                    @Override
                    protected void done() {
                        // Update the GUI after the solver finishes
                        if (solved) {
                            labelSolved.setText("Solusi Ditemukan!\n");
                            updateTable(board);
                            panelOutput.setVisible(true);
                        } else {
                            labelSolved.setText("Solusi tidak ditemukan!\n");
                        }
                        labelStatistik.setText(time + "ms dengan " + iterations + " percobaan.");
                        Font descFont = new Font("SansSerif", Font.PLAIN, 12);
                        labelStatistik.setFont(descFont);
                        solveButton.setEnabled(true);
                        saveButton.setEnabled(true);
                    }
                };

                worker.execute();
            }
        });


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board == null) {
                    JOptionPane.showMessageDialog(null, "Tidak ada solusi untuk disimpan!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String fileName = txtOutputPath.getText();
                String filePath = "test/output/" + fileName;
                OutputHandler outputHandler = new OutputHandler(solved, time, iterations);
                boolean saved = outputHandler.saveSolution(filePath, board);

                if (saved) {
                    JOptionPane.showMessageDialog(null, "Solusi berhasil disimpan di: " + filePath, "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menyimpan solusi.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateTable(Board board) {
        DefaultTableModel model = new DefaultTableModel(board.getRows(), board.getCols());

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                char cell = board.getCell(i, j);
                model.setValueAt(cell == 0 ? "" : cell, i, j);
            }
        }

        outputArea.setModel(model);

        JTableHeader header = outputArea.getTableHeader();
        header.setVisible(false);

        outputArea.setRowHeight(30);
        for (int i = 0; i < outputArea.getColumnCount(); i++) {
            outputArea.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new IQPuzzlerGUI().setVisible(true));
    }
}
