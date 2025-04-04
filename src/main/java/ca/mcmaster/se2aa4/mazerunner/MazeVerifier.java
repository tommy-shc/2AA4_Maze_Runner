package ca.mcmaster.se2aa4.mazerunner;

import java.util.LinkedList;
import java.util.Queue;

public class MazeVerifier{

    private Maze maze;

    MazeVerifier(Maze maze){
        this.maze = maze;
    }
    public boolean verifyPath(String path) {
        String[] components = path.split(" ");
        int count;
        String move;

        Position pos = new Position(maze.getWestEntrance(),0,'E');

        Queue<MoveCommand> moveQueue = new LinkedList<>();

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

                // Execute the move 'count' times
                for (int i = 0; i < count; i++) {
                    if (move.equals("F")) {            
                        moveQueue.add(new MoveForwardCommand());

                    } else if (move.equals("L")){
                        // Change direction (left turn)
                        moveQueue.add(new TurnLeftCommand());
                    
                    } else {
                        // Change direction (right turn)
                        moveQueue.add(new TurnRightCommand());
                    }
                }
            }

            while(!moveQueue.isEmpty()){
                moveQueue.remove().execute(pos);
            }

            // Check if the final position is the exit
            if (maze.getValue(pos.getX(), pos.getY()) == 2) {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }
}