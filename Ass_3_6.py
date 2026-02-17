import sys
from bisect import bisect_left

def get_subset_sums(arr):
    """
    Generates all possible subset sums for a list of integers.
    """
    sums = [0]
    for num in arr:
        # Append (current_sum + num) to the list
        # We use a fixed range to avoid infinite loops while modifying the list
        current_len = len(sums)
        for i in range(current_len):
            sums.append(sums[i] + num)
    return sums

def solve():
    # Read all input from standard input
    input_data = sys.stdin.read().split()
    
    if not input_data:
        return

    iterator = iter(input_data)
    
    try:
        t_str = next(iterator, None)
        if not t_str: return
        T = int(t_str)
        
        for _ in range(T):
            # Read N and Target S
            N = int(next(iterator))
            S = int(next(iterator))
            
            A = []
            for _ in range(N):
                A.append(int(next(iterator)))
            
            # --- Meet-in-the-Middle Logic ---
            
            # 1. Split array into two halves
            mid = N // 2
            left_part = A[:mid]
            right_part = A[mid:]
            
            # 2. Generate all subset sums for both halves
            left_sums = get_subset_sums(left_part)
            right_sums = get_subset_sums(right_part)
            
            # 3. Sort the right half sums to enable Binary Search
            right_sums.sort()
            
            # 4. Find the closest sum
            min_diff = float('inf')
            
            # For each sum 'u' in the left half, we want a 'v' in the right half
            # such that u + v is closest to S.
            # Ideally, v should be close to (S - u).
            
            right_len = len(right_sums)
            
            for u in left_sums:
                target = S - u
                
                # Find the insertion point where 'target' fits in sorted right_sums
                idx = bisect_left(right_sums, target)
                
                # Check the value at the insertion index (if within bounds)
                # This value is >= target
                if idx < right_len:
                    diff = abs((u + right_sums[idx]) - S)
                    if diff < min_diff:
                        min_diff = diff
                
                # Check the value immediately before the insertion index (if within bounds)
                # This value is < target
                if idx > 0:
                    diff = abs((u + right_sums[idx - 1]) - S)
                    if diff < min_diff:
                        min_diff = diff
                
                # Optimization: If we found an exact match (diff is 0), we can't do better
                if min_diff == 0:
                    break
            
            print(min_diff)

    except StopIteration:
        pass

if __name__ == "__main__":
    solve()