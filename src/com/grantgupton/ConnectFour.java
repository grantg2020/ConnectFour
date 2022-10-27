package com.grantgupton;

import java.util.Random;
import java.util.Scanner;

import com.grantgupton.Model.States;

public class ConnectFour {
    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        Model game = new Model();

        printBoard(game);
        System.out.println();

        Random random = new Random();
        boolean player = random.nextBoolean();

        Scanner userInput = new Scanner(System.in);

        // Main game loop
        while (game.getWinner() == Model.States.EMPTY) {
            System.out.print(player ? "Blue: " : "Red: ");
            System.out.println("enter a number 1-" + game.getWidth());

            // Error handling
            if (!userInput.hasNextInt()) {
                System.out.println("Please enter a number.");
                if (userInput.hasNext())
                    userInput.next();
                continue;
            }
            int move = userInput.nextInt() - 1;
            if (move < 0 || move > game.getWidth() - 1) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            if (game.getCurrentHeight(move) >= game.getHeight()) {
                System.out.println("That column is full. Please try another column.");
                continue;
            }

            if (player) { // States.BLUE_PLAYER
                game.makeMove(States.BLUE_PLAYER, move);
            } else if (!player) { // States.RED_PLAYER
                game.makeMove(States.RED_PLAYER, move);
            }
            player = !player;

            printBoard(game);
        }

        String winner = game.getWinner() == States.BLUE_PLAYER ? "Blue" : "Red";
        if (game.getWinner() == States.EMPTY)
            System.out.println("Nobody won!");
        else
            System.out.println(winner + " won!");
    }

    public static void printBoard(Model game) {
        clearScreen();
        Model.States[][] board = game.getBoard();

        for (int i = 0; i < board[0].length; i++) {
            System.out.print("----");
        }
        System.out.println("-");

        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case EMPTY:
                        System.out.print(" ");
                        break;
                    case BLUE_PLAYER:
                        System.out.print(TEXT_BLUE + "X" + TEXT_RESET);
                        break;
                    case RED_PLAYER:
                        System.out.print(TEXT_RED + "O" + TEXT_RESET);
                        break;
                    default:
                        break;
                }
                System.out.print(" | ");
            }
            System.out.println();
            for (int x = 0; x < board[0].length; x++) {
                System.out.print("----");
            }
            System.out.println("-");
        }

        for (int i = 0; i < board[0].length; i++) {
            System.out.printf(" %2d ", i + 1);
        }
        System.out.println();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
