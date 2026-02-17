 import java.util.*;

public class Ass_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Check if there is input to read
        if (sc.hasNextInt()) {
            int T = sc.nextInt(); // Number of test cases
            
            while (T-- > 0) {
                int N = sc.nextInt(); // Size of array
                int Q = sc.nextInt(); // Number of queries
                
                int[] products = new int[N];
                
                // Read the sorted product IDs
                for (int i = 0; i < N; i++) {
                    products[i] = sc.nextInt();
                }
                
                // Process each query
                while (Q-- > 0) {
                    int target = sc.nextInt();
                    
                    // Step 1: Find the first time the number appears
                    int firstIndex = findFirst(products, target);
                    
                    // If firstIndex is -1, the number is not in the list at all
                    if (firstIndex == -1) {
                        System.out.println(0);
                    } else {
                        // Step 2: Find the last time the number appears
                        int lastIndex = findLast(products, target);
                        
                        // Step 3: Calculate total count
                        int count = (lastIndex - firstIndex) + 1;
                        System.out.println(count);
                    }
                }
            }
        }
    }

    // Function to find the FIRST occurrence of target
    public static int findFirst(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int result = -1; // Store the potential answer here

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                result = mid;      // Found it! But is there one before it?
                end = mid - 1;     // Look to the LEFT
            } else if (target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    // Function to find the LAST occurrence of target
    public static int findLast(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                result = mid;      // Found it! But is there one after it?
                start = mid + 1;   // Look to the RIGHT
            } else if (target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }
}     

