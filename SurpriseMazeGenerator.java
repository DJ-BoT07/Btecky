import java.util.Random;
import java.util.Stack;

public class SurpriseMazeGenerator {

    public static void main(String[] args) {
        int rows = 10;
        int cols = 10;

        int[][] maze = generateMaze(rows, cols);

        displayMaze(maze);
    }

    private static int[][] generateMaze(int rows, int cols) {
        int[][] maze = new int[rows][cols];
        Random random = new Random();
        Stack<int[]> stack = new Stack<>();
        int startRow = 1;
        int startCol = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }

        maze[startRow][startCol] = 0;
        stack.push(new int[]{startRow, startCol});

        while (!stack.isEmpty()) {
            int[] current = stack.peek();
            int currentRow = current[0];
            int currentCol = current[1];

            int[][] neighbors = {
                {currentRow - 2, currentCol},
                {currentRow + 2, currentCol},
                {currentRow, currentCol - 2},
                {currentRow, currentCol + 2}
            };
            random.shuffle(neighbors);

            boolean found = false;
            for (int[] neighbor : neighbors) {
                int newRow = neighbor[0];
                int newCol = neighbor[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (maze[newRow][newCol] == 1) {
                        maze[currentRow + (newRow - currentRow) / 2][currentCol + (newCol - currentCol) / 2] = 0;
                        maze[newRow][newCol] = 0;
                        stack.push(new int[]{newRow, newCol});
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                stack.pop();
            }
        }

        return maze;
    }

    private static void displayMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                if (cell == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
