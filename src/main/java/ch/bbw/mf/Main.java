package ch.bbw.mf;


public class Main {
    public static void main(String[] args) {
        int[][] board = new int[CONSTANTS.BOARD_HEIGHT][CONSTANTS.BOARD_WIDTH];

        for (int y = 0; y < CONSTANTS.BOARD_HEIGHT; y++) {
            for (int x = 0; x < CONSTANTS.BOARD_WIDTH; x++) {
                board[y][x] = 0;
            }
        }

        CONSTANTS.GAME_ACTIVE = true;
        VierGewinnt.runSimulation(board, 1);
    }
}