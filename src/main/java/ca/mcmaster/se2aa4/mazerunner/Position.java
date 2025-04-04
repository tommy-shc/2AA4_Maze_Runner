package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    
    private int x;
    private int y;
    private char direction;

    
    public Position(int x,int y,char direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public char getDir(){
        return this.direction;
    } 

    public void setDir(char dir){
        this.direction = dir;
    }



}
