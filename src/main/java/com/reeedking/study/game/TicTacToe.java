package com.reeedking.study.game;

public class TicTacToe {

    private Character[][] board = {{'\0', '\0', '\0'}, {'\0', '\0', '\0'}, {'\0', '\0', '\0'}};

    private char lastPlayer = '\0';

    private static final int SIZE = 3;

    public String play(int x, int y) {
        checkAxis(x);
        checkAxis(y);
        lastPlayer = nextPlayer();
        setBox(x, y);
        if (isWin())
            return lastPlayer + " is the winner";
        if (isDraw())
            return "The result is draw";
        return "No winner";
    }

    private boolean isWin() {
        int playerTotal = lastPlayer * 3;
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] + board[1][i] + board[2][i] == playerTotal)
                return true;
            if (board[i][0] + board[i][1] + board[i][2] == playerTotal)
                return true;
        }
        if (board[0][0] + board[1][1] + board[2][2] == playerTotal)
            return true;
        if (board[0][2] + board[1][1] + board[2][0] == playerTotal)
            return true;
        return false;
    }

    private void checkAxis(int axis) {
        if (axis < 1 || axis > 3)
            throw new RuntimeException("out of outside board");
    }

    private void setBox(int x, int y) {
        if (board[x - 1][y - 1] != '\0')
            throw new RuntimeException("Box is occupied!");
        else
            board[x - 1][y - 1] = lastPlayer;
    }

    public char nextPlayer() {
        if (lastPlayer == 'X')
            return 'O';
        return 'X';
    }

    private boolean isDraw() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (board[x][y] == '\0')
                    return false;
            }
        }
        return true;
    }
}
