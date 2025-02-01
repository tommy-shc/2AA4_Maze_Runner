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

    public boolean verifyPath(String path){

        String[] components = path.split(" ");
        int count;
        String move;
        String currentDir = "E";
        int currentX = 0;
        int currentY = 0;
        //Find West Entry location
        for(int i = 0;i<numOfRows;i++){
            if(maze[i][0] == 2){
                currentX = i;
                maze[i][0] = 0;
                break;
            }
        }


        for(String component:components){

            if(component.isEmpty()){
                continue;
            }

            count = 1;
            if(component.length() > 1 && component.charAt(0) > 48 && component.charAt(0) < 58) {

                count = Integer.parseInt(component.substring(0, 1));
                move = component.substring(1);
            }else{
                //Single move
                move = component;
            }
            //Convert a left move to 3 right moves
            if(move.equals("L")){
                count = 3;
                move = "R";
            }
            //Execute the move 'count' times
            for (int i = 0; i < count; i++) {
                //Move
                if(move.equals("F")){
                    if(currentDir.equals("E")){
                        currentY += 1;
                    }else if(currentDir.equals("S")){
                        currentX += 1;
                    }else if(currentDir.equals("W")){
                        currentY -= 1;
                    }else if(currentDir.equals("N")){
                        currentY -= 1;
                    }
                
                ///Change Direction
                }else{
                    if(currentDir.equals("E")){
                        currentDir = "S";
                    }else if(currentDir.equals("S")){
                        currentDir = "W";
                    }else if(currentDir.equals("W")){
                        currentDir = "N";
                    }else if(currentDir.equals("N")){
                        currentDir = "E";
                    }
                }
            }

        }

        if(maze[currentX][currentY] == 2){
            return true;
        }

        return false;
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

        System.out.println(entryPoint);
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

    private String findPathRHRHelper(int currentX,int currentY,String dir,String path){
        
        String facingDirection = dir;
        int x = currentX;
        int y = currentY;

        while(maze[x][y] != 2){
            if(facingDirection.equals("E")){
                //Move right
                if(isValidMove(x+1,y)){
                    x += 1;
                    facingDirection = "S";
                    path += " R F";
                //Move Forward
                }else if(isValidMove(x,y+1)){
                    y += 1;
                    facingDirection = "E";
                    path += "F";
                //Move Left
                }else if(isValidMove(x-1,y)){
                    x -= 1;
                    facingDirection = "N";
                    path += " L F";
                //Move Backward
                }else if(isValidMove(x,y-1)){
                    y -= 1;
                    facingDirection = "W";
                    path += " RR F";
                }
            }else if(facingDirection.equals("S")){
                //Move right
                if(isValidMove(x,y-1)){
                    y -= 1;
                    facingDirection = "W";
                    path += " R F";
                //Move Forward
                }else if(isValidMove(x+1,y)){
                    x += 1;
                    facingDirection = "S";
                    path += "F";
                //Move Left
                }else if(isValidMove(x,y+1)){
                    y += 1;
                    facingDirection = "E";
                    path += " L F";
                //Move Backward
                }else if(isValidMove(x-1,y)){
                    x -= 1;
                    facingDirection = "N";
                    path += " RR F";
                }
            }else if(facingDirection.equals("W")){
                //Move right
                if(isValidMove(x-1,y)){
                    x -= 1;
                    facingDirection = "N";
                    path += " R F";
                //Move Forward
                }else if(isValidMove(x,y-1)){
                    y -= 1;
                    facingDirection = "W";
                    path += "F";
                //Move Left
                }else if(isValidMove(x+1,y)){
                    x += 1;
                    facingDirection = "S";
                    path += " L F";
                //Move Backward
                }else if(isValidMove(x,y+1)){
                    y += 1;
                    facingDirection = "E";
                    path += " RR F";
                }
            }else if(facingDirection.equals("N")){
                //Move right
                if(isValidMove(x,y+1)){
                    y += 1;
                    facingDirection = "E";
                    path += " R F";
                //Move Forward
                }else if(isValidMove(x-1,y)){
                    x -= 1;
                    facingDirection = "N";
                    path += "F";
                //Move Left
                }else if(isValidMove(x,y-1)){
                    y -= 1;
                    facingDirection = "W";
                    path += " L F";
                //Move Backward
                }else if(isValidMove(x+1,y)){
                    x += 1;
                    facingDirection = "S";
                    path += " RR F";
                }
            }

        }

        return path + " ";
    }

    private Boolean isValidMove(int x,int y){

        if(x < 0 || x > numOfRows || y < 0 || y > numOfColumns){
            return false;
        }

        if(maze[x][y] == 0){
            return false;
        }

        return true;
    }
    
}