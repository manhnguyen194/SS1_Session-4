package com.tictactoe.moea;

import org.moeaframework.core.Variable;

public class CustomEncodingUtils {

    public static Game getGame(Variable variable) {
        if (variable instanceof Game) {
            return (Game) variable;
        }
        throw new IllegalArgumentException("Variable is not a Game object");
    }

}
