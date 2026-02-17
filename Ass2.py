import sys

def find_first_occurrence(arr, target):
    low = 0
    high = len(arr) - 1
    first_occurrence = -1
    while low <= high:
        mid = low + (high - low) // 2
        if arr[mid] == target:
            first_occurrence = mid
            high = mid - 1
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return first_occurrence

def find_last_occurrence(arr, target):
    low = 0
    high = len(arr) - 1
    last_occurrence = -1
    while low <= high:
        mid = low + (high - low) // 2
        if arr[mid] == target:
            last_occurrence = mid
            low = mid + 1
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return last_occurrence

def solve():
    # Prompt for N and Q
    line1 = input("\nEnter number of products (N) and number of queries (Q) separated by space: ").split()
    if not line1: return
    N, Q = map(int, line1)

    # Prompt for the list
    print(f"Enter {N} product IDs (sorted by size, e.g., 10 20 20 30):")
    product_ids = list(map(int, input().split()))

    for i in range(Q):
        query_id = int(input(f" Query {i+1}: Enter Product ID to search: "))
        first = find_first_occurrence(product_ids, query_id)
        
        if first == -1:
            print(f" Result: Product ID {query_id} found 0 times.")
        else:
            last = find_last_occurrence(product_ids, query_id)
            count = last - first + 1
            print(f" Result: Product ID {query_id} found {count} times.")

# Start of the program
try:
    T_str = input("Enter the number of test cases (T): ")
    T = int(T_str)
    for i in range(T):
        print(f"\n--- Test Case {i+1} ---")
        solve()
except ValueError:
    print("Please enter valid integers.")