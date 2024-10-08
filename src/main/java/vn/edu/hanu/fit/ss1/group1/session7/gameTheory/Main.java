package vn.edu.hanu.fit.ss1.group1.session7.gameTheory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create players
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        // Create a list of players
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        // Create a game instance
        Game game = new Game(players);

        // Display players
        game.displayPlayers();

        // Additional game logic can be added here
    }
}
