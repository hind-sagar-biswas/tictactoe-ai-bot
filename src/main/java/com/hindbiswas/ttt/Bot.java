package com.hindbiswas.ttt;

public class Bot {
    private final Player player;

    public Bot(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * Checks for a win or tie on a 3x3 Tic Tac Toe board.
     * 
     * @param board A 1D array of 9 Player values.
     * @return Player.X or Player.O if there's a winner,
     *         Player.N if it's a tie,
     *         null if the game is still ongoing.
     */
    public static Player checkWin(Player[] board) {
        int[][] winConditions = {
                { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, // rows
                { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, // columns
                { 0, 4, 8 }, { 2, 4, 6 } // diagonals
        };

        // Check for win
        for (int[] line : winConditions) {
            Player a = board[line[0]];
            Player b = board[line[1]];
            Player c = board[line[2]];

            if (a != Player.N && a == b && b == c) {
                return a; // X or O wins
            }
        }

        // Check if board is full (tie)
        boolean isFull = true;
        for (Player cell : board) {
            if (cell == Player.N) {
                isFull = false;
                break;
            }
        }

        if (isFull)
            return Player.N; // Tie

        return null; // Game still ongoing
    }

    public int makeMove(Player[] board) throws IllegalArgumentException {
        // Validate the board
        if (board == null || board.length != 9)
            throw new IllegalArgumentException("Invalid Board State: Board must be an array of 9 cells.");

        if (checkWin(board) != null)
            throw new IllegalArgumentException("Invalid Board State: Game is already over.");

        // MinMax AI
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] != Player.N) // Check if the cell is empty
                continue;

            board[i] = player; // Make the move
            int score = this.minimax(board, false); // Evaluate the move
            board[i] = Player.N; // Undo the move

            if (score > bestScore) {
                bestScore = score;
                bestMove = i;
            }
        }

        return bestMove;
    }

    private int minimax(Player[] board, boolean isMaximizing) {
        Player result = checkWin(board);

        // Check if the game is over
        if (result != null) {
            if (result == player)
                return 1;
            if (result == Player.N)
                return 0;
            return -1;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (board[i] != Player.N) // Check if the cell is empty
                    continue;

                board[i] = player; // Make the move
                int score = this.minimax(board, false); // Evaluate the move
                board[i] = Player.N; // Undo the move

                bestScore = Math.max(bestScore, score);
            }
            return bestScore;
        }

        int bestScore = Integer.MAX_VALUE;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != Player.N) // Check if the cell is empty
                continue;

            board[i] = player == Player.X ? Player.O : Player.X; // Make the move
            int score = this.minimax(board, true); // Evaluate the move
            board[i] = Player.N; // Undo the move

            bestScore = Math.min(bestScore, score);
        }

        return bestScore;
    }
}
