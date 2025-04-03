package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    // Logger for tracking application events
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        // Define command-line options
        Options options = new Options();
        options.addOption("i", "input", true, "Path to the maze file");
        options.addOption("p", "input", true, "Path to solve the maze");

        CommandLineParser parser = new DefaultParser();
        Maze maze;

        try {
            // Default maze file path
            String filename = "./examples/straight.maz.txt";
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                filename = cmd.getOptionValue("i");

                logger.info("**** Reading the maze from file " + filename);
                //System.out.println(new File(filename).getAbsolutePath());

                // Initialize readers for the maze file
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                BufferedReader readerTwo = new BufferedReader(new FileReader(filename));
                String line;

                // Initialize variables to store maze dimensions
                int numOfRows = 0;
                int numOfColumns = 0;

                // First pass: Calculate maze dimensions
                while ((line = readerTwo.readLine()) != null) {
                    numOfColumns = 0;
                    for (int i = 0; i < line.length(); i++) {
                        numOfColumns++;
                    }
                    numOfRows++;
                }

                // Initialize maze with calculated dimensions
                int[][] tempMaze = new int[numOfRows][numOfColumns];
                logger.info("**** Second Read");
                logger.info(numOfRows);
                logger.info(numOfColumns);

                // Second pass: Populate maze data
                for (int i = 0; i < numOfRows; i++) {
                    line = reader.readLine();
                    if (line.equals("")) {
                        // Handle empty lines
                        for (int j = 0; j < numOfColumns; j++) {
                            if ((j == 0) || (j == numOfColumns - 1)) {
                                tempMaze[i][j] = 2; // Mark as boundary
                            } else {
                                tempMaze[i][j] = 1; // Mark as passable
                            }
                        }
                    } else if (line.length() < numOfColumns) {
                        // Handle lines shorter than expected
                        for (int j = 0; j < numOfColumns; j++) {
                            if (j < line.length()) {
                                if (line.charAt(j) == '#') {
                                    tempMaze[i][j] = 0; // Mark as wall
                                } else if (line.charAt(j) == ' ') {
                                    if ((j == 0) || (j == numOfColumns - 1)) {
                                        tempMaze[i][j] = 2; // Mark as boundary
                                    } else {
                                        tempMaze[i][j] = 1; // Mark as passable
                                    }
                                }
                            } else {
                                if ((j == 0) || (j == numOfColumns - 1)) {
                                    tempMaze[i][j] = 2; // Mark as boundary
                                } else {
                                    tempMaze[i][j] = 1; // Mark as passable
                                }
                            }
                        }
                    } else {
                        // Handle lines of expected length
                        for (int j = 0; j < numOfColumns; j++) {
                            if (line.charAt(j) == '#') {
                                tempMaze[i][j] = 0; // Mark as wall
                            } else if (line.charAt(j) == ' ') {
                                if ((j == 0) || (j == numOfColumns - 1)) {
                                    tempMaze[i][j] = 2; // Mark as boundary
                                } else {
                                    tempMaze[i][j] = 1; // Mark as passable
                                }
                            }
                        }
                    }
                }

                maze = new Maze(tempMaze,numOfRows, numOfColumns);
                maze.displayMaze();

                logger.info("**** Displaying Path");
                //maze.displayMaze();

                // Handle path computation or verification based on command-line options
                if (!cmd.hasOption("p")) {
                    logger.trace("**** Computing path");
                    MazeSolver mazeSolver = new MazeSolver(maze);
                    String path = mazeSolver.findPathRHR(); // Compute path using Right-Hand Rule
                    System.out.println(path); //Display unfoactorized path
                    System.out.println(mazeSolver.getFactorizedForm(path)); // Display factorized path
                } else {
                    logger.trace("**** Verifying Path");
                    MazeVerifier mazeVerifer = new MazeVerifier(maze);
                    if (mazeVerifer.verifyPath(cmd.getOptionValue("p"))) {
                        System.out.println("correct path");
                    } else {
                        System.out.println("incorrect path");
                    }
                }

                // Close file readers
                reader.close();
                readerTwo.close();
            }

        } catch (FileNotFoundException e) {
            // Handle file not found exception
            logger.info("PATH NOT COMPUTED");
            logger.error("File could not be located");

        } catch (IOException e) {
            // Handle IO exceptions
            logger.info("PATH NOT COMPUTED");
            logger.error("IOexception");

        } catch (Exception e) {
            // Handle generic exceptions
            logger.info("PATH NOT COMPUTED");
            logger.error("Error");
        }

        logger.trace("** End of MazeRunner");
    }
}