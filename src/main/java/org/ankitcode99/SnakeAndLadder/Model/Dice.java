package org.ankitcode99.SnakeAndLadder.Model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private int count;
    private int mini=1, maxi=6;
    Dice(int count){
        this.count = count;
    }

    public int rollDice(){
        int total=0, diceRolled=0;
        while(diceRolled<count){
            total += ThreadLocalRandom.current().nextInt(mini, maxi+1);
            diceRolled++;
        }
        return total;
    }
}
