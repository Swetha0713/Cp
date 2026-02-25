import java.util.*;

// Helper class to store transaction routes
class Edge {
    int u, v, w;
    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}

public class Ass_6_12_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            
            Edge[] edges = new Edge[m];
            
            // Read all transaction routes
            for (int i = 0; i < m; i++) {
                edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
            }
            
            int s = sc.nextInt(); // Source node
            
            // Use long to prevent integer overflow with large constraints
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[s] = 0;
            
            // Step 1: Relax all edges N - 1 times
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < m; j++) {
                    int u = edges[j].u;
                    int v = edges[j].v;
                    int w = edges[j].w;
                    
                    if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
            
            // Step 2: Check for Negative Weight Cycles
            boolean hasNegativeCycle = false;
            for (int j = 0; j < m; j++) {
                int u = edges[j].u;
                int v = edges[j].v;
                int w = edges[j].w;
                
                // If we can STILL find a shorter path, a negative cycle exists!
                if (dist[u] != Long.MAX_VALUE && dist[u] + w < dist[v]) {
                    hasNegativeCycle = true;
                    break;
                }
            }
            
            // Output the results
            if (hasNegativeCycle) {
                System.out.println("NEGATIVE CYCLE");
            } else {
                for (int i = 0; i < n; i++) {
                    if (dist[i] == Long.MAX_VALUE) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(dist[i] + " ");
                    }
                }
                System.out.println();
            }
        }
        sc.close();
    }
}