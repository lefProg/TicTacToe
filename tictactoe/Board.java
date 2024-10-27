package tictactoe;

public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void display() {
        System.out.println(grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2]);
        System.out.println("---------");
        System.out.println(grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2]);
        System.out.println("---------");
        System.out.println(grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2]);
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, char symbol) {
        grid[row][col] = symbol;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    public void resetCell(int row, int col) {
        // Assuming ' ' (space) represents an empty cell
        setCell(row, col, ' '); // or whatever your representation for an empty cell is
    }
    
}
