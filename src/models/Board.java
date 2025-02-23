package models;

public class Board {
    private final int rows;
    private final int cols;
    private char[][] board;

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
    }

    public void printBoard(){
        for (char[] row: board){
            for (char cell: row) {
                System.out.print(cell == 0? '_' : cell);
            }
            System.out.println();
        }
    }
}
