import java.util.*;

public class Ass_6_11_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            // Build the Graph
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            
            int s = sc.nextInt(); // Source section
            int d = sc.nextInt(); // Destination section
            
            // 1. Find shortest path using BFS
            int shortestPath = bfs(s, d, n, adj);
            System.out.println(shortestPath);
            
            // 2. Detect cycle using DFS
            boolean hasCycle = checkCycle(n, adj);
            if (hasCycle) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }
    
    // BFS implementation for Shortest Path
    static int bfs(int start, int target, int n, List<List<Integer>> adj) {
        int[] distance = new int[n];
        Arrays.fill(distance, -1); // -1 means unvisited
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        distance[start] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            if (current == target) {
                return distance[current];
            }
            
            for (int neighbor : adj.get(current)) {
                if (distance[neighbor] == -1) { // If not visited
                    distance[neighbor] = distance[current] + 1;
                    queue.add(neighbor);
                }
            }
        }
        return -1; // Path not found
    }
    
    // Setup for DFS cycle check across all disconnected parts of the warehouse
    static boolean checkCycle(int n, List<List<Integer>> adj) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // -1 represents that the starting node has no parent
                if (dfs(i, -1, visited, adj)) {
                    return true; 
                }
            }
        }
        return false;
    }
    
    // DFS implementation for Cycle Detection
    static boolean dfs(int node, int parent, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, visited, adj)) {
                    return true;
                }
            } 
            // If the neighbor IS visited, but it's not the section we just came from...
            else if (neighbor != parent) {
                return true; // We found a cycle!
            }
        }
        return false;
    }
}
