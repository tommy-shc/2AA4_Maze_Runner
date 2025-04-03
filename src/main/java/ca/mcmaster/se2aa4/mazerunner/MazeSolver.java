package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver{

    private Maze maze;

    public MazeSolver(Maze maze){
        this.maze = maze;
    }
    // Find a path through the maze using the Right-Hand Rule
    public String findPathRHR() {
        
        return findPathRHRHelper(maze.getWestEntrance(), 0, "E", ""); // Start at entry point, facing East
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

        try {
            
            // Traverse the maze until the exit is found
            while (maze.getValue(x, y) != 2) {
                if (facingDirection.equals("E")) {
                    // Try moving right, forward, left, or backward
                    if (maze.isValidMove(x + 1, y)) {
                        x += 1;
                        facingDirection = "S";
                        path += " R F";
                    } else if (maze.isValidMove(x, y + 1)) {
                        y += 1;
                        facingDirection = "E";
                        path += "F";
                    } else if (maze.isValidMove(x - 1, y)) {
                        x -= 1;
                        facingDirection = "N";
                        path += " L F";
                    } else if (maze.isValidMove(x, y - 1)) {
                        y -= 1;
                        facingDirection = "W";
                        path += " RR F";
                    }
                } else if (facingDirection.equals("S")) {
                    // Similar logic for South-facing direction
                    if (maze.isValidMove(x, y - 1)) {
                        y -= 1;
                        facingDirection = "W";
                        path += " R F";
                    } else if (maze.isValidMove(x + 1, y)) {
                        x += 1;
                        facingDirection = "S";
                        path += "F";
                    } else if (maze.isValidMove(x, y + 1)) {
                        y += 1;
                        facingDirection = "E";
                        path += " L F";
                    } else if (maze.isValidMove(x - 1, y)) {
                        x -= 1;
                        facingDirection = "N";
                        path += " RR F";
                    }
                } else if (facingDirection.equals("W")) {
                    // Similar logic for West-facing direction
                    if (maze.isValidMove(x - 1, y)) {
                        x -= 1;
                        facingDirection = "N";
                        path += " R F";
                    } else if (maze.isValidMove(x, y - 1)) {
                        y -= 1;
                        facingDirection = "W";
                        path += "F";
                    } else if (maze.isValidMove(x + 1, y)) {
                        x += 1;
                        facingDirection = "S";
                        path += " L F";
                    } else if (maze.isValidMove(x, y + 1)) {
                        y += 1;
                        facingDirection = "E";
                        path += " RR F";
                    }
                } else if (facingDirection.equals("N")) {
                    // Similar logic for North-facing direction
                    if (maze.isValidMove(x, y + 1)) {
                        y += 1;
                        facingDirection = "E";
                        path += " R F";
                    } else if (maze.isValidMove(x - 1, y)) {
                        x -= 1;
                        facingDirection = "N";
                        path += "F";
                    } else if (maze.isValidMove(x, y - 1)) {
                        y -= 1;
                        facingDirection = "W";
                        path += " L F";
                    } else if (maze.isValidMove(x + 1, y)) {
                        x += 1;
                        facingDirection = "S";
                        path += " RR F";
                    }
                }
            }

            return path + " ";

        } catch (Exception e) {
            return "";
        }

    }

}