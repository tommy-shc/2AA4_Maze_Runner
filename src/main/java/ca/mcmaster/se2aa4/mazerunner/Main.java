package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i","input",true,"Path to the maze file");

        CommandLineParser parser = new DefaultParser();

        Maze maze;
        
        try {


            String filename = "./examples/tiny.maz.txt";
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("i")){
                filename = cmd.getOptionValue("i");
            }

            logger.info("**** Reading the maze from file " + filename);
            System.out.println(new File(filename).getAbsolutePath());

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            BufferedReader readerTwo = new BufferedReader(new FileReader(filename));
            String line;

            //Initialize variables for file reading
            int numOfRows = 0;
            int numOfColumns = 0;
            
            //Initial read to calculate maze size
            while ((line = readerTwo.readLine()) != null) {
                numOfColumns = 0;
                for (int i=0;i<line.length();i++){
                    numOfColumns++;
                }
                numOfRows++;

            }

            maze = new Maze(numOfRows, numOfColumns);

            //Second read to get maze data
            for (int i=0;i<numOfRows;i++){
                line = reader.readLine();
                for(int j=0;j<numOfColumns;j++){

                    if (line.charAt(j) == '#') {
                        //logger.trace("WALL ");
                        maze.setValue(0, i, j);
                    } else if (line.charAt(j) == ' ') {
                        //logger.trace("PASS ");
                        if((j == 0) || (j == numOfColumns-1)){
                            maze.setValue(2, i, j);
                        }else{
                            maze.setValue(1, i, j);
                        }
                    }
                }

                //Account for full empty line

            }

            maze.displayMaze();
            System.out.println(maze.findPathRHR());

            reader.close();
            readerTwo.close();
        }catch(FileNotFoundException e){
            
            logger.error("File could not be located");

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        logger.trace("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.trace("** End of MazeRunner");
    }
}
