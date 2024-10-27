package tictactoe;

public class Player {
    private char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public Move makeMove(Board board) {
        // Logic for human player can be implemented here
        return new Move(0, 0, symbol); // Placeholder
    }
}
