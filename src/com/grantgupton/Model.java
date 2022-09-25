package com.grantgupton;

/**
 * Model of connect four game
 */
public class Model {

    /**
     * Enum to represent the state of a square on the board
     */
    public static enum States {
        EMPTY,
        BLUE_PLAYER,
        RED_PLAYER
    }

    /** Number in a row to win the game */
    private static final int NUMBER_TO_WIN = 4;

    /** Connect four board */
    private States[][] board;

    /** Holds the last move made column */
    private int lastMoveRow = 0;

    /** Holds the last move made row */
    private int lastMoveColumn = 0;

    /**
     * Default constructor
     */
    public Model() {
        this(3, 12);
    }

    /**
     * Contructor with width and height
     * 
     * @param width  width of board
     * @param height height of board
     */
    public Model(int width, int height) {
        // TODO: load file with settings data
        initBoard(width, height);
    }

    /**
     * Initializes board to width and height specified
     * 
     * @param width  width of board
     * @param height height of board
     */
    public void initBoard(int width, int height) {
        board = new States[height][width];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = States.EMPTY;
            }
        }
    }

    /**
     * Makes a move on the board and returns true if successful
     * 
     * @param player player to make move with
     * @param column column position of move
     * @return true if move was successful
     */
    public boolean makeMove(States player, int column) {
        // Check if position is valid
        if (column < 0 || column >= board.length)
            return false;
        // Check if board position is empty
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][column] == States.EMPTY) {
                board[i][column] = player;
                lastMoveRow = i;
                lastMoveColumn = column;
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the board
     * 
     * @return the board
     */
    public States[][] getBoard() {
        return board;
    }

    /**
     * Returns the winner of the game, or empty if not
     * 
     * @return the winner of the game, or empty if not
     */
    public States getWinner() {
        // Make sure last move was made by player
        if (board[lastMoveRow][lastMoveColumn] == States.EMPTY)
            return States.EMPTY;

        // Check horizontal
        if ((dirStatesCount(lastMoveRow, lastMoveColumn, 1, 0) + dirStatesCount(lastMoveRow, lastMoveColumn, -1, 0)
                - 1) >= NUMBER_TO_WIN) {
            return board[lastMoveRow][lastMoveColumn];
        }

        // Check vertical
        if ((dirStatesCount(lastMoveRow, lastMoveColumn, 0, 1) + dirStatesCount(lastMoveRow, lastMoveColumn, 0, -1)
                - 1) >= NUMBER_TO_WIN) {
            return board[lastMoveRow][lastMoveColumn];
        }

        // Check diagonals
        if ((dirStatesCount(lastMoveRow, lastMoveColumn, 1, 1) + dirStatesCount(lastMoveRow, lastMoveColumn, -1, -1)
                - 1) >= NUMBER_TO_WIN) {
            return board[lastMoveRow][lastMoveColumn];
        }
        if ((dirStatesCount(lastMoveRow, lastMoveColumn, 1, -1) + dirStatesCount(lastMoveRow, lastMoveColumn, -1, 1)
                - 1) >= NUMBER_TO_WIN) {
            return board[lastMoveRow][lastMoveColumn];
        }

        return States.EMPTY;
    }

    /**
     * Checks amount of same pieces in a direction using dx and dy
     * 
     * @param x  x position of piece to check
     * @param y  y position of piece to check
     * @param dx change in x
     * @param dy change in y
     * @return number of same pieces as first location
     */
    private int dirStatesCount(int x, int y, int dx, int dy) {
        if (dx == dy && dx == 0)
            return 1;

        boolean isSame = true;
        States player = board[x][y];
        int count = 1;

        while (isSame) {
            x += dx;
            y += dy;

            // Check boundaries
            if (x < 0 || x > board.length - 1 || y < 0 || y > board[0].length - 1) {
                return count;
            }

            // Check if next piece is not the same
            if (board[x][y] != player) {
                return count;
            }

            count++;
        }

        return count;
    }

    public void resetGame() {
        lastMoveRow = 0;
        lastMoveColumn = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = States.EMPTY;
            }
        }
    }
}