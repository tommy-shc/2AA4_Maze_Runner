package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements MoveCommand{
    @Override
    public void execute(Position pos) {
        switch (pos.getDir()) {
            case 'E' -> pos.setDir('S');
            case 'S' -> pos.setDir('W');
            case 'W' -> pos.setDir('N');
            case 'N' -> pos.setDir('E');
        }
        
    }
    
}
