
import java.util.*;

public class Ass_3_5 {

    // Helper function to generate all subset sums for a given array part
    private static ArrayList<Long> getSubsetSums(long[] arr) {
        ArrayList<Long> sums = new ArrayList<>();
        sums.add(0L); // Start with sum 0 (empty subset)

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
        // Use Scanner for reading input
        Scanner sc = new Scanner(System.in);

        // Check if there is input available
        if (!sc.hasNext()) {
            return;
        }

        try {
            int T = sc.nextInt(); // Number of test cases

            while (T-- > 0) {
                int N = sc.nextInt();
                long S = sc.nextLong(); // Target sum can be large

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

                // 3. Store right_sums frequency in a HashMap for O(1) lookup
                HashMap<Long, Integer> rightFreq = new HashMap<>();
                for (long val : rightSums) {
                    rightFreq.put(val, rightFreq.getOrDefault(val, 0) + 1);
                }

                // 4. Iterate through left_sums and check for complement in right_sums
                long count = 0;
                for (long u : leftSums) {
                    long target = S - u;
                    if (rightFreq.containsKey(target)) {
                        count += rightFreq.get(target);
                    }
                }

                System.out.println(count);
            }
        } catch (NoSuchElementException e) {
            // Handle cases where input might stop abruptly
        } finally {
            sc.close();
        }
    }
}
