package org.example;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    GameBoard board;

    TicTacToeGame() {
        initializeGame();
    }

    private void initializeGame() {
        players = new LinkedList<>();
        PlayingPiece xpiece = new PlayingPiece(PieceType.X);
        Player player1 = new Player("player1", xpiece);

        PlayingPiece opiece = new PlayingPiece(PieceType.O);
        Player player2 = new Player("Player2", opiece);
        players.add(player1);
        players.add(player2);

        board = new GameBoard(3);
    }

    public String startGame() {
        boolean noWinner = true;
        while(noWinner) {
            Player playerTurn = players.removeFirst();

            board.printBoard();
            System.out.println(playerTurn.getName() + " enter row, col:");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] iValues = input.split(",");
            int row =  Integer.parseInt(iValues[0]);
            int col =  Integer.parseInt(iValues[1]);

            boolean isAdded = board.addPiece(row, col, playerTurn.getPiece());
            if (!isAdded) {
                System.out.println("That location is already take use some other location.");
                players.addFirst(playerTurn);
                continue;
            }
            players.addLast(playerTurn);

            boolean isWinner = isThereWinner(row, col, playerTurn.getPiece());
            if (isWinner) {
                return playerTurn.getName();
            }
        }

        return "Tie";
    }

    private boolean isThereWinner(int row, int col, PlayingPiece piece) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        for (int i = 0; i < board.sz; i++) {
            if (board.board[row][i] == null || board.board[row][i] != piece)
                rowMatch = false;
        }
        for (int i = 0; i < board.sz; i++) {
            if (board.board[i][col] == null || board.board[i][col] != piece)
                colMatch = false;
        }
        for (int i = 0, j = 0; i < board.sz && j < board.sz; i++, j++) {
            if (i == j && (board.board[i][j] == null || board.board[i][j] != piece))
                diagonalMatch = false;
        }
        for (int i = 0, j = board.sz - 1; i < board.sz; i++, j--) {
            if (board.board[i][j] == null || board.board[i][j] != piece)
                antiDiagonalMatch = false;
        }

        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;
    }

}
