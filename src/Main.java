import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Network Max Flow Solver\n");

        NetworkFileParser parser = new NetworkFileParser();
        int[][] capacityArray = parser.chooseAndParseFile();

        // Print capacityArray (which contains capacities along the edges)
        if (capacityArray != null) {

//            parser.printCapacityMatrix(capacityArray);

            int n = capacityArray.length;
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nEnter Source Node (0 to " + (n-1) + "): ");
            int source = scanner.nextInt();

            System.out.print("Enter Sink Node (0 to " + (n-1) + "): ");
            int sink = scanner.nextInt();

            MaxFlowSolver solver = new MaxFlowSolver();
            int maxFlow = solver.edmondsKarp(capacityArray, source, sink);

            System.out.println("\nMaximum Flow from node " + source + " to node " + sink + " is: " + maxFlow);
        } else {
            System.out.println("No file was selected or an error occurred.");
        }


    }
}
