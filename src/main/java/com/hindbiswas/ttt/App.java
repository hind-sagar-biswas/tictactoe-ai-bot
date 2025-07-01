/**
 * Project: Tic Tac Toe AI Console Game
 * 
 * Description:
 * This is the main entry point for the console-based Tic Tac Toe game where a human player
 * plays against an AI-controlled bot. The game runs in a loop, keeping score for the 
 * player and the computer until the player decides to quit.
 * 
 * Problem Statement:
 * Implement a playable Tic Tac Toe game in the console where:
 *  - The player selects a side (X or O)
 *  - The game handles turns, checks for wins or ties
 *  - The computer makes optimal moves using an AI strategy
 *  - Scores are tracked across rounds
 * 
 * Algorithm Used:
 * The computer opponent (Bot) uses the **Minimax algorithm** to calculate the best possible move
 * at each turn. This ensures the AI always plays optimally and either wins or forces a draw.
 * 
 * Game Flow:
 * 1. Displays a stylized banner
 * 2. Prompts player to choose a side (X or O)
 * 3. Initiates a game using the `Game` class
 * 4. Repeats the game loop until the player chooses to exit
 * 5. Displays final scores
 * 
 * Features:
 * - Smart AI using Minimax
 * - Clear turn-based interaction
 * - CLI-based board rendering with numbered positions
 * - Input validation for side choice and moves
 * 
 * Author: Hind Biswas Krishna
 * Version: 1.0
 * Date: July 1, 2025
 * License: MIT
 * 
 * Package: com.hindbiswas.ttt
 */

package com.hindbiswas.ttt;

/**
 * Main entry point for the console-based Tic Tac Toe game
 *
 */
public class App {
    public static void main(String[] args) {
        int computer_score = 0;
        int player_score = 0;

        banner();
        while (true) {
            Game game = new Game();
            int[] scores = game.start();
            computer_score += scores[1];
            player_score += scores[0];

            System.out.println("Computer: " + computer_score + " Player: " + player_score);

            char choice = game.in.character("Do you want to play again? (Y/n)",
                    c -> Character.toUpperCase(c) == 'Y' || Character.toUpperCase(c) == 'N', 'Y');

            if (Character.toUpperCase(choice) == 'N')
                break;
        }

        System.out.println("\n=====================================\n");
        System.out.println("Final Score - Computer: " + computer_score + " Player: " + player_score);
        System.out.println("Thanks for playing!");
    }

    public static void banner() {
        String[] banner = {
                "___________.__               ___________                     ___________            ",
                "\\__    ___/|__| ____         \\__    ___/____    ____         \\__    ___/___   ____  ",
                "  |    |   |  |/ ___\\   ______ |    |  \\__  \\ _/ ___\\   ______ |    | /  _ \\_/ __ \\ ",
                "  |    |   |  \\  \\___  /_____/ |    |   / __ \\\\  \\___  /_____/ |    |(  <_> )  ___/ ",
                "  |____|   |__|\\___  >         |____|  (____  /\\___  >         |____| \\____/ \\___  >",
                "                   \\/                       \\/     \\/                            \\/ "
        };

        for (String line : banner) {
            System.out.println(line);
        }
        System.out.println("=====================================");
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("=====================================");
    }
}
