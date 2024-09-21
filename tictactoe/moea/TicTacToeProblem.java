package com.tictactoe.moea;

import org.moeaframework.core.*;
import org.moeaframework.problem.AbstractProblem;

public class TicTacToeProblem extends AbstractProblem {

    public static final int MAX_MOVES = 25;  // Maximum number of moves in a game

    public TicTacToeProblem() {
        super(1, 2);  // 1 variable (game), 2 objectives (win/lose, game length)
    }

    @Override
    public Solution newSolution() {
        Solution solution = new Solution(1, 2);  // 1 variable, 2 objectives
        Game game = new Game();

        // Simulate random moves between Player X and O
        for (int i = 0; i < MAX_MOVES; i++) {
            int row = (int) (Math.random() * game.BOARD_SIZE);
            int col = (int) (Math.random() * game.BOARD_SIZE);

            if (game.makeMove(row, col, game.currentPlayer)) {
                if (game.checkWin(game.currentPlayer)) {
                    break;  // Stop if a win is found
                }
            }
        }

        solution.setVariable(0, game);
        return solution;
    }

    @Override
    public void evaluate(Solution solution) {
        Game game = CustomEncodingUtils.getGame(solution.getVariable(0));

        // Objective 1: Win or Lose
        boolean win = game.checkWin('X');  // Check if Player 'X' wins
        solution.setObjective(0, win ? 0 : 1);  // Objective 0: Minimize if win

        // Objective 2: Game Length
        int gameLength = calculateGameLength(game);
        solution.setObjective(1, gameLength);  // Objective 1: Minimize game length
    }

    private int calculateGameLength(Game game) {
        // Calculate the length of the game based on how many moves were made
        int length = 0;
        for (int i = 0; i < game.BOARD_SIZE; i++) {
            for (int j = 0; j < game.BOARD_SIZE; j++) {
                if (game.board[i][j] != '-') {
                    length++;
                }
            }
        }
        return length;
    }
}
