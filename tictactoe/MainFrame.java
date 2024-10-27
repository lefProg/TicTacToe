package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public MainFrame(boolean isAI) {
        startGame(isAI); // Initialize the game with the selected mode

        // Set up the frame
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Create buttons for the grid
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setActionCommand(i + "," + j); // Set action command for each button
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    private void startGame(boolean isAI) {
        // Initialize the game components
        board = new Board();
        player1 = new Player('X'); // Human player
        player2 = isAI ? new AIPlayer('O') : new Player('O'); // AI or second human
        currentPlayer = player1; // Start with player 1
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String[] parts = command.split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (board.isCellEmpty(row, col)) {
            board.setCell(row, col, currentPlayer.getSymbol());
            buttons[row][col].setText(String.valueOf(currentPlayer.getSymbol()));
            buttons[row][col].setEnabled(false);

            if (GameLogic.checkWin(board, currentPlayer.getSymbol())) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer.getSymbol() + " wins!");
                resetGame();
                return;
            }

            if (GameLogic.isDraw(board)) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
                return;
            }

            switchTurn();

            // AI's turn
            if (currentPlayer instanceof AIPlayer) {
                Move move = currentPlayer.makeMove(board);
                board.setCell(move.getRow(), move.getCol(), currentPlayer.getSymbol());
                buttons[move.getRow()][move.getCol()].setText(String.valueOf(currentPlayer.getSymbol()));
                buttons[move.getRow()][move.getCol()].setEnabled(false);

                if (GameLogic.checkWin(board, currentPlayer.getSymbol())) {
                    JOptionPane.showMessageDialog(this, "Player " + currentPlayer.getSymbol() + " wins!");
                    resetGame();
                    return;
                }

                if (GameLogic.isDraw(board)) {
                    JOptionPane.showMessageDialog(this, "It's a draw!");
                    resetGame();
                    return;
                }

                switchTurn();
            }
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void resetGame() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Restart Game", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            String[] options = {"Player vs Player", "Player vs AI"};
            int modeChoice = JOptionPane.showOptionDialog(this, "Choose game mode:",
                    "Tic Tac Toe",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            boolean isAI = (modeChoice == 1); // Player vs AI if choice is 1
            startGame(isAI); // Restart the game with the selected mode

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText(""); // Clear button text
                    buttons[i][j].setEnabled(true); // Re-enable buttons
                }
            }
        } else {
            System.exit(0); // Exit the application if the player chooses not to play again
        }
    }
}
