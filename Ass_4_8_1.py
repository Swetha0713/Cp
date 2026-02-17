class TrieNode:
    """
    Represents a single node in the Trie.
    """
    def __init__(self):
        # Dictionary to store children nodes (Key: Char, Value: TrieNode)
        self.children = {}
        # Boolean to mark if this node represents the end of a complete name
        self.is_end_of_word = False

class Trie:
    """
    The main Trie class to manage contact operations.
    """
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        """
        Inserts a contact name into the Trie.
        """
        current = self.root
        for char in word:
            # If the character doesn't exist, create a new node
            if char not in current.children:
                current.children[char] = TrieNode()
            # Move to the next node
            current = current.children[char]
        
        # Mark the end of the word
        current.is_end_of_word = True

    def search(self, word):
        """
        Checks if a full contact name exists.
        Returns: True if found, False otherwise.
        """
        current = self.root
        for char in word:
            if char not in current.children:
                return False
            current = current.children[char]
        
        # It must be the end of a stored word, not just a prefix
        return current.is_end_of_word

    def starts_with(self, prefix):
        """
        Checks if any contact starts with the given prefix.
        Returns: True if exists, False otherwise.
        """
        current = self.root
        for char in prefix:
            if char not in current.children:
                return False
            current = current.children[char]
        
        # If we successfully traversed the prefix, return True
        return True

# --- Driver Code (based on your Test Case) ---

def run_mobile_contact_test():
    # 1. Initialize Trie
    contact_trie = Trie()
    
    # 2. Input Contacts
    contacts = ["Anil", "Anitha", "Anirudh", "Bala", "Balaji"]
    print(f"--- Inserting Contacts: {contacts} ---")
    for name in contacts:
        contact_trie.insert(name)
    
    print("\n--- Performing Operations ---")

    # Operation 1: Search for contact "Anil"
    result_1 = contact_trie.search("Anil")
    print(f"Contact 'Anil' found → {result_1}")

    # Operation 2: Search for contact "Anand"
    result_2 = contact_trie.search("Anand")
    print(f"Contact 'Anand' not found → {result_2}")

    # Operation 3: Check prefix "Ani"
    result_3 = contact_trie.starts_with("Ani")
    print(f"Prefix 'Ani' exists → {result_3}")

    # Operation 4: Check prefix "Bal"
    result_4 = contact_trie.starts_with("Bal")
    print(f"Prefix 'Bal' exists → {result_4}")

# Execute the test
if __name__ == "__main__":
    run_mobile_contact_test()