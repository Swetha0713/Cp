import sys
import re

class SegmentTree:
    def __init__(self, arr):
        self.arr = arr
        self.n = len(arr)
        self.min_tree = [0] * (4 * self.n)
        self.max_tree = [0] * (4 * self.n)
        if self.n > 0:
            self.build(0, 0, self.n - 1)

    def build(self, node, start, end):
        if start == end:
            self.min_tree[node] = self.arr[start]
            self.max_tree[node] = self.arr[start]
            return

        mid = (start + end) // 2
        left_child = 2 * node + 1
        right_child = 2 * node + 2

        self.build(left_child, start, mid)
        self.build(right_child, mid + 1, end)

        self.min_tree[node] = min(self.min_tree[left_child], self.min_tree[right_child])
        self.max_tree[node] = max(self.max_tree[left_child], self.max_tree[right_child])

    def query_min(self, node, start, end, l, r):
        if r < start or end < l:
            return float('inf')
        if l <= start and end <= r:
            return self.min_tree[node]
            
        mid = (start + end) // 2
        left_min = self.query_min(2 * node + 1, start, mid, l, r)
        right_min = self.query_min(2 * node + 2, mid + 1, end, l, r)
        return min(left_min, right_min)

    def query_max(self, node, start, end, l, r):
        if r < start or end < l:
            return float('-inf')
        if l <= start and end <= r:
            return self.max_tree[node]
            
        mid = (start + end) // 2
        left_max = self.query_max(2 * node + 1, start, mid, l, r)
        right_max = self.query_max(2 * node + 2, mid + 1, end, l, r)
        return max(left_max, right_max)

    def update(self, node, start, end, idx, val):
        if start == end:
            self.arr[idx] = val
            self.min_tree[node] = val
            self.max_tree[node] = val
            return

        mid = (start + end) // 2
        left_child = 2 * node + 1
        right_child = 2 * node + 2

        if start <= idx <= mid:
            self.update(left_child, start, mid, idx, val)
        else:
            self.update(right_child, mid + 1, end, idx, val)

        self.min_tree[node] = min(self.min_tree[left_child], self.min_tree[right_child])
        self.max_tree[node] = max(self.max_tree[left_child], self.max_tree[right_child])

def solve():
    # Read all input
    input_data = open("input.txt").read()
    if not input_data.strip():
        return
        
    # Extract all words/numbers, ignoring punctuation like (, ) and ,
    tokens = re.findall(r'[a-zA-Z]+|\d+', input_data)
    
    if not tokens: return
    
    n = int(tokens[0])
    temperatures = [int(x) for x in tokens[1:n+1]]
    
    seg_tree = SegmentTree(temperatures)
    
    # Process queries
    idx = n + 1
    while idx < len(tokens):
        cmd = tokens[idx]
        if cmd == "RangeMin":
            l, r = int(tokens[idx+1]), int(tokens[idx+2])
            print(seg_tree.query_min(0, 0, n - 1, l, r))
            idx += 3
        elif cmd == "RangeMax":
            l, r = int(tokens[idx+1]), int(tokens[idx+2])
            print(seg_tree.query_max(0, 0, n - 1, l, r))
            idx += 3
        elif cmd == "Update":
            target_idx, val = int(tokens[idx+1]), int(tokens[idx+2])
            seg_tree.update(0, 0, n - 1, target_idx, val)
            idx += 3
        else:
            idx += 1 # Safety fallback

if __name__ == "__main__":
    solve()