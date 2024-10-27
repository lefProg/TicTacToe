package tictactoe;

public class GameLogic {
    public static boolean checkWin(Board board, char symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board.getCell(i, 0) == symbol && board.getCell(i, 1) == symbol && board.getCell(i, 2) == symbol) ||
                (board.getCell(0, i) == symbol && board.getCell(1, i) == symbol && board.getCell(2, i) == symbol)) {
                return true;
            }
        }

        // Check diagonals
        return (board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol) ||
               (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol);
    }

    public static boolean isDraw(Board board) {
        // Check if all cells are filled without a winner
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
