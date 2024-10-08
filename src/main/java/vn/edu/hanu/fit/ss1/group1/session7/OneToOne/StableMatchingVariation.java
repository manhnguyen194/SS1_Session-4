package vn.edu.hanu.fit.ss1.group1.session7.OneToOne;

import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import java.util.Random;

public class StableMatchingVariation implements Variation {
    private final Random random;  // Add random generator

    public StableMatchingVariation() {
        this.random = new Random();  // Initialize random generator
    }

    @Override
    public Solution[] evolve(Solution[] parents) {
        Solution child = parents[0].copy();
        Matching matching = (Matching) child.getVariable(0);

        // Example Mutation: Swap two partners randomly
        int person1 = random.nextInt(matching.getMatching().length);  // Use matching.length instead of matching.size()
        int person2 = random.nextInt(matching.getMatching().length);
        int temp = matching.getMatchedPartner(person1);
        matching.setMatchedPartner(person1, matching.getMatchedPartner(person2));
        matching.setMatchedPartner(person2, temp);

        return new Solution[]{child};
    }
    @Override
    public String getName() {
        return "Matching Problem";
    }
    @Override
    public int getArity() {
        return 1; // Since this is a mutation operator, it operates on a single parent
    }
}
