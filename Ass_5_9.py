class DSU:
    def __init__(self, n):
        # Initialize parent of i as i itself
        self.parent = list(range(n))

    def find(self, i):
        # Path Compression: Point directly to the root
        if self.parent[i] != i:
            self.parent[i] = self.find(self.parent[i])
        return self.parent[i]

    def union(self, i, j):
        root_a = self.find(i)
        root_b = self.find(j)
        if root_a != root_b:
            self.parent[root_a] = root_b

    def is_connected(self, i, j):
        return self.find(i) == self.find(j)

# --- Main Execution ---
def solve():
    number_of_users = 7
    dsu = DSU(number_of_users)

    # Friendships (0-indexed)
    friendships = [
        (0, 1),
        (1, 3),
        (2, 4),
        (5, 6),
        (3, 4)
    ]

    for u, v in friendships:
        dsu.union(u, v)

    # Queries
    queries = [
        (0, 4),
        (2, 6),
        (5, 6)
    ]

    print("Query Results:")
    for u, v in queries:
        if dsu.is_connected(u, v):
            print("YES")
        else:
            print("NO")

if __name__ == "__main__":
    solve()