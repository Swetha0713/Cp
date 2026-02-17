import java.util.*;

public class Ass_3_6 {

    // Helper function to generate all subset sums for a part of the array
    private static ArrayList<Long> getSubsetSums(long[] arr) {
        ArrayList<Long> sums = new ArrayList<>();
        sums.add(0L); // Start with an empty subset sum of 0

        for (long num : arr) {
            int currentSize = sums.size();
            // For every existing sum, add the current number to create new sums
            for (int i = 0; i < currentSize; i++) {
                sums.add(sums.get(i) + num);
            }
        }
        return sums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Check if input exists to avoid crashes
        if (!sc.hasNext()) {
            sc.close();
            return;
        }

        int T = sc.nextInt(); // Number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();
            long S = sc.nextLong(); // Target sum

            long[] A = new long[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextLong();
            }

            // --- Meet-in-the-Middle Logic ---

            // 1. Split array into two halves
            int mid = N / 2;
            long[] leftPart = Arrays.copyOfRange(A, 0, mid);
            long[] rightPart = Arrays.copyOfRange(A, mid, N);

            // 2. Generate all subset sums for both halves
            ArrayList<Long> leftSums = getSubsetSums(leftPart);
            ArrayList<Long> rightSums = getSubsetSums(rightPart);

            // 3. Sort the right half sums to enable Binary Search
            Collections.sort(rightSums);

            // 4. Find the closest sum
            long minDiff = Long.MAX_VALUE;

            for (long u : leftSums) {
                long target = S - u;

                // Binary search for 'target' in rightSums
                int idx = Collections.binarySearch(rightSums, target);

                if (idx >= 0) {
                    // Exact match found! Difference is 0.
                    minDiff = 0;
                    break;
                } else {
                    // Not found, convert return value to insertion point
                    int insertionPoint = -(idx + 1);

                    // Check the element at insertionPoint (value > target)
                    if (insertionPoint < rightSums.size()) {
                        long diff = Math.abs((u + rightSums.get(insertionPoint)) - S);
                        if (diff < minDiff) minDiff = diff;
                    }

                    // Check the element before insertionPoint (value < target)
                    if (insertionPoint > 0) {
                        long diff = Math.abs((u + rightSums.get(insertionPoint - 1)) - S);
                        if (diff < minDiff) minDiff = diff;
                    }
                }
            }

            System.out.println(minDiff);
        }
        
        sc.close();
    }
}