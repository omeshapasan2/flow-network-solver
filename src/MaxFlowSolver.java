import java.util.LinkedList;
import java.util.Queue;

public class MaxFlowSolver {
    public int edmondsKarp(int[][] capacityArray, int source, int sink){
        //Edmond's Karp Algo (calculate max flow)
        int n = capacityArray.length;
        int[][] rGraph = new int[n][n]; // Initialize residual graph
        int[] parent = new int[n]; // Parent array for storing BFS path
        int maxFlow = 0; // Final Result

        // Initialize residual graph with original capacities
        for( int i=0 ; i<n ; i++ ){
           for( int j=0 ; j<n ; j++ ){
               rGraph[i][j] = capacityArray[i][j];
           }
        }

        System.out.println("Starting Edmonds-Karp Algorithm... \n");

        // Augment the flow while there is a path from source to sink
        while (bfs(rGraph,source,sink,parent)){
            // Find the bottleneck (minimum residual capacity) in the path found
            int pathFlow = Integer.MAX_VALUE;
            for (int v = sink ; v != source ; v = parent[v]){
                int u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            System.out.println("Found augmenting path with flow = " + pathFlow);

            // Update residual capacities of the edges and reverse edges
            for( int v = sink; v != source ; v = parent[v] ){
                int u = parent[v];
                rGraph[u][v] -= pathFlow ;
                rGraph[v][u] += pathFlow ;

                System.out.println("Updated residual capacity: " + u + "->" + v + " = " + rGraph[u][v] );
                System.out.println("Updated reverse capacity: " + v + "->" + u + " = " + rGraph[v][u] );
            }

            maxFlow += pathFlow ;
            System.out.println("Current Max Flow = " + maxFlow + "\n");

        }

        System.out.println("Edmonds-Karp Finished. Max Flow = " + maxFlow);
        return maxFlow;
    }

    private boolean bfs(int[][] rGraph, int source, int sink, int[] parent){
        // BFS method (used to find paths)
        int n = rGraph.length; // Number of nodes
        boolean[] visited = new boolean[n]; // To mark visited nodes

        Queue<Integer> queue = new LinkedList<>(); // Create a queue for BFS
        queue.add(source); // Start from source
        visited[source] = true;
        parent[source] = -1; // Source has no parent

        System.out.println("Starting BFS from source node: " + source );

        while(!queue.isEmpty()){
            int u = queue.poll(); // Take the first node from queue
            System.out.println("Dequeued Node: " + u);

            for( int v=0 ; v<n ; v++ ){  // Check all other Nodes
                // Check if "v is not visited" and "there's capacity from u to v"
                if( !visited[v] && rGraph[u][v] > 0 ){
                    queue.add(v); // Add to queue to check its neighbors later
                    parent[v] = u; // Remember how we reached this node
                    visited[v] = true;

                    System.out.println("Found path to node" + v + "(From node " + u + "), remaining capacity: " + rGraph[u][v]);

                    // Stop if reached the sink
                    if( v== sink ){
                        System.out.println("Reached sink node: " + sink );
                        return true;
                    }
                }
            }
        }

        System.out.println("No augmenting path found from source to sink");

        return false;
        
    }
}
