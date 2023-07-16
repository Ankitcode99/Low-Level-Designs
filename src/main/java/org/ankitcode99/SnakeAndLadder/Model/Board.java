package org.ankitcode99.SnakeAndLadder.Model;

import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    int size;
    Cell[][] gameBoard;

    Board(int size, int snakeCount, int ladderCount){
        this.size = size;
        gameBoard = new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                gameBoard[i][j] = new Cell();
            }
        }
        addSnakesAndLadder(snakeCount, ladderCount);
    }

    private void addSnakesAndLadder(int snakeCount, int ladderCount) {
        HashSet<Integer> usedCells = new HashSet<>();
        while(snakeCount>0){
            int start = ThreadLocalRandom.current().nextInt(1, size*size-1);
            int end = ThreadLocalRandom.current().nextInt(1, size*size-1);

            if(start <= end || usedCells.contains(start) || usedCells.contains(end)){
                continue;
            }

            Link snake = new Link(start, end);
            Cell cell = getCell(start);
            cell.link = snake;
            usedCells.add(start);
            usedCells.add(end);

            System.out.println("Added snake from "+start+" to "+end);

            snakeCount--;
        }

        while(ladderCount>0){
            int start = ThreadLocalRandom.current().nextInt(1, size*size-1);
            int end = ThreadLocalRandom.current().nextInt(1, size*size-1);

            if(start >= end || usedCells.contains(start) || usedCells.contains(end)){
                continue;
            }

            Link ladder = new Link(start, end);
            Cell cell = getCell(start);
            cell.link = ladder;
            usedCells.add(start);
            usedCells.add(end);

            System.out.println("Added ladder from "+start+" to "+end);

            ladderCount--;
        }

    }

    Cell getCell(int number) {
        int row = number / size;
        int column = number % size;

        return gameBoard[row][column];
    }
}
