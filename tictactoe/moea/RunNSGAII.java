package com.tictactoe.moea;

import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.operator.UniformCrossover;
import org.moeaframework.core.operator.CompoundVariation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;

public class RunNSGAII {

    public static void main(String[] args) {
        // Define the problem (TicTacToeProblem you have created)
        TicTacToeProblem problem = new TicTacToeProblem();

        // Create the crossover and mutation operators
        UniformCrossover crossover = new UniformCrossover(0.9);  // 90% crossover rate
        BitFlipMutation mutation = new BitFlipMutation(1.0 / TicTacToeProblem.MAX_MOVES); // Mutation rate based on number of moves

        // Combine crossover and mutation into a single variation operator
        Variation variation = new CompoundVariation(crossover, mutation);

        // Configure and create the NSGAII algorithm
        Algorithm algorithm = new NSGAII(
                problem
        );
        ((NSGAII) algorithm).setVariation(variation);
        // Run the algorithm for a fixed number of evaluations
        while (algorithm.getNumberOfEvaluations() < 10000) {
            algorithm.step();  // Execute a step of the algorithm
        }

        // Get the result population
        NondominatedPopulation result = algorithm.getResult();

        // Print the results (final board state, win/loss, game length)
        for (Solution solution : result) {
            Game game = CustomEncodingUtils.getGame(solution.getVariable(0));  // Decode game from solution
            printBoard(game.getBoard());
            System.out.println("Player win: " + solution.getObjective(0));     // Objective 0: win or lose
            System.out.println("Game length: " + solution.getObjective(1));    // Objective 1: game length
        }

        // Terminate the algorithm
        algorithm.terminate();
    }
    private static void printBoard(char[][] board) {
        System.out.println("Final Board State:");
        System.out.println("-------------");
        for (char[] row : board) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell == '\0' ? " " : cell);  // Replace empty cells with a space
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
}
