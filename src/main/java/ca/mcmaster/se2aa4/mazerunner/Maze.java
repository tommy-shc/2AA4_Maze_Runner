package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    // 2D array to represent the maze structure
    private int[][] maze;
    private int numOfRows; // Number of rows in the maze
    private int numOfColumns; // Number of columns in the maze
    private int westEntrance = 0;

    // Constructor to initialize the maze with given dimensions
    public Maze(int[][] tempMaze,int row, int column) {
        this.maze = new int[row][column];

        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                this.maze[i][j] = tempMaze[i][j];
            }
        }
        this.numOfRows = row;
        this.numOfColumns = column;
        System.out.println("Reached here");
        markWestEntrance();
    }

    // Returns a copy of the current maze
    public int[][] getMaze(){
        int[][] mazeCopy = new int[numOfRows][numOfColumns];
        for(int i=0;i<numOfRows;i++){
            for(int j=0;j<numOfColumns;j++){
                mazeCopy[i][j] = maze[i][j];
            }
        }
        return mazeCopy;
    }

    // Marks the East entrance as visited
    private void markWestEntrance() {
        // Find the entry point on the West side of the maze
        for (int i = 0; i < this.numOfRows; i++) {
            if (maze[i][0] == 2) {
                System.out.println("Reached here 2");

                westEntrance = i;
                maze[i][0] = 0; // Mark entry point as visited
                return;
            }
        }
    }

    // Get the Row number of the West entrance
    public int getWestEntrance(){
        return westEntrance;
    }

    // Get the value at a specific position in the maze
    public int getValue(int x, int y) {
        return maze[x][y];
    }

    // Display the maze in the console
    public void displayMaze() {
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    // Check if a move to a specific position is valid
    public Boolean isValidMove(int x, int y) {
        // Check if the position is within bounds and not a wall
        if (x < 0 || x >= numOfRows || y < 0 || y >= numOfColumns) {
            return false;
        }

        if (maze[x][y] == 0) {
            return false;
        }

        return true;
    }

}