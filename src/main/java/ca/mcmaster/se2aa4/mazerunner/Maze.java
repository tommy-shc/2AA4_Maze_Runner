package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private int[][] maze;
    
    Maze(int row,int column){
        this.maze = new int[row][column];
    }

    public void setValue(int value,int x,int y){
        maze[x][y] = value;
    }

    public int getValue(int x,int y){
        return maze[x][y];
    }

    public void displayMaze(){
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    
}
