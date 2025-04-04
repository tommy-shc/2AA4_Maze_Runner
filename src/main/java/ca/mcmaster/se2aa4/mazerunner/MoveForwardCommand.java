package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand implements MoveCommand{

    @Override
    public void execute(Position pos) {
        switch (pos.getDir()) {
            case 'E' -> pos.setY(pos.getY()+1);
            case 'S' -> pos.setX(pos.getX()+1);
            case 'W' -> pos.setY(pos.getY()-1);
            case 'N' -> pos.setX(pos.getX()-1);
        }
        
    }
    
}
