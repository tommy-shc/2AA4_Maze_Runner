package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MazeSolverTest {

    int[][] tempMaze = {
        {1, 1, 1, 1, 1, 1, 1},
        {2, 2, 1, 2, 2, 1, 2},
        {2, 1, 1, 1, 1, 1, 2},
        {2, 2, 1, 2, 2, 1, 2},
        {1, 1, 1, 1, 1, 1, 1},
        {2, 2, 2, 2, 2, 2, 2}
    };

    Maze maze = new Maze(tempMaze,6, 7);

    @Test
    void basicFactorizedPathTest() {

        MazeSolver ms = new MazeSolver(maze);

        // Normal case
        assertEquals("4F ",ms.getFactorizedForm("FFFF "));
    }
}
