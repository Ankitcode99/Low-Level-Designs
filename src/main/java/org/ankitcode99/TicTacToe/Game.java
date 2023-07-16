package org.ankitcode99.TicTacToe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    Board board;
    private static Deque<Player> players;

    public Game(){
        initializeGame();
    }

    private void initializeGame() {
        Player player1 = new Player();
        player1.playingPiece = new PieceX();

        Player player2 = new Player();
        player2.playingPiece = new PieceO();

        players = new LinkedList<>();
        players.add(player1);
        players.add(player2);

        board = new Board(3);
    }

    public void startGame(){
        boolean winnerFound = false;
        do{
            if(board.getFreeSpaces()==0){
                System.out.println("It's a DRAW!");
                System.out.println("Good bye!");
                System.exit(0);
            }


            Player currentPlayer = players.pop();
            System.out.print(currentPlayer.getName()+"\'s Turn: ");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] positionValues = input.split(",");

            if(!checkPositionValidity(positionValues)){
                retryChance(currentPlayer);
                continue;
            }

            int row = Integer.parseInt(positionValues[0]), column = Integer.parseInt(positionValues[1]);
            boolean pieceAddResult = board.addPiece(row, column, currentPlayer.getPlayingPiece());
            if(!pieceAddResult){
                retryChance(currentPlayer);
                return;
            }

            if(board.checkWinCondition()){
                System.out.println(currentPlayer.getName()+" WINS!!!!!!!!!!");
                winnerFound = true;
            }
            players.addLast(currentPlayer);
        }while(!winnerFound);
    }

    private void retryChance(Player currentPlayer) {
        System.out.println("INVALID cell positions! Please try again");
        players.addFirst(currentPlayer);
        return;
    }

    private boolean checkPositionValidity(String[] positionValues) {
        boolean validity = true;
        for(String positionValue: positionValues){
            int position = Integer.parseInt(positionValue);
            validity = validity && position<board.size;
        }
        return validity;
    }
}
