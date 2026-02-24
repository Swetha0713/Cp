import java.util.*;

public class Ass_5_10 {

    static class SegmentTree {
        int[] minTree;
        int[] maxTree;
        int[] arr;
        int n;

        public SegmentTree(int[] inputArr) {
            this.arr = inputArr;
            this.n = arr.length;
            // The maximum size of a segment tree array is 4 * N
            minTree = new int[4 * n];
            maxTree = new int[4 * n];
            build(0, 0, n - 1);
        }

        // 1. Build the Segment Tree
        private void build(int node, int start, int end) {
            if (start == end) {
                minTree[node] = arr[start];
                maxTree[node] = arr[start];
                return;
            }

            int mid = start + (end - start) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            build(leftChild, start, mid);
            build(rightChild, mid + 1, end);

            minTree[node] = Math.min(minTree[leftChild], minTree[rightChild]);
            maxTree[node] = Math.max(maxTree[leftChild], maxTree[rightChild]);
        }

        // 2. Perform Range Minimum Query
        public int queryMin(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return Integer.MAX_VALUE; // Return infinity so it doesn't affect min
            }
            if (l <= start && end <= r) {
                return minTree[node];
            }
            
            int mid = start + (end - start) / 2;
            int leftMin = queryMin(2 * node + 1, start, mid, l, r);
            int rightMin = queryMin(2 * node + 2, mid + 1, end, l, r);
            
            return Math.min(leftMin, rightMin);
        }

        // 3. Perform Range Maximum Query
        public int queryMax(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return Integer.MIN_VALUE; // Return -infinity so it doesn't affect max
            }
            if (l <= start && end <= r) {
                return maxTree[node];
            }
            
            int mid = start + (end - start) / 2;
            int leftMax = queryMax(2 * node + 1, start, mid, l, r);
            int rightMax = queryMax(2 * node + 2, mid + 1, end, l, r);
            
            return Math.max(leftMax, rightMax);
        }

        // 4. Update a sensor reading dynamically
        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                arr[idx] = val;
                minTree[node] = val;
                maxTree[node] = val;
                return;
            }

            int mid = start + (end - start) / 2;
            int leftChild = 2 * node + 1;
            int rightChild = 2 * node + 2;

            if (start <= idx && idx <= mid) {
                update(leftChild, start, mid, idx, val); 
            } else {
                update(rightChild, mid + 1, end, idx, val); 
            }

            minTree[node] = Math.min(minTree[leftChild], minTree[rightChild]);
            maxTree[node] = Math.max(maxTree[leftChild], maxTree[rightChild]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int[] temperatures = new int[n];

            for (int i = 0; i < n; i++) {
                temperatures[i] = sc.nextInt();
            }

            SegmentTree segTree = new SegmentTree(temperatures);

            // Read queries until there is no more input
            while (sc.hasNext()) {
                // Read the query type string (e.g., "RangeMin(2,6)" or just "RangeMin" depending on formatting)
                // For simplicity, let's assume the input is spaced out like: RangeMin 2 6
                String rawInput = sc.next(); 
                
                // If your input literally has brackets like "RangeMin(2,6)", we clean it:
                if (rawInput.contains("(")) {
                    String clean = rawInput.replace("(", " ").replace(")", " ").replace(",", " ");
                    Scanner stringReader = new Scanner(clean);
                    String type = stringReader.next();
                    
                    if (type.equals("RangeMin")) {
                        System.out.println(segTree.queryMin(0, 0, n - 1, stringReader.nextInt(), stringReader.nextInt()));
                    } else if (type.equals("RangeMax")) {
                        System.out.println(segTree.queryMax(0, 0, n - 1, stringReader.nextInt(), stringReader.nextInt()));
                    } else if (type.equals("Update")) {
                        segTree.update(0, 0, n - 1, stringReader.nextInt(), stringReader.nextInt());
                    }
                    stringReader.close();
                } else {
                    // Standard space-separated input format
                    String type = rawInput;
                    if (type.equals("RangeMin")) {
                        System.out.println(segTree.queryMin(0, 0, n - 1, sc.nextInt(), sc.nextInt()));
                    } else if (type.equals("RangeMax")) {
                        System.out.println(segTree.queryMax(0, 0, n - 1, sc.nextInt(), sc.nextInt()));
                    } else if (type.equals("Update")) {
                        segTree.update(0, 0, n - 1, sc.nextInt(), sc.nextInt());
                    }
                }
            }
        }
        sc.close();
    }
}