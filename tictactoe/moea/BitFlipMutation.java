package com.tictactoe.moea;

import org.moeaframework.core.PRNG;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variable;
import org.moeaframework.core.operator.Mutation;
import org.moeaframework.core.variable.BinaryVariable;
import org.moeaframework.util.validate.Validate;

public class BitFlipMutation implements Mutation {

    /**
     * The probability of flipping a bit.
     */
    private double probability;

    /**
     * Constructs a bit flip mutation operator with the default settings.
     */
    public BitFlipMutation() {
        this(0.01);  // Default flip probability (1%)
    }

    /**
     * Constructs a bit flip mutation operator with a specified probability.
     *
     * @param probability the probability of flipping a bit
     */
    public BitFlipMutation(double probability) {
        super();
        setProbability(probability);
    }

    /**
     * Returns the probability of flipping a bit.
     *
     * @return the probability of flipping a bit
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Sets the probability of flipping a bit.
     *
     * @param probability the probability of flipping a bit
     */
    public void setProbability(double probability) {
        Validate.that("probability", probability).isProbability();
        this.probability = probability;
    }

    @Override
    public Solution mutate(Solution parent) {
        // Copy the parent solution so we can mutate the copy
        Solution result = parent.copy();

        // Loop through all variables in the solution
        for (int i = 0; i < result.getNumberOfVariables(); i++) {
            Variable variable = result.getVariable(i);

            // Apply bit flip mutation to BinaryVariable instances
            if (variable instanceof BinaryVariable binaryVariable) {
                mutate(binaryVariable, probability);
            }
        }

        return result;
    }

    /**
     * Mutates the specified binary variable by flipping bits with a certain probability.
     *
     * @param variable    the binary variable to be mutated
     * @param probability the probability of flipping each bit
     */
    public static void mutate(BinaryVariable variable, double probability) {
        for (int i = 0; i < variable.getNumberOfBits(); i++) {
            // Flip the bit with the specified probability
            if (PRNG.nextDouble() <= probability) {
                variable.set(i, !variable.get(i));
            }
        }
    }

    /**
     * Returns the name of the operator.
     * This is required to implement the Variation interface.
     *
     * @return the name of the mutation operator
     */
    @Override
    public String getName() {
        return "BitFlipMutation";
    }
}
