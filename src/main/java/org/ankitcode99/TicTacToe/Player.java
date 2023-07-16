package org.ankitcode99.TicTacToe;

import lombok.Getter;
import lombok.Setter;

public class Player {

    private static int id;
    @Getter private String name;
    @Getter @Setter PlayingPiece playingPiece;

    Player() {
        this.name = "Player_"+id;
        id++;
    }
}
