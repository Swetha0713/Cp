import sys

def solve():
    # We use sys.stdin.read() to read all input at once because 
    # reading line-by-line with N=200,000 can be too slow in Python.
    input_data = sys.stdin.read().split()
    
    if not input_data:
        return

    # This creates an iterator to walk through our input data one by one
    iterator = iter(input_data)
    
    try:
        # Read T (Number of test cases)
        T_str = next(iterator, None)
        if not T_str: return
        T = int(T_str)
        
        for _ in range(T):
            # Read N (Number of meetings)
            N = int(next(iterator))
            meetings = []
            
            # Read the Start and End times
            for _ in range(N):
                s = int(next(iterator))
                e = int(next(iterator))
                meetings.append([s, e])
            
            # --- THE SORTING STEP ---
            # Sort by End Time (index 1)
            # key=lambda x: x[1] is the same as the Java Comparator
            meetings.sort(key=lambda x: x[1])
            
            # --- GREEDY LOOP ---
            count = 1
            last_end_time = meetings[0][1]
            
            for i in range(1, N):
                # Check if next meeting starts after (or when) previous ended
                if meetings[i][0] >= last_end_time:
                    count += 1
                    last_end_time = meetings[i][1]
            
            print(count)
            
    except StopIteration:
        pass

if __name__ == "__main__":
    solve()