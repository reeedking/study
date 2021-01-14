package com.reeedking.study.connect4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class ConnectTDDspec {

    private Connect4TDD tested;

    private OutputStream outputStream;

    @BeforeEach
    public void before() {
        outputStream = new ByteArrayOutputStream();
        tested = new Connect4TDD(new PrintStream(outputStream));
    }

    @Test
    public void whenGameStartedTheBoardIsEmpty() {
        assertThat(tested.getNumberOfDiscs(), is(0));
    }

    @Test
    public void whenDiscOutSideBoardThenRuntimeException() {
        int column = -1;
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> tested.putDiscInColumn(column));
        assertEquals(runtimeException.getMessage(), "Invalid column: " + column);
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {
        int column = 1;
        assertThat(tested.putDiscInColumn(column), is(0));
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.putDiscInColumn(column), is(1));
    }

    @Test
    public void whenDiscInsertedThmNumberOfDiscsIncreases() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getNumberOfDiscs(), is(1));
    }

    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        int column = 1;
        int maxDiscsInColumn = 6;
        for (int i = 0; i < maxDiscsInColumn; i++) {
            tested.putDiscInColumn(column);
        }
        RuntimeException exception = assertThrows(RuntimeException.class, () -> tested.putDiscInColumn(column));
        assertEquals(exception.getMessage(), "No more room in column: " + column);
    }

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        assertThat(tested.getCurrentPlayer(), is("R"));
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsRed() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(tested.getCurrentPlayer(), is("G"));
    }

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        tested.getCurrentPlayer();
        assertThat(outputStream.toString(), containsString("Player R turn"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        int column = 1;
        tested.putDiscInColumn(column);
        assertThat(outputStream.toString(), containsString("| |R| | | | | |"));
    }

    @Test
    public void whenTheGameStartsIsNotFinished() {
        assertFalse(tested.isFinished(), "The game must not be finished");
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                tested.putDiscInColumn(j);
            }
        }
        assertTrue(tested.isFinished(), "The game must be finished");
    }

    @Test
    public void when4VerticalDiscsAreConnectedThenPlayerWins() {
        for (int i = 0; i < 3; i++) {
            tested.putDiscInColumn(1); //R
            tested.putDiscInColumn(2); //G
        }
        assertThat(tested.getWinner(), emptyString());
        tested.putDiscInColumn(1); //R
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4HorizontalDiscAreConnectedThenPlayerWins() {
        for (int i = 0; i < 3; i++) {
            tested.putDiscInColumn(i); //R
            tested.putDiscInColumn(i); //G
        }
        assertThat(tested.getWinner(), emptyString());
        tested.putDiscInColumn(3); //R
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4DiagonalDiscsAreConnectedThenThatPlayerWins() {
        int[] gameplay = new int[]{1, 2, 2, 3, 4, 3, 3, 4, 4, 5, 4};
        for (int i : gameplay)
            tested.putDiscInColumn(i);
        assertThat(tested.getWinner(), is("R"));
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        int[] gameplay = new int[]{3, 4, 2, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i : gameplay)
            tested.putDiscInColumn(i);
        assertThat(tested.getWinner(), is("G"));
    }
}


