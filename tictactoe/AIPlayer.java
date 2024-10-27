package tictactoe;
import java.util.*;

public class AIPlayer extends Player {
    
    public AIPlayer(char symbol) {
        super(symbol);
    }

    public Move makeMove(Board board) {
        char mySymbol = getSymbol();
        char opponentSymbol = (mySymbol == 'X') ? 'O' : 'X';
    

        Move winningMove = findWinningMove(board, mySymbol);
        if (winningMove != null) return winningMove;
    
        Move blockMove = findWinningMove(board, opponentSymbol);
        if (blockMove != null) return blockMove;
    
        Move forkMove = findForkMove(board, mySymbol);
        if (forkMove != null) return forkMove;
    
        Move blockForkMove = blockOpponentFork(board, opponentSymbol);
        if (blockForkMove != null) return blockForkMove;
    
        if (board.isCellEmpty(1, 1)) return new Move(1, 1, mySymbol);
    
        Move cornerMove = findEmptyCorner(board);
        if (cornerMove != null) return cornerMove;
    
        Move sideMove = findEmptySide(board);
        return sideMove != null ? sideMove : new Move(0, 0, mySymbol); 
    }
    
    
    private Move findWinningMove(Board board, char symbol) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isCellEmpty(row, col)) {
                    board.setCell(row, col, symbol);
                    if (checkWin(board, symbol)) {
                        board.setCell(row, col, ' ');
                        return new Move(row, col, symbol);
                    }
                    board.setCell(row, col, ' ');
                }
            }
        }
        return null;
    }
    
    private Move findForkMove(Board board, char symbol) {
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.isCellEmpty(row, col)) {
                    board.setCell(row, col, symbol);

                    int winCount = 0;
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            if (board.isCellEmpty(r, c)) {
                                board.setCell(r, c, symbol);
                                if (checkWin(board, symbol)) {
                                    winCount++;
                                }
                                board.setCell(r, c, ' '); 
                            }
                        }
                    }
                    board.setCell(row, col, ' '); 
                    if (winCount >= 2) { 
                        return new Move(row, col, symbol);
                    }
                }
            }
        }
        return null;
    }
    
    private Move blockOpponentFork(Board board, char opponentSymbol) {
        return findForkMove(board, opponentSymbol);
    }
    
    private Move findEmptyCorner(Board board) {
        if (board.isCellEmpty(0, 0)) return new Move(0, 0, getSymbol());
        if (board.isCellEmpty(0, 2)) return new Move(0, 2, getSymbol());
        if (board.isCellEmpty(2, 0)) return new Move(2, 0, getSymbol());
        if (board.isCellEmpty(2, 2)) return new Move(2, 2, getSymbol());
        return null;
    }
    
    private Move findEmptySide(Board board) {
        if (board.isCellEmpty(0, 1)) return new Move(0, 1, getSymbol());
        if (board.isCellEmpty(1, 0)) return new Move(1, 0, getSymbol());
        if (board.isCellEmpty(1, 2)) return new Move(1, 2, getSymbol());
        if (board.isCellEmpty(2, 1)) return new Move(2, 1, getSymbol());
        return null;
    }
    
    private boolean checkWin(Board board, char symbol) {
        return (checkRows(board, symbol) || checkColumns(board, symbol) || checkDiagonals(board, symbol));
    }
    
    private boolean checkRows(Board board, char symbol) {
        for (int row = 0; row < 3; row++) {
            if (board.getCell(row, 0) == symbol && board.getCell(row, 1) == symbol && board.getCell(row, 2) == symbol) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkColumns(Board board, char symbol) {
        for (int col = 0; col < 3; col++) {
            if (board.getCell(0, col) == symbol && board.getCell(1, col) == symbol && board.getCell(2, col) == symbol) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkDiagonals(Board board, char symbol) {
        return (board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol) ||
               (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol);
    }
    
}
