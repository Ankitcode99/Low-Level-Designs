package org.ankitcode99.SnakeAndLadder.Model;

import java.util.Deque;
import java.util.LinkedList;

public class Game {

    Board board;
    Dice dice;
    Deque<Player> players = new LinkedList<>();

    public Game(int size, int snakeCount, int ladderCount, int diceCount, Player... playersList){
        board = new Board(size, snakeCount, ladderCount);
        dice = new Dice(diceCount);
        for(Player player: playersList){
            players.add(player);
        }
    }

    public void startGame(){
        boolean winnerFound = false;
        do{
            Player currentPlayer = players.pop();
            int diceValue = dice.rollDice();

            int newPosition = makeMove(currentPlayer, diceValue);
            currentPlayer.setPosition(newPosition);

            System.out.println(currentPlayer.getName()+"   moves to   "+newPosition);

            if(newPosition == board.size* board.size-1){
                winnerFound = true;
                System.out.println(currentPlayer.getName()+"  is the winner!!!!!!!!");
            }
            if(diceValue==6){
                players.addFirst(currentPlayer);
            }else{
                players.addLast(currentPlayer);
            }
        }while(!winnerFound);
    }

    private int makeMove(Player currentPlayer, int diceValue) {
        if(currentPlayer.getPosition() + diceValue > board.size* board.size - 1){
            return currentPlayer.getPosition();
        }

        Cell cell = board.getCell(currentPlayer.getPosition() + diceValue);
        if(cell.link!=null && cell.link.start==currentPlayer.getPosition()+diceValue){
            if(cell.link.start < cell.link.end) {
                System.out.println(currentPlayer.getName()+"  got a ladder! going from  "+cell.link.start+"   to   "+cell.link.end);
            }else{
                System.out.println(currentPlayer.getName()+"  met with a snake! going from  "+cell.link.start+"   to   "+cell.link.end);
            }

            return cell.link.end;
        }

        return currentPlayer.getPosition() + diceValue;
    }
}
