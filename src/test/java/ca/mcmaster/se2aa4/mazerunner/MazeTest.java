package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MazeTest {
    
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
    void getValueTest(){

        assertEquals(0, maze.getValue(0,0));

    }

    @Test
    void isValidMoveTrueTest(){

        assertTrue(maze.isValidMove(5, 1));

    }

    @Test
    void isValidMoveFalseTest(){

        assertFalse(maze.isValidMove(7, 8));

    }


}
