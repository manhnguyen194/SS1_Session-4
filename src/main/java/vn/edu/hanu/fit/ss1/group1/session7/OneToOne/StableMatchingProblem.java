package vn.edu.hanu.fit.ss1.group1.session7.OneToOne;

import java.util.Random;
import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;

public class StableMatchingProblem extends AbstractProblem {
    private final int[][] menPreferences;
    private final int[][] womenPreferences;
    private final Random random;  // Add random generator

    public StableMatchingProblem(int[][] menPreferences, int[][] womenPreferences) {
        super(menPreferences.length, 1); // One objective: stability
        this.menPreferences = menPreferences;
        this.womenPreferences = womenPreferences;
        this.random = new Random();  // Initialize random generator
    }

    @Override
    public void evaluate(Solution solution) {
        Matching matching = (Matching) solution.getVariable(0);
        int instability = 0;

        // Calculate number of blocking pairs (instability)
        for (int man = 0; man < menPreferences.length; man++) {
            int woman = matching.getMatchedPartner(man);
            for (int preferredWoman : menPreferences[man]) {
                if (isBlockingPair(man, woman, preferredWoman, matching)) {
                    instability++;
                }
            }
        }

        solution.setObjective(0, instability);
    }

    private boolean isBlockingPair(int man, int woman, int preferredWoman, Matching matching) {
        int matchedMan = matching.getMatchedPartner(preferredWoman);
        return prefers(preferredWoman, man, matchedMan) && prefers(man, preferredWoman, woman);
    }

    private boolean prefers(int person, int option1, int option2) {
        for (int option : person == 0 ? menPreferences[person] : womenPreferences[person]) {
            if (option == option1) return true;
            if (option == option2) return false;
        }
        return false;
    }

    @Override
    public Solution newSolution() {
        int[] matching = new int[menPreferences.length];
        for (int i = 0; i < matching.length; i++) {
            matching[i] = random.nextInt(menPreferences.length); // Use random to initialize matching
        }
        Solution solution = new Solution(1, 1);
        solution.setVariable(0, new Matching(matching));
        return solution;
    }
}

