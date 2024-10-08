package vn.edu.hanu.fit.ss1.group1.session7.OneToOne;

import org.moeaframework.Executor;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;

public class Main {
    public static void main(String[] args) {
        int[][] menPreferences = {
                {0, 1, 2}, // Man 0 prefers Woman 0, 1, 2
                {2, 0, 1}, // Man 1 prefers Woman 2, 0, 1
                {1, 0, 2}  // Man 2 prefers Woman 1, 0, 2
        };

        int[][] womenPreferences = {
                {0, 1, 2}, // Woman 0 prefers Man 0, 1, 2
                {2, 0, 1}, // Woman 1 prefers Man 2, 0, 1
                {1, 0, 2}  // Woman 2 prefers Man 1, 0, 2
        };

        // Create the problem instance
        StableMatchingProblem problem = new StableMatchingProblem(menPreferences, womenPreferences);

        // Create the executor and set up the algorithm
        Executor executor = new Executor()
                .withProblem(problem)
                .withAlgorithm("NSGAIII")
                .withMaxEvaluations(10000);

        // Set up variation operators directly in the algorithm (this example does not use variations)
        // Since NSGA-III does not support direct variations in Executor, you need to handle them in the problem class.

        // Run the executor
        NondominatedPopulation result = executor.run(); // The result is directly returned by run()

        // Display the results
        System.out.println("Best solutions:");
        for (Solution solution : result) {
            System.out.println(solution);
        }
    }
}
