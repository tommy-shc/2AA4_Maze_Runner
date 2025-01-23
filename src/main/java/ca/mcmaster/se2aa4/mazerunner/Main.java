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

        try {


            String filename = "./examples/tiny.maz.txt";
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("i")){
                filename = cmd.getOptionValue("i");

            }

            logger.info("**** Reading the maze from file " + filename);
            System.out.println(new File(filename).getAbsolutePath());

            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            logger.debug("Debugging log message");

            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(i) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.info(System.lineSeparator());
            }
            reader.close();
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
