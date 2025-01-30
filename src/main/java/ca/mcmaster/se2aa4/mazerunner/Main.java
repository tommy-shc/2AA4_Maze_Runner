package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

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

            String filename = "./examples/small.maz.txt";
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
            logger.info("**** Second Read");
            logger.info(numOfRows);
            logger.info(numOfColumns);
            //Second read to get maze data
            for (int i=0;i<numOfRows;i++){
                logger.info("read: " + i);
                line = reader.readLine();
                if(line.equals("")){
                    for(int j=0;j<numOfColumns;j++){
                        if((j == 0) || (j == numOfColumns-1)){
                            maze.setValue(2, i, j);
                        }else{
                            maze.setValue(1, i, j);
                        }
                    }
                }else if(line.length() < numOfColumns){
                    for(int j=0;j<numOfColumns;j++){
                        if(j<line.length()){
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
                        }else{
                            if((j == 0) || (j == numOfColumns-1)){
                                maze.setValue(2, i, j);
                            }else{
                                maze.setValue(1, i, j);
                            }
                        }
                    }
                }else{
                    for(int j=0;j<numOfColumns;j++){
                        System.out.print(j + " ");
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
                }
            }

            logger.info("**** Displaying Path");
            //maze.displayMaze();

            logger.trace("**** Computing path");
            String path = maze.findPathRHR();
            //System.out.println(path);
            System.out.println(maze.getFactorizedForm(path));

            reader.close();
            readerTwo.close();
        }catch(FileNotFoundException e){

            logger.info("PATH NOT COMPUTED");
            logger.error("File could not be located");

        } catch(IOException e) {
            logger.info("PATH NOT COMPUTED");
            logger.error("IOexception");

        } catch(Exception e) {
            logger.info("PATH NOT COMPUTED");
            logger.error("Error");
        }

        logger.trace("** End of MazeRunner");
    }
}
