import java.util.*;

public class Ass_5_9 {

    // Disjoint Set Union (DSU) / Union-Find Data Structure
    static class DSU {
        int[] parent;

        // Initialize: Each user is their own parent (independent set)
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // FIND Operation: Returns the representative (root) of the set
        // Uses "Path Compression" optimization
        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            // Recursively find the root and point i directly to it (flatten the tree)
            parent[i] = find(parent[i]);
            return parent[i];
        }

        // UNION Operation: Merges two sets
        public void union(int i, int j) {
            int rootA = find(i);
            int rootB = find(j);

            if (rootA != rootB) {
                // Connect one root to the other
                parent[rootA] = rootB;
            }
        }

        // CONNECTED Operation: Checks if two users are in the same community
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Check if input exists to avoid crashes
        if (sc.hasNextInt()) {
            
            // 1. Read Number of Users (N)
            int N = sc.nextInt();
            
            // Initialize DSU with N users (0 to N-1)
            DSU dsu = new DSU(N);

            // 2. Read Number of Friendships (M)
            int M = sc.nextInt();

            // Process M friendships
            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                dsu.union(u, v);
            }

            // 3. Process Queries
            // The problem statement didn't specify the number of queries (Q),
            // so we read until there are no more integers (End of File).
            // If your input has a specific Q count, uncomment the next line:
            // int Q = sc.nextInt(); while(Q-- > 0) {
            
            while (sc.hasNextInt()) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                if (dsu.isConnected(u, v)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
        sc.close();
    }
}