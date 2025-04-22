public class Main {
    public static void main(String[] args) {
        System.out.println("Network Max Flow Solver\n");

        NetworkFileParser parser = new NetworkFileParser();
        int[][] capacityArray = parser.chooseAndParseFile();

        // Print capacityArray (which contains capacities along the edges)
        if (capacityArray != null) {
            parser.printCapacityMatrix(capacityArray);
        } else {
            System.out.println("No file was selected or an error occurred.");
        }


    }
}
