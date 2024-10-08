package vn.edu.hanu.fit.ss1.group1.session7.gameTheory;

import java.util.List;

public class Game {
    private List<Player> players;

    public Game(List<Player> players) {
        this.players = players;
    }

    // Example method to display players
    public void displayPlayers() {
        System.out.println("Players in the game:");
        for (Player player : players) {
            System.out.println("- " + player.getName());
        }
    }

    // Additional methods for game logic will go here
}
