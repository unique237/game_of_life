public class GameOfLife {

    private static final int SIZE = 5; // the grid size
    private int[][] grid = new int[SIZE][SIZE];

    
    public void initialize() {
        grid[1][2] = 1;
        grid[2][2] = 1;
        grid[3][2] = 1;
    }

    public void printGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] == 1 ? "O " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private int countNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i, c = col + j;
                if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                    count += grid[r][c];
                }
            }
        }
        return count;
    }

    public void nextGeneration() {
        int[][] newGrid = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int neighbors = countNeighbors(i, j);
                if (grid[i][j] == 1 && (neighbors == 2 || neighbors == 3)) {
                    newGrid[i][j] = 1;
                } else if (grid[i][j] == 0 && neighbors == 3) {
                    newGrid[i][j] = 1;
                } else {
                    newGrid[i][j] = 0;
                }
            }
        }

        grid = newGrid;
    }

    
    public static void main(String[] args) throws InterruptedException {
        GameOfLife game = new GameOfLife();
        game.initialize();

        for (int i = 0; i < 5; i++) { 
            System.out.println("Generation " + i + ":");
            game.printGrid();
            game.nextGeneration();
            Thread.sleep(1000); 
        }
    }
}
