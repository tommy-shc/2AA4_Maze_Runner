package ca.mcmaster.se2aa4.mazerunner;

public class MazeVerifier{

    private Maze maze;

    MazeVerifier(Maze maze){
        this.maze = maze;
    }
    public boolean verifyPath(String path) {
        String[] components = path.split(" ");
        int count;
        String move;
        String currentDir = "E"; // Start facing East
        int currentX = maze.getWestEntrance();
        int currentY = 0;

        try {

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
            if (maze.getValue(currentX, currentY) == 2) {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }
}