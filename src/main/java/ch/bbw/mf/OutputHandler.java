package ch.bbw.mf;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OutputHandler {

    public void draw(int[][] board) {
        int width = board[0].length;
        int height = board.length;

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

        for (int y = 0; y < height; y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < width; x++) {
                if (board[y][x] == CONSTANTS.EMPTY_FILED_CODE){
                    line.append(CONSTANTS.CHAR_EMPTY_FIELD);
                } else if (board[y][x] == CONSTANTS.PLAYER_1_FIELD_CODE) {
                    line.append(CONSTANTS.CHAR_PLAYER_1);
                }else if (board[y][x] == CONSTANTS.PLAYER_2_FIELD_CODE) {
                    line.append(CONSTANTS.CHAR_PLAYER_2);
                }else{
                    throw new IllegalArgumentException("Illegal Code in grid");
                }
            }
            System.out.println(line);
        }
    }

    public int promptForUserInput(int currentPlayer, int size_board) {
        Scanner scanner = new Scanner(System.in);

        String plyersymbol = currentPlayer == 1 ? CONSTANTS.CHAR_PLAYER_1 : CONSTANTS.CHAR_PLAYER_2;

        System.out.print("Player " + currentPlayer + ", you are: " + plyersymbol + " please select Row for next move: ");

        int row = Integer.MAX_VALUE;
        try {
             row = scanner.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Please enter a valid row number");
            row = promptForUserInput(currentPlayer, size_board);
        }

        if (row <= 0 || row > size_board){
            //throw new IllegalArgumentException("Illegal Row number: " + row);
            row = promptForUserInput(currentPlayer, size_board);
        }

        return row;
    }
}
