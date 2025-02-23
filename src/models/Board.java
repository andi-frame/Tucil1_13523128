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
                System.out.print(cell == 0? '_' : cell);
            }
            System.out.println();
        }
    }
}
