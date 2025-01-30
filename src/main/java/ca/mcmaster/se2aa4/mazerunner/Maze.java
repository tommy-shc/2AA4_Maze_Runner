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
        return findPathRHRHelper(entryPoint,0,"E","");


    }

    public String getFactorizedForm(String path){

        char lastChar = 'Z';
        String newPath = "";
        int counter = 1;

        for(int i=0;i<path.length();i++){
            if(lastChar != 'Z'){

                if(lastChar == path.charAt(i)){
                    counter++;
                }

            }

            if(path.charAt(i)==' '){
                if(counter == 1){
                    newPath += lastChar + " ";
                }else{
                    newPath += counter+""+lastChar + " ";
                }
                counter = 1;
            }

            lastChar = path.charAt(i);
        }

        return newPath;
    }

    private String findPathRHRHelper(int x,int y,String facingDirection,String path){
        
        if(maze[x][y] == 2){
            return path + " ";
        }

        //Which direction currently facing

        if(facingDirection.equals("E")){
            //Move right
            if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+"R F");
            //Move Forward
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+"F");
            //Move Left
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+" L F");
            //Move Backward
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+" RR F");
            }
        }else if(facingDirection.equals("S")){
            //Move right
            if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+" R F");
            //Move Forward
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+"F");
            //Move Left
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+" L F");
            //Move Backward
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"W",path+" RR F");
            }
        }else if(facingDirection.equals("W")){
            //Move right
            if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+" R F");
            //Move Forward
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+"F");
            //Move Left
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x,y+1,"S",path+" L F");
            //Move Backward
            }else if(isValidMove(x,y+1)){
                return findPathRHRHelper(x,y+1,"E",path+" RR F");
            }
        }else if(facingDirection.equals("N")){
            //Move right
            if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y+1,"E",path+" R F");
            //Move Forward
            }else if(isValidMove(x-1,y)){
                return findPathRHRHelper(x-1,y,"N",path+"F");
            //Move Left
            }else if(isValidMove(x,y-1)){
                return findPathRHRHelper(x,y-1,"W",path+" L F");
            //Move Backward
            }else if(isValidMove(x+1,y)){
                return findPathRHRHelper(x+1,y,"S",path+" RR F");
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
