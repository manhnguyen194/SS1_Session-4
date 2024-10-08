package vn.edu.hanu.fit.ss1.group1.session7.OneToOne;

import org.moeaframework.core.Variable;
import org.moeaframework.core.variable.EncodingUtils;

import java.util.Arrays;
import java.util.Random;

public class Matching implements Variable {
    private int[] matching;

    public Matching(int[] matching) {
        this.matching = matching.clone();
    }

    public int getMatchedPartner(int i) {
        return matching[i];
    }

    public void setMatchedPartner(int i, int partner) {
        matching[i] = partner;
    }
    public int[] getMatching() {
        return matching;
    }

    @Override
    public Variable copy() {
        return new Matching(matching);
    }

    @Override
    public String toString() {
        return Arrays.toString(matching);
    }

    // Randomize the matching by assigning random partners
    @Override
    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < matching.length; i++) {
            matching[i] = random.nextInt(matching.length); // Randomly assign partners
        }
    }

    // Encode the matching into an array of integers
    @Override
    public String encode() {
        // Converts the int[] matching array to a comma-separated String
        return Arrays.toString(matching).replaceAll("[\\[\\] ]", ""); // E.g., "1,2,3"
    }

    // Decode the encoded array of integers back into a matching
    @Override
    public void decode(String data) {
        // Splits the string and converts it back to an int[] array
        String[] parts = data.split(",");
        for (int i = 0; i < parts.length; i++) {
            matching[i] = Integer.parseInt(parts[i]);
        }
    }
}
