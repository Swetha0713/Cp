import sys

# Increase recursion depth just in case the graph is huge
sys.setrecursionlimit(200000)

def dfs(node, adj, visited):
    visited[node] = True
    
    # Visit all connected neighbors
    for neighbor in adj[node]:
        if not visited[neighbor]:
            dfs(neighbor, adj, visited)

def solve():
    try:
        # Read directly from input.txt 
        input_data = open("input.txt").read().split()
    except FileNotFoundError:
        print("Please create input.txt with your input data.")
        return

    if not input_data:
        return
        
    n = int(input_data[0])
    m = int(input_data[1])
    
    # 1. Build the Graph (Adjacency List)
    adj = [[] for _ in range(n)]
    
    idx = 2
    for _ in range(m):
        u = int(input_data[idx])
        v = int(input_data[idx+1])
        adj[u].append(v)
        adj[v].append(u)
        idx += 2
        
    # 2. Track visited nodes
    visited = [False] * n
    components = 0
    
    # 3. Count Components using DFS
    for i in range(n):
        if not visited[i]:
            components += 1
            dfs(i, adj, visited)
            
    print(components)

if __name__ == "__main__":
    solve()