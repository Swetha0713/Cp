import sys

def solve():
    try:
        # Read directly from input.txt for a smooth workflow
        input_data = open("input.txt").read().split()
    except FileNotFoundError:
        # Fallback to standard input if the file is missing
        input_data = sys.stdin.read().split()

    if not input_data:
        return

    n = int(input_data[0])
    m = int(input_data[1])

    edges = []
    idx = 2
    
    # Read all edges
    for _ in range(m):
        u = int(input_data[idx])
        v = int(input_data[idx+1])
        w = int(input_data[idx+2])
        edges.append((u, v, w))
        idx += 3

    s = int(input_data[idx]) # Source node

    # Initialize distances to infinity
    dist = [float('inf')] * n
    dist[s] = 0

    # Step 1: Relax edges N-1 times
    for _ in range(n - 1):
        for u, v, w in edges:
            if dist[u] != float('inf') and dist[u] + w < dist[v]:
                dist[v] = dist[u] + w

    # Step 2: Check for negative cycles
    has_negative_cycle = False
    for u, v, w in edges:
        if dist[u] != float('inf') and dist[u] + w < dist[v]:
            has_negative_cycle = True
            break

    # Output results
    if has_negative_cycle:
        print("NEGATIVE CYCLE")
    else:
        result = []
        for i in range(n):
            if dist[i] == float('inf'):
                result.append("INF")
            else:
                result.append(str(dist[i]))
        print(" ".join(result))

if __name__ == "__main__":
    solve()