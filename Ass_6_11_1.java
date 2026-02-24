import java.util.*;

public class Ass_6_11_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int N = sc.nextInt(); // Number of nodes/computers
            int M = sc.nextInt(); // Number of edges/connections
            
            // 1. Build the Graph (Adjacency List)
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                adj.add(new ArrayList<>());
            }
            
            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                // Since it's an undirected network, connect both ways
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            
            // 2. Track visited nodes
            boolean[] visited = new boolean[N];
            int components = 0;
            
            // 3. Count Components using DFS
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    components++; // Found a new isolated subnetwork
                    dfs(i, adj, visited); // Mark all connected computers as visited
                }
            }
            
            System.out.println(components);
        }
        sc.close();
    }
    
    // Recursive DFS function
    public static void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        visited[node] = true; // Mark current computer as visited
        
        // Visit all its neighbors
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited);
            }
        }
    }
}