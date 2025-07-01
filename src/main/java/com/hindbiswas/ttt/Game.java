package com.hindbiswas.ttt;

/**
 * Game
 */
public class Game {
    Input in = new Input();
    private final Player[] board = {
            Player.N, Player.N, Player.N,
            Player.N, Player.N, Player.N,
            Player.N, Player.N, Player.N
    };
    private final Bot bot;
    private final Player player;
    private Player turn = Player.X;

    public Game() {
        System.out.println("=====================================\n");
        System.out.println("Start a new game...");
        char choice = in.character("Choose your side (X/O)",
                c -> Character.toUpperCase(c) == 'X' || Character.toUpperCase(c) == 'O', 'X');
        player = Character.toUpperCase(choice) == 'X' ? Player.X : Player.O;
        bot = new Bot(player == Player.X ? Player.O : Player.X);

        System.out.println("You are playing as " + player + ". The computer is playing as " + bot.getPlayer() + ".");
        System.out.println("X makes the first move.");
    }

    public int[] start() {
        System.out.println("Let's play!");
        System.out.println("=====================================\n");
        while (true) {
            printBoard();

            if (turn == bot.getPlayer()) {
                try {
                    System.out.println("Computer's move: The computer is thinking...");
                    Thread.sleep(1000);
                    int move = bot.makeMove(board);
                    board[move] = bot.getPlayer();
                    System.out.println("Computer's move: " + (move + 1));
                } catch (InterruptedException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else {
                int move = playerMove();
                board[move] = player;
            }

            Player result = Bot.checkWin(board);
            if (result != null) {
                printBoard();

                if (result == Player.N)
                    System.out.println("It's a Tie!");
                else if (result == player)
                    System.out.println("You win!");
                else
                    System.out.println("You lose!");

                return new int[] { player == result ? 1 : 0, bot.getPlayer() == result ? 1 : 0 };
            }
            turn = player == turn ? bot.getPlayer() : player;
        }
    }

    public void printBoard() {
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            // Print player symbol
            String symbol = switch (board[i]) {
                case X -> "X";
                case O -> "O";
                case N -> String.valueOf(i + 1);
            };
            System.out.print(" " + symbol + " ");

            // Print separators
            if (i % 3 != 2) {
                System.out.print("|");
            } else if (i != board.length - 1) {
                System.out.println("\n-----------");
            }
        }
        System.out.println("\n");
    }

    private int playerMove() {
        return in.integer("Enter your move (1-9)", i -> i > 0 && i <= 9 && board[i - 1] == Player.N) - 1;
    }
}
