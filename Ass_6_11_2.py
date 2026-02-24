import sys
from collections import deque

sys.setrecursionlimit(200000)

def bfs(start, target, n, adj):
    dist = [-1] * n
    queue = deque([start])
    dist[start] = 0
    
    while queue:
        curr = queue.popleft()
        
        # If we reach our destination, return the distance
        if curr == target:
            return dist[curr]
            
        for neighbor in adj[curr]:
            if dist[neighbor] == -1: # If unvisited
                dist[neighbor] = dist[curr] + 1
                queue.append(neighbor)
                
    return -1 # Path not found

def dfs(node, parent, visited, adj):
    visited[node] = True
    
    for neighbor in adj[node]:
        if not visited[neighbor]:
            if dfs(neighbor, node, visited, adj):
                return True
        # If visited and not the parent we just came from, it's a circle!
        elif neighbor != parent:
            return True
            
    return False

def solve():
    try:
        # Read straight from input.txt
        input_data = open("input.txt").read().split()
    except FileNotFoundError:
        print("Please create input.txt with your input data.")
        return

    if not input_data:
        return
        
    n = int(input_data[0])
    m = int(input_data[1])
    
    adj = [[] for _ in range(n)]
    
    idx = 2
    for _ in range(m):
        u = int(input_data[idx])
        v = int(input_data[idx+1])
        adj[u].append(v)
        adj[v].append(u)
        idx += 2
        
    s = int(input_data[idx])
    d = int(input_data[idx+1])
    
    # 1. Shortest path using BFS
    shortest_path = bfs(s, d, n, adj)
    print(shortest_path)
    
    # 2. Cycle detection using DFS
    visited = [False] * n
    has_cycle = False
    
    for i in range(n):
        if not visited[i]:
            if dfs(i, -1, visited, adj):
                has_cycle = True
                break
                
    if has_cycle:
        print("YES")
    else:
        print("NO")

if __name__ == "__main__":
    solve()