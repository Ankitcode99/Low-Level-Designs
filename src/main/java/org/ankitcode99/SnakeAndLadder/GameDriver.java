package org.ankitcode99.SnakeAndLadder;

import org.ankitcode99.SnakeAndLadder.Model.Game;
import org.ankitcode99.SnakeAndLadder.Model.Player;

public class GameDriver {
    public static void main(String[] args) {
        Player[] players = new Player[2];
        players[0] = new Player("Ankit");
        players[1] = new Player("Harsh");
        Game game = new Game(10, 5, 5, 1, players);
        game.startGame();
    }
}
