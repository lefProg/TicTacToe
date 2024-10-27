package tictactoe;

public class GameManager {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public GameManager(boolean isAI) {
        board = new Board();
        player1 = new Player('X'); // Human player
        player2 = isAI ? new AIPlayer('O') : new Player('O'); // AI or second human
        currentPlayer = player1; // Start with player 1
    }

    public void startGame() {
        while (!isGameOver()) {
            System.out.println("Current Board:");
            board.display();
            System.out.println("Player " + currentPlayer.getSymbol() + "'s turn.");
            Move move = currentPlayer.makeMove(board);
            board.setCell(move.getRow(), move.getCol(), currentPlayer.getSymbol());

            if (GameLogic.checkWin(board, currentPlayer.getSymbol())) {
                System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                return;
            }

            if (GameLogic.isDraw(board)) {
                System.out.println("It's a draw!");
                return;
            }

            switchTurn();
        }
    }

    private boolean isGameOver() {
        return GameLogic.checkWin(board, player1.getSymbol()) ||
               GameLogic.checkWin(board, player2.getSymbol()) ||
               GameLogic.isDraw(board);
    }

    private void switchTurn() {
        if(currentPlayer == player1){
            currentPlayer = player2;
        }
        else{
            currentPlayer = player1;
        }
    }
}
