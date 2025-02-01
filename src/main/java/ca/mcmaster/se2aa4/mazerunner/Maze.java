package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    // 2D array to represent the maze structure
    private int[][] maze;
    private int numOfRows; // Number of rows in the maze
    private int numOfColumns; // Number of columns in the maze

    // Constructor to initialize the maze with given dimensions
    Maze(int row, int column) {
        this.maze = new int[row][column];
        this.numOfRows = row;
        this.numOfColumns = column;
    }

    // Set a value at a specific position in the maze
    public void setValue(int value, int x, int y) {
        maze[x][y] = value;
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

    // Verify if a given path is valid in the maze
    public boolean verifyPath(String path) {
        String[] components = path.split(" ");
        int count;
        String move;
        String currentDir = "E"; // Start facing East
        int currentX = 0;
        int currentY = 0;

        // Find the entry point on the West side of the maze
        for (int i = 0; i < numOfRows; i++) {
            if (maze[i][0] == 2) {
                currentX = i;
                maze[i][0] = 0; // Mark entry point as visited
                break;
            }
        }

        // Process each component of the path
        for (String component : components) {
            if (component.isEmpty()) {
                continue; // Skip empty components
            }

            count = 1;
            if (component.length() > 1 && component.charAt(0) > 48 && component.charAt(0) < 58) {
                // Handle multi-step moves (e.g., "2F" means move forward twice)
                count = Integer.parseInt(component.substring(0, 1));
                move = component.substring(1);
            } else {
                // Handle single-step moves
                move = component;
            }

            // Convert a left move to three right moves (simplifies logic)
            if (move.equals("L")) {
                count = 3;
                move = "R";
            }

            // Execute the move 'count' times
            for (int i = 0; i < count; i++) {
                if (move.equals("F")) {
                    // Move forward based on current direction
                    if (currentDir.equals("E")) {
                        currentY += 1;
                    } else if (currentDir.equals("S")) {
                        currentX += 1;
                    } else if (currentDir.equals("W")) {
                        currentY -= 1;
                    } else if (currentDir.equals("N")) {
                        currentX -= 1;
                    }
                } else {
                    // Change direction (right turn)
                    if (currentDir.equals("E")) {
                        currentDir = "S";
                    } else if (currentDir.equals("S")) {
                        currentDir = "W";
                    } else if (currentDir.equals("W")) {
                        currentDir = "N";
                    } else if (currentDir.equals("N")) {
                        currentDir = "E";
                    }
                }
            }
        }

        // Check if the final position is the exit
        if (maze[currentX][currentY] == 2) {
            return true;
        }

        return false;
    }

    // Find a path through the maze using the Right-Hand Rule
    public String findPathRHR() {
        int entryPoint = 0;

        // Find the entry point on the West side of the maze
        for (int i = 0; i < numOfRows; i++) {
            if (maze[i][0] == 2) {
                entryPoint = i;
                maze[i][0] = 0; // Mark entry point as visited
                break;
            }
        }
        return findPathRHRHelper(entryPoint, 0, "E", ""); // Start at entry point, facing East
    }

    // Convert a path into a factorized form (e.g., "F F F" becomes "3F")
    public String getFactorizedForm(String path) {
        char lastChar = 'Z'; // Placeholder for the last character processed
        String newPath = "";
        int counter = 1;

        for (int i = 0; i < path.length(); i++) {
            if (lastChar != 'Z') {
                if (lastChar == path.charAt(i)) {
                    counter++; // Count consecutive moves
                }
            }

            if (path.charAt(i) == ' ') {
                // Append the factorized move to the new path
                if (counter == 1) {
                    newPath += lastChar + " ";
                } else {
                    newPath += counter + "" + lastChar + " ";
                }
                counter = 1; // Reset counter
            }

            lastChar = path.charAt(i);
        }

        return newPath;
    }

    // Helper method for the Right-Hand Rule pathfinding algorithm
    private String findPathRHRHelper(int currentX, int currentY, String dir, String path) {
        String facingDirection = dir;
        int x = currentX;
        int y = currentY;

        // Traverse the maze until the exit is found
        while (maze[x][y] != 2) {
            if (facingDirection.equals("E")) {
                // Try moving right, forward, left, or backward
                if (isValidMove(x + 1, y)) {
                    x += 1;
                    facingDirection = "S";
                    path += " R F";
                } else if (isValidMove(x, y + 1)) {
                    y += 1;
                    facingDirection = "E";
                    path += "F";
                } else if (isValidMove(x - 1, y)) {
                    x -= 1;
                    facingDirection = "N";
                    path += " L F";
                } else if (isValidMove(x, y - 1)) {
                    y -= 1;
                    facingDirection = "W";
                    path += " RR F";
                }
            } else if (facingDirection.equals("S")) {
                // Similar logic for South-facing direction
                if (isValidMove(x, y - 1)) {
                    y -= 1;
                    facingDirection = "W";
                    path += " R F";
                } else if (isValidMove(x + 1, y)) {
                    x += 1;
                    facingDirection = "S";
                    path += "F";
                } else if (isValidMove(x, y + 1)) {
                    y += 1;
                    facingDirection = "E";
                    path += " L F";
                } else if (isValidMove(x - 1, y)) {
                    x -= 1;
                    facingDirection = "N";
                    path += " RR F";
                }
            } else if (facingDirection.equals("W")) {
                // Similar logic for West-facing direction
                if (isValidMove(x - 1, y)) {
                    x -= 1;
                    facingDirection = "N";
                    path += " R F";
                } else if (isValidMove(x, y - 1)) {
                    y -= 1;
                    facingDirection = "W";
                    path += "F";
                } else if (isValidMove(x + 1, y)) {
                    x += 1;
                    facingDirection = "S";
                    path += " L F";
                } else if (isValidMove(x, y + 1)) {
                    y += 1;
                    facingDirection = "E";
                    path += " RR F";
                }
            } else if (facingDirection.equals("N")) {
                // Similar logic for North-facing direction
                if (isValidMove(x, y + 1)) {
                    y += 1;
                    facingDirection = "E";
                    path += " R F";
                } else if (isValidMove(x - 1, y)) {
                    x -= 1;
                    facingDirection = "N";
                    path += "F";
                } else if (isValidMove(x, y - 1)) {
                    y -= 1;
                    facingDirection = "W";
                    path += " L F";
                } else if (isValidMove(x + 1, y)) {
                    x += 1;
                    facingDirection = "S";
                    path += " RR F";
                }
            }
        }

        return path + " ";
    }

    // Check if a move to a specific position is valid
    private Boolean isValidMove(int x, int y) {
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