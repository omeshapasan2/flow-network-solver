import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class NetworkFileParser {
    public int[][] chooseAndParseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(null);

        int[][] capacityArray = null;
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                String nodesCount = reader.readLine();
                int n = Integer.parseInt(nodesCount.trim());
                capacityArray = new int[n][n];

                System.out.println("Nodes Count is " + nodesCount + "\n");
                System.out.println("A=Node 1 , B= Node 2 , F = Capacity \nA B F");

                // Read Each line one by one in the file
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);

                    // Split the line into parts (from, to, capacity)
                    String[] parts = line.split(" ");
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    int capacity = Integer.parseInt(parts[2]);

                    // Storing capacity in the 2D array for easy access.
                    capacityArray[from][to] = capacity;
                }

            } catch (IOException e) {
                System.err.println("Error reading the File: " + e.getMessage());
            }
        }
        return capacityArray;
    }

//    public void printCapacityMatrix(int[][] capacityArray) {
//        int n = capacityArray.length;
//
//        System.out.println("\nCapacity Matrix:");
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(capacityArray[i][j] + "\t");
//            }
//            System.out.println();
//        }
//    }

}
