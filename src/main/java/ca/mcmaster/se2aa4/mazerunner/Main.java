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

        int[][] maze = new int[5][5];

        try {


            String filename = "./examples/straight.maz.txt";
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("i")){
                filename = cmd.getOptionValue("i");

            }

            logger.info("**** Reading the maze from file " + filename);
            System.out.println(new File(filename).getAbsolutePath());

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            logger.debug("Debugging log message");

            int counter = 0;
            //int prevLineLength = 0;

            while ((line = reader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {

                    if (line.charAt(i) == '#') {
                        logger.trace("WALL ");
                        maze[counter][i] = 0;
                    } else if (line.charAt(i) == ' ') {
                        logger.trace("PASS ");
                        if((i == 0) || (i == 4)){
                            maze[counter][i] = 2;
                        }else{
                            maze[counter][i] = 1;
                        }
                    }
                }

                if(line.length() == 0){
                    for(int i=0;i<5;i++){
                        if(i == 4){
                            maze[counter][i] = 2;
                        }else{
                            maze[counter][i] = 1;
                        }
                    }
                }

                counter++;
                logger.info(System.lineSeparator());
            }
            reader.close();
        }catch(FileNotFoundException e){
            
            logger.error("File could not be located");

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        System.out.println(findPath(maze, 2, 0,""));

        //Print Maze
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }


        logger.trace("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.trace("** End of MazeRunner");
    }

    public static String findPath(int[][] maze,int row,int col,String path){

        while(maze[row][col] != 2){
            path+="F";
            col += 1;
        }

        return path;
    }
}
