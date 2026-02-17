class TrieNode:
    """
    Represents a single node in the Trie.
    """
    def __init__(self):
        # Dictionary to store children nodes (Key: Character, Value: TrieNode)
        self.children = {}
        # Boolean to mark if this node completes a valid course code
        self.is_end_of_code = False

class Trie:
    """
    The main Trie class to manage course code operations.
    """
    def __init__(self):
        self.root = TrieNode()

    def insert(self, course_code):
        """
        Inserts a course code into the Trie.
        """
        current = self.root
        for char in course_code:
            if char not in current.children:
                current.children[char] = TrieNode()
            current = current.children[char]
        
        # Mark the end of the course code
        current.is_end_of_code = True

    def search(self, course_code):
        """
        Checks if a full course code exists.
        Returns: True if found, False otherwise.
        """
        current = self.root
        for char in course_code:
            if char not in current.children:
                return False
            current = current.children[char]
        
        # Must be the end of a stored code
        return current.is_end_of_code

    def starts_with(self, prefix):
        """
        Checks if any course code starts with the given prefix.
        Returns: True if exists, False otherwise.
        """
        current = self.root
        for char in prefix:
            if char not in current.children:
                return False
            current = current.children[char]
        
        # If we successfully traversed the prefix, it exists
        return True

# --- Driver Code (based on your Test Case) ---

def run_course_search_test():
    # 1. Initialize Trie
    course_trie = Trie()
    
    # 2. Input Course Codes
    courses = ["CS101", "CS102", "CS201", "EE101", "ME105"]
    print(f"--- Inserting Course Codes: {courses} ---")
    for code in courses:
        course_trie.insert(code)
    
    print("\n--- Performing Operations ---")

    # Operation 1: Search for course code "CS101"
    result_1 = course_trie.search("CS101")
    print(f"Course code 'CS101' found → {result_1}")

    # Operation 2: Search for course code "CS301"
    result_2 = course_trie.search("CS301")
    print(f"Course code 'CS301' not found → {result_2}")

    # Operation 3: Check prefix "CS"
    result_3 = course_trie.starts_with("CS")
    print(f"Prefix 'CS' exists → {result_3}")

    # Operation 4: Check prefix "EE"
    result_4 = course_trie.starts_with("EE")
    print(f"Prefix 'EE' exists → {result_4}")

# Execute the test
if __name__ == "__main__":
    run_course_search_test()