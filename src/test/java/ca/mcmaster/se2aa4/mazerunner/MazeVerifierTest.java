package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class MazeVerifierTest {
    
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
    void verifyPathSmallMazeTest(){
        MazeVerifier mv = new MazeVerifier(maze);

        assertTrue(mv.verifyPath("5F 2R 2F R 2F R 2F 2R 2F R 2F R 3F "));

    }

    @Test
    void verifyPathWrongPathForSmallMazeTest(){
        MazeVerifier mv = new MazeVerifier(maze);

        assertFalse(mv.verifyPath("R 2F 2R 2F R 2F R 3F "));

    }


}
