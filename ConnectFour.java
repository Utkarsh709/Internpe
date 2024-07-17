import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLS = 7;
    private static char[][] board = new char[ROWS][COLS];
    private static final char EMPTY = '.';
    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    public static void main(String[] args) {
        initializeBoard();
        printBoard();

        boolean playerOneTurn = true;
        boolean gameWon = false;

        while (!gameWon && !isBoardFull()) {
            if (playerOneTurn) {
                System.out.println("Player One's Turn (X):");
                gameWon = makeMove(PLAYER_ONE);
            } else {
                System.out.println("Player Two's Turn (O):");
                gameWon = makeMove(PLAYER_TWO);
            }
            printBoard();
            playerOneTurn = !playerOneTurn;
        }

        if (gameWon) {
            System.out.println("Congratulations! " + (playerOneTurn ? "Player Two" : "Player One") + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static void initializeBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private static void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static boolean makeMove(char player) {
        Scanner scanner = new Scanner(System.in);
        int col;
        while (true) {
            System.out.print("Enter column (1-7): ");
            col = scanner.nextInt() - 1;
            if (col >= 0 && col < COLS && board[0][col] == EMPTY) {
                break;
            } else {
                System.out.println("Invalid column or column is full. Try again.");
            }
        }

        int row = ROWS - 1;
        while (board[row][col] != EMPTY) {
            row--;
        }
        board[row][col] = player;

        return checkWin(row, col, player);
    }

    private static boolean checkWin(int row, int col, char player) {
        return checkDirection(row, col, player, 1, 0)  // Horizontal
            || checkDirection(row, col, player, 0, 1)  // Vertical
            || checkDirection(row, col, player, 1, 1)  // Diagonal /
            || checkDirection(row, col, player, 1, -1); // Diagonal \
    }

    private static boolean checkDirection(int row, int col, char player, int rowDelta, int colDelta) {
        int count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i * rowDelta;
            int c = col + i * colDelta;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLS && board[r][c] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col] == EMPTY) {
                return false;
            }
        }
        return true;
    }
}

