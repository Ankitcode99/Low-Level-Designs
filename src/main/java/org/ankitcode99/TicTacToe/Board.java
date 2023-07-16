package org.ankitcode99.TicTacToe;

import com.sun.tools.javac.util.Pair;

public class Board {
    public int size;
    PlayingPiece[][] board;

    private static PlayingPiece lastPiece;
    private static Pair<Integer, Integer> lastMoveCell;

    int[] rowSum;
    int[] columnSum;
    int diagonalSum;
    int antiDiagonalSum;

    public Board(int size){
        this.size = size;
        board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int column, PlayingPiece playingPiece){
        if(board[row][column]!=null)
            return false;

        board[row][column] = playingPiece;
        updateSums(row, column, playingPiece);
        return true;
    }

    private void updateSums(int row, int column, PlayingPiece playingPiece) {
        int value = playingPiece instanceof PieceX ? 1: -1;

        rowSum[row] += value;
        columnSum[column] += value;

        if(row==column) {
            diagonalSum += value;
        }
        if(row+column==this.size-1) {
            antiDiagonalSum += value;
        }

        lastPiece = playingPiece;
        lastMoveCell = new Pair<>(row, column);
    }

    public int getFreeSpaces(){
       int count=0;
       for(int row=0;row<this.size;row++){
           for(int col=0;col<this.size;col++){
               count += (board[row][col]==null)?1:0;
           }
       }
       return count;
    }

    public boolean checkWinCondition(){
        int target = lastPiece instanceof PieceX ? 3 : -3;
        return rowSum[lastMoveCell.fst]==target || columnSum[lastMoveCell.snd]==target || diagonalSum == target || antiDiagonalSum ==target;
    }

}
