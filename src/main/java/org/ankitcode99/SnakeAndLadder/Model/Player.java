package org.ankitcode99.SnakeAndLadder.Model;

import lombok.Getter;
import lombok.Setter;

public class Player {
    @Setter @Getter private int position;
    @Setter @Getter
    private String name;

    public Player(String name){
        this.name = name;
        position=0;
    }
}
