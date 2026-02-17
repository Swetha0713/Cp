import java.util.*;

public class Ass_1
 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        if (sc.hasNextInt()) {
            int T = sc.nextInt(); // Number of test cases
            
            while (T-- > 0) {
                int N = sc.nextInt(); // Number of meetings
                
                // Create a 2D array to store Start and End times
                // meetings[i][0] = Start Time
                // meetings[i][1] = End Time
                int[][] meetings = new int[N][2];
                
                for (int i = 0; i < N; i++) {
                    meetings[i][0] = sc.nextInt();
                    meetings[i][1] = sc.nextInt();
                }
                
                // Sort the 2D array based on End Time (column 1)
                Arrays.sort(meetings, (a, b) -> Integer.compare(a[1], b[1]));
                
                int count = 1; // We always pick the first meeting
                int lastEndTime = meetings[0][1];
                
                // Iterate through the rest of the meetings
                for (int i = 1; i < N; i++) {
                    // If current meeting starts after (or when) previous ended
                    if (meetings[i][0] >= lastEndTime) {
                        count++;
                        lastEndTime = meetings[i][1]; // Update end time
                    }
                }
                
                System.out.println(count);
            }
        }
    }
}