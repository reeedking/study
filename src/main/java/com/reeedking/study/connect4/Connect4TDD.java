package com.reeedking.study.connect4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Connect4TDD {

    private static final int ROWS = 6;

    private static final int COLUMNS = 7;

    private static final String EMPTY = " ";

    private static final String RED = "R";

    private static final String GREEN = "G";

    private static final String DELIMITER = "|";

    private static final int DISCS_TO_WIN = 4;

    private String winner = "";

    private String currentPlayer = RED;

    private String[][] board = new String[ROWS][COLUMNS];

    private PrintStream outputChannel;

    public Connect4TDD(PrintStream printStream) {
        outputChannel = printStream;
        for (String[] row : board)
            Arrays.fill(row, EMPTY);
    }

    public int getNumberOfDiscs() {
        return IntStream.range(0, COLUMNS).map(this::getNumberOfDiscsInColumn).sum();
    }

    private int getNumberOfDiscsInColumn(int column) {
        //  IntStream.range(0, 7) 返回 0 1 2 3 4 5 6
        return (int) IntStream.range(0, ROWS).filter(row -> !EMPTY.equals(board[row][column])).count();
    }

    public int putDiscInColumn(int column) {
        checkColumn(column);
        int row = getNumberOfDiscsInColumn(column);
        checkPositionToInsert(row, column);
        board[row][column] = currentPlayer;
        printBoard();
        checkWinner(row, column);
        switchPlayer();
        return row;
    }

    private void checkColumn(int column) {
        if (column < 0 || column >= COLUMNS)
            throw new RuntimeException("Invalid column: " + column);
    }

    private void checkPositionToInsert(int row, int column) {
        if (row == ROWS)
            throw new RuntimeException("No more room in column: " + column);
    }

    public String getCurrentPlayer() {
        outputChannel.printf("Player %s turn%n", currentPlayer);
        return currentPlayer;
    }

    private void switchPlayer() {
        if (currentPlayer.equals(RED))
            currentPlayer = GREEN;
        else
            currentPlayer = RED;
    }

    private void printBoard() {
        for (int row = ROWS - 1; row >= 0; row--) {
            StringJoiner stringJoiner = new StringJoiner(DELIMITER, DELIMITER, DELIMITER);
            Stream.of(board[row]).forEach(stringJoiner::add);
            outputChannel.println(stringJoiner.toString());
        }
    }

    public boolean isFinished() {
        return getNumberOfDiscs() == ROWS * COLUMNS;
    }

    private void checkWinner(int row, int column) {
        Pattern winPattern = Pattern.compile(".*" + currentPlayer + "{" + DISCS_TO_WIN + "}.*");
        if (winner.isEmpty()) {
            int startOffset = Math.min(column, row);
            int myColumn = column - startOffset,
                    myRow = row -startOffset;
            StringJoiner stringJoiner = new StringJoiner(" ");
            do {
                stringJoiner.add(board[myRow++][myColumn++]);
            }while (myColumn < COLUMNS && myRow < ROWS);
            String color = board[row][column];
            String vertical = IntStream.range(0, ROWS).mapToObj(r -> board[r][column]).reduce(String::concat).get();
            String horizontal = Stream.of(board[row]).reduce(String::concat).get();
            if (winPattern.matcher(vertical).matches() || winPattern.matcher(horizontal).matches())
                winner = color;
        }
        if (winner.isEmpty()) {
            int startOffset = Math.min(column, row);
            int myColumn = column - startOffset,
                    myRow = row - startOffset;
            StringJoiner stringJoiner = new StringJoiner("");
            do {
                stringJoiner.add(board[myRow++][myColumn++]);
            } while (myColumn < COLUMNS && myRow < ROWS);
            if (winPattern.matcher(stringJoiner.toString()).matches())
                winner = currentPlayer;
        }
        if (winner.isEmpty()) {
            int startOffset = Math.min(column, ROWS - 1 - row);
            int myColumn = column - startOffset,
                    myRow = row + startOffset;
            StringJoiner stringJoiner = new StringJoiner("");
            do {
                stringJoiner.add(board[myRow--][myColumn++]);
            } while (myColumn < COLUMNS && myRow >= 0);
            if (winPattern.matcher(stringJoiner.toString()).matches())
                winner = currentPlayer;
        }


    }

    public String getWinner() {
        return winner;
    }
}
