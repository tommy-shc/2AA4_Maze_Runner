package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MazeFactory {

    // Create Maze from 2D int array
    public static Maze createFromArray(int[][] mazeArray) {
        return new Maze(mazeArray, mazeArray.length, mazeArray[0].length);
    }

    // Create Maze from a text file
    public static Maze createFromFile(String filePath) {
        List<int[]> mazeList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] row = line.chars()
                        .filter(Character::isDigit)
                        .map(c -> c - '0')
                        .toArray();
                mazeList.add(row);
            }
        } catch (Exception e) {
            System.out.println("Error reading maze file: ");
            return null;
        }

        int rowCount = mazeList.size();
        int colCount = mazeList.get(0).length;
        int[][] mazeArray = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            mazeArray[i] = mazeList.get(i);
        }

        return new Maze(mazeArray, rowCount, colCount);
    }
}
