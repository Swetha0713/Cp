import sys
from collections import defaultdict

def get_subset_sums(arr):
    """
    Generates all possible subset sums for a list of integers.
    """
    sums = [0]
    for num in arr:
        # Create new sums by adding the current number to all existing sums
        current_len = len(sums)
        for i in range(current_len):
            sums.append(sums[i] + num)
    return sums

def solve():
    """
    Main function to handle test cases and input/output.
    """
    # Read all input from stdin at once
    input_data = sys.stdin.read().split()
    
    if not input_data:
        return

    iterator = iter(input_data)
    
    try:
        # Get number of test cases
        t_str = next(iterator, None)
        if not t_str: return
        T = int(t_str)
        
        for _ in range(T):
            # Get N and S
            N = int(next(iterator))
            S = int(next(iterator))
            
            # Get the array elements
            A = []
            for _ in range(N):
                A.append(int(next(iterator)))
            
            # --- Meet-in-the-Middle Algorithm ---
            
            # 1. Divide the array into two halves
            mid = N // 2
            left_part = A[:mid]
            right_part = A[mid:]
            
            # 2. Generate all subset sums for both halves
            left_sums = get_subset_sums(left_part)
            right_sums = get_subset_sums(right_part)
            
            # 3. Count frequencies of sums in the right half
            right_freq = defaultdict(int)
            for val in right_sums:
                right_freq[val] += 1
            
            # 4. Check combinations
            # For every sum 'u' in left_sums, we need 'v' in right_sums such that u + v = S
            # So, we look for v = S - u
            ans = 0
            for u in left_sums:
                target = S - u
                if target in right_freq:
                    ans += right_freq[target]
            
            print(ans)
            
    except StopIteration:
        pass

if __name__ == "__main__":
    solve()