class FenwickTree:
    def __init__(self, size):
        # 1-based indexing for BIT, so size + 1
        self.tree = [0] * (size + 1)

    def update(self, i, delta):
        """Adds delta to element at index i."""
        while i < len(self.tree):
            self.tree[i] += delta
            # Move to next node: i + (i & -i) extracts the lowest set bit
            i += i & (-i)

    def query(self, i):
        """Returns the prefix sum from index 1 to i."""
        s = 0
        while i > 0:
            s += self.tree[i]
            # Move to parent: i - (i & -i) removes the lowest set bit
            i -= i & (-i)
        return s

def solve_rainfall_problem():
    print("--- Rainfall Measurement Problem ---")
    
    # Input Data
    daily_rainfall = [5, 12, 7, 10, 6, 8]
    n = len(daily_rainfall)
    
    # Initialize Fenwick Tree
    ft = FenwickTree(n)
    
    # Build the tree (Day 1 is index 1, so we map i -> i+1)
    for i, rain in enumerate(daily_rainfall):
        ft.update(i + 1, rain)

    # Operation 1: Find total rainfall till Day 4
    total_day_4 = ft.query(4)
    print(f"Total rainfall till Day 4: {total_day_4} mm")

    # Operation 2: Update Day 3 rainfall from 7 -> 9
    # Delta = New Value - Old Value = 9 - 7 = 2
    day_to_update = 3
    new_value = 9
    old_value = daily_rainfall[day_to_update - 1] # -1 because list is 0-indexed
    change = new_value - old_value
    
    print(f"Updating Day {day_to_update} from {old_value} to {new_value}...")
    ft.update(day_to_update, change)
    
    # Update the local list to keep track of current values
    daily_rainfall[day_to_update - 1] = new_value

    # Operation 3: Find total rainfall till Day 4 again
    total_day_4_after = ft.query(4)
    print(f"After update, total rainfall till Day 4: {total_day_4_after} mm")

# Run the solution
solve_rainfall_problem()