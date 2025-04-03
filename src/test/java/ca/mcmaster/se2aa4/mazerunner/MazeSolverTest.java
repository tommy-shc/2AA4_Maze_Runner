package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MazeSolverTest {

    int[][] tempMaze = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 2},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {2, 1, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0}
    };
    

    Maze maze = new Maze(tempMaze,6, 7);

    @Test
    void basicFactorizedPathTest() {

        MazeSolver ms = new MazeSolver(maze);

        // Normal case
        assertEquals("4F ",ms.getFactorizedForm("FFFF "));
    }

    @Test
    void longFactorizedPathTest(){

        MazeSolver ms = new MazeSolver(maze);

        assertEquals("5F 2R 2F R 2F R 2F 2R 2F R 2F R 3F ",ms.getFactorizedForm("FFFFF RR FF R FF R FF RR FF R FF R FFF "));
    }

    @Test
    void findPathRHRTinyMazeTest(){

        MazeSolver ms = new MazeSolver(maze);

        assertEquals("FFFFF RR FF R FF R FF RR FF R FF R FFF ",ms.findPathRHR());

    }

    @Test
    void findPathRHREmptyMazeTest(){

        int[][] tempMaze2 = {{}};

        Maze emptyMaze = new Maze(tempMaze2,0, 0);

        MazeSolver ms = new MazeSolver(emptyMaze);

        assertEquals("",ms.findPathRHR());

    }
}
