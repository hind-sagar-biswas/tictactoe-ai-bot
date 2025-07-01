package com.hindbiswas.ttt;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerFindBestMove() {
        Player[] board = {
                Player.O, Player.N, Player.X,
                Player.X, Player.N, Player.N,
                Player.X, Player.O, Player.O
        };
        Bot bot = new Bot(Player.X);
        int bestMove = bot.makeMove(board);
        int expectedMove = 4;

        System.out.println("Expected: " + expectedMove);
        System.out.println("Actual: " + 4);

        assertTrue(bestMove == expectedMove);
    }
}
