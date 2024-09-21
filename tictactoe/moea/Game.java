package com.tictactoe.moea;

import org.moeaframework.core.Variable;

import java.util.Random;

public class Game implements Variable {

    public char[][] board;
    public final int BOARD_SIZE = 5;  //[5x5] board
    public char currentPlayer;
    private Random random;

    public Game() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        random = new Random();
        initializeBoard();
        currentPlayer = random.nextBoolean() ? 'X' : 'O';  // Choose random X or O to start
    }
    public char[][] getBoard() {
        return board;
    }
    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Encode the board state to a string or some encoded form
    public String encode() {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                encoded.append(board[i][j]);
            }
        }
        return encoded.toString();  // Encoded state as string
    }

    // Decode from the encoded string back into the board
    public void decode(String encodedState) {
        int index = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = encodedState.charAt(index++);
            }
        }
    }

    // Player makes a move
    public boolean makeMove(int row, int col, char player) {
        if (board[row][col] == '-') {  // If the cell is empty
            board[row][col] = player;
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch player
            return true;
        }
        return false;
    }

    public boolean checkWin(char player) {
        // Check all rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean rowWin = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] != player) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;  // Return true if row contains all the same player
        }

        // Check all columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            boolean colWin = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[j][i] != player) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;  // Return true if column contains all the same player
        }

        // Check diagonal (top-left to bottom-right)
        boolean diagonal1Win = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] != player) {
                diagonal1Win = false;
                break;
            }
        }
        if (diagonal1Win) return true;

        // Check diagonal (top-right to bottom-left)
        boolean diagonal2Win = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][BOARD_SIZE - i - 1] != player) {
                diagonal2Win = false;
                break;
            }
        }
        if (diagonal2Win) return true;

        // If no win condition is met, return false
        return false;
    }

    // Randomize the board with random moves (used for initializing a solution)
    @Override
    public void randomize() {
        initializeBoard();
        currentPlayer = random.nextBoolean() ? 'X' : 'O';  // Randomly assign who goes first

        // Randomly place Xs and Os until a certain number of moves
        int numberOfMoves = random.nextInt(BOARD_SIZE * BOARD_SIZE);  // Random number of moves
        for (int i = 0; i < numberOfMoves; i++) {
            int row, col;
            do {
                row = random.nextInt(BOARD_SIZE);
                col = random.nextInt(BOARD_SIZE);
            } while (board[row][col] != '-');  // Ensure the cell is empty

            board[row][col] = currentPlayer;
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch player after each move
        }
    }

    @Override
    public Game copy() {
        Game copy = new Game();
        copy.decode(this.encode());  // Create a deep copy using encode/decode
        return copy;
    }
}
