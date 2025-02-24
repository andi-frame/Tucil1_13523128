package models;

public class Board {
    private final int rows;
    private final int cols;
    private final int pieces;
    private final String type;
    private char[][] board;

    public Board(int rows, int cols, int pieces, String type){
        this.rows = rows;
        this.cols = cols;
        this.pieces = pieces;
        this.type = type;
        this.board = new char[rows][cols];
    }

    public void printBoard(){
        for (char[] row: board){
            for (char cell: row) {
                System.out.print(cell == 0? '_' : cell + " ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, char value) {
        board[row][col] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = board[i][j];
                sb.append(cell == 0 ? '_' : cell).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
