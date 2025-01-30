package ca.mcmaster.se2aa4.mazerunner;

public class Maze {

    private int[][] maze;
    private int numOfRows;
    private int numOfColumns;
    
    Maze(int row,int column){
        this.maze = new int[row][column];
        this.numOfRows = row;
        this.numOfColumns = column;
    }

    public void setValue(int value,int x,int y){
        maze[x][y] = value;
    }

    public int getValue(int x,int y){
        return maze[x][y];
    }

    public void displayMaze(){
        for(int i=0;i<numOfRows;i++){
            for(int j=0;j<numOfColumns;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    //Right hand rule implementation
    public String findPathRHR(){
        int entryPoint = 0;
        //Find West Entry location
        for(int i = 0;i<numOfRows;i++){
            if(maze[i][0] == 2){
                entryPoint = i;
                maze[i][0] = 0;
                break;
            }
        }
        String path = "Path -> ";
        path = findPathRHRHelper(entryPoint,1,"E",path);
        return path;

    }

    private String findPathRHRHelper(int x,int y,String facingDirection,String path){

        System.out.println(path);
        
        if(maze[x][y] == 2){
            return path;
        }

        //Which direction currently facing

        if(facingDirection.equals("E")){
            //Move right
            if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+"R");
            //Move Forward
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+"F");
            //Move Left
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+"L");
            //Move Backward
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+"B");
            }
        }else if(facingDirection.equals("S")){
            //Move right
            if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+"R");
            //Move Forward
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+"F");
            //Move Left
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+"L");
            //Move Backward
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"W",path+"B");
            }
        }else if(facingDirection.equals("W")){
            //Move right
            if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+"R");
            //Move Forward
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+"F");
            //Move Left
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x,y+1,"S",path+"L");
            //Move Backward
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+"B");
            }
        }else if(facingDirection.equals("N")){
            //Move right
            if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y+1,"E",path+"R");
            //Move Forward
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+"F");
            //Move Left
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+"L");
            //Move Backward
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+"B");
            }
        }

        //No path found 
        return "NO Path";
    }

    private Boolean isValidMove(int x,int y){
        try{
            if(maze[x][y] > 0){
                return true;
            }
        }catch(Exception e){
            return false;
        }

        return false;
    }
    
}
