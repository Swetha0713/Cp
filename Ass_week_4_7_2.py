class FenwickTree:
    def __init__(self, size):
        # Initialize tree with 0s. 
        # size + 1 is used because Fenwick Tree uses 1-based indexing.
        self.tree = [0] * (size + 1)

    def update(self, i, delta):
        """
        Adds 'delta' to the element at index i.
        This updates the current node and its specific ancestors.
        """
        while i < len(self.tree):
            self.tree[i] += delta
            # Move to next node: Add the lowest set bit
            # Example: if i is 1 (001), next is 2 (010), then 4 (100)
            i += i & (-i)

    def query(self, i):
        """
        Returns the prefix sum (total) from index 1 to i.
        """
        s = 0
        while i > 0:
            s += self.tree[i]
            # Move to parent: Subtract the lowest set bit
            # Example: if i is 7 (111), parent is 6 (110), then 4 (100)
            i -= i & (-i)
        return s

def solve_course_watch_time():
    print("--- Online Course Watch-Time Analysis ---")
    
    # --- Input Specification from Problem ---
    # Number of days: 5
    # Daily watch time: [30, 40, 20, 50, 10]
    days = 5
    watch_times = [30, 40, 20, 50, 10]
    
    print(f"Initial Data: {watch_times}")

    # 1. Initialize and Build the Fenwick Tree
    ft = FenwickTree(days)
    
    # We populate the tree by calling update() for every initial value.
    # Note: i is 0-based (0,1,2...), so we pass i+1 to make it 1-based for the Tree.
    for i, time in enumerate(watch_times):
        ft.update(i + 1, time)

    # --- Operation 1: Query watch time till Day 4 ---
    day_query = 4
    total_time_initial = ft.query(day_query)
    print(f"1. Total watch time till Day {day_query} = {total_time_initial}")
    
    # --- Operation 2: Update Day 2 watch time from 40 -> 55 ---
    day_to_update = 2
    new_value = 55
    old_value = watch_times[day_to_update - 1] # Accessing list at index 1
    
    # Crucial Step: Fenwick Trees support "Add", not "Set".
    # We must calculate the difference (delta).
    # Delta = New Value - Old Value = 55 - 40 = +15
    delta = new_value - old_value
    
    print(f"2. Updating Day {day_to_update} from {old_value} to {new_value} (Delta: {delta})")
    ft.update(day_to_update, delta)
    
    # Update our local list to keep it consistent
    watch_times[day_to_update - 1] = new_value

    # --- Operation 3: Query watch time till Day 4 again ---
    total_time_final = ft.query(day_query)
    print(f"3. After update, total watch time till Day {day_query} = {total_time_final}")

# Run the solution
if __name__ == "__main__":
    solve_course_watch_time()