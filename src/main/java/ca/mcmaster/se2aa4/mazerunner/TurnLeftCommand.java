package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements MoveCommand {
    @Override
    public void execute(Position pos) {
        switch (pos.getDir()) {
            case 'E' -> pos.setDir('N');
            case 'S' -> pos.setDir('E');
            case 'W' -> pos.setDir('S');
            case 'N' -> pos.setDir('W');
        }
    }
}
