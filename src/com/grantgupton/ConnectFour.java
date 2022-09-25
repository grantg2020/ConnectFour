package com.grantgupton;

public class ConnectFour {
    public static void main(String[] args) {
        Model game = new Model();

        printBoard(game);
        System.out.println();
        game.makeMove(Model.States.BLUE_PLAYER, 1);
        game.makeMove(Model.States.BLUE_PLAYER, 1);
        game.makeMove(Model.States.BLUE_PLAYER, 1);
        game.makeMove(Model.States.BLUE_PLAYER, 1);
        System.out.println(game.getWinner());
        printBoard(game);
    }

    public static void printBoard(Model game) {
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
                        System.out.print("B");
                        break;
                    case RED_PLAYER:
                        System.out.print("R");
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
    }
}
