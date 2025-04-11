package org.example;

public class GameBoard {
    int sz;
    PlayingPiece[][] board;

    GameBoard(int sz) {
        this.sz = sz;
        board = new PlayingPiece[sz][sz];
    }


    public boolean addPiece(int row, int col, PlayingPiece piece) {
        if (board[row][col] != null) return false;
        board[row][col] = piece;
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < sz; i++) {
            for (int j = 0 ; j < sz; j++) {
                if (board[i][j] == null) System.out.print("_ ");
                else System.out.print(board[i][j].pieceType+ " ");
            }
            System.out.println();
        }
    }
}
