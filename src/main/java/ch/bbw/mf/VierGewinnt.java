package ch.bbw.mf;

public class VierGewinnt {
    private static boolean gameActive = false;
    public static void runSimulation(int[][] board, int player) {
        int board_width = board[0].length;

        OutputHandler outputHandler = new  OutputHandler();
        outputHandler.draw(board);

        int userinput = outputHandler.promptForUserInput(player, board_width);

        int[][] next_board = computeNextBoard(board, player, userinput);
        int next_player = player == 1 ? 2 : 1;

        if (CONSTANTS.GAME_ACTIVE) {
            runSimulation(next_board, next_player);
        }
    }

    private static int[][] computeNextBoard(int[][] board, int player, int userinput) {
        int board_height = board.length;
        int x = userinput - 1;

        for (int y = 0; y <= board_height; y++) {
            if(!freeUnderneath(x, y, board)){
               if (board[y][x] != 0) {
                   System.out.println("this Row is already filled, please choose another one!");
                   runSimulation(board, player);
                   break;
               }
                board[y][x] = player;
               if (hasPlayerWonGame(board,x, y, player)) {
                    OutputHandler outputHandler = new  OutputHandler();
                    outputHandler.draw(board);
                    System.out.println("Player " + player + " won!");
                    CONSTANTS.GAME_ACTIVE = false;
                    break;
               }
                break;
            }
        }
        return board;
    }

    private static boolean freeUnderneath(int x, int y, int[][] board) {
        if (y == board.length - 1) {
            return false;
        }
        return board[y + 1][x] != 1 && board[y + 1][x] != 2;
    }

    public static boolean hasPlayerWonGame(int[][] board, int x, int y, int player) {
        // Check horizontal
        if (countConsecutive(board, x, y, 0, 1, player) +
                countConsecutive(board, x, y, 0, -1, player) - 1 >= 4) {
            return true;
        }

        // Check vertical
        if (countConsecutive(board, x, y, 1, 0, player) +
                countConsecutive(board, x, y, -1, 0, player) - 1 >= 4) {
            return true;
        }

        // Check diagonal (bottom-left to top-right)
        if (countConsecutive(board, x, y, 1, 1, player) +
                countConsecutive(board, x, y, -1, -1, player) - 1 >= 4) {
            return true;
        }

        // Check diagonal (top-left to bottom-right)
        if (countConsecutive(board, x, y, 1, -1, player) +
                countConsecutive(board, x, y, -1, 1, player) - 1 >= 4) {
            return true;
        }

        return false;
    }

    private static int countConsecutive(int[][] board, int x, int y, int directionX, int directionY, int player) {
        int count = 0;
        int height = board.length;
        int width = board[0].length;

        while (x >= 0 && x < width && y >= 0 && y < height && board[y][x] == player) {
            count++;
            x += directionX;
            y += directionY;
        }

        return count;
    }
}