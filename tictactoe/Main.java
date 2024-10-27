package tictactoe;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String[] options = {"Player vs Player", "Player vs AI"};
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode:",
                "Tic Tac Toe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        boolean isAI = (choice == 1); // Player vs AI if choice is 1
        MainFrame mainFrame = new MainFrame(isAI); // Pass the AI choice
        mainFrame.setVisible(true);
    }
}
