import java.util.HashMap;
import java.util.Map;

// Helper Class: Represents a node in the Trie
class ContactTrieNode {
    Map<Character, ContactTrieNode> children;
    boolean isEndOfWord;

    public ContactTrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}

// Helper Class: The Trie data structure
class ContactTrie {
    private final ContactTrieNode root;

    public ContactTrie() {
        root = new ContactTrieNode();
    }

    // Insert a contact name
    public void insert(String word) {
        ContactTrieNode current = root;
        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new ContactTrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfWord = true;
    }

    // Search for a complete contact name
    public boolean search(String word) {
        ContactTrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isEndOfWord;
    }

    // Check if any contact starts with a given prefix
    public boolean startsWith(String prefix) {
        ContactTrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return true;
    }
}

// Main Class (Must match filename Ass_4_8_1.java)
public class Ass_4_8_1 {
    public static void main(String[] args) {
        ContactTrie contactTrie = new ContactTrie();

        // 1. Input Contacts
        String[] contacts = {"Anil", "Anitha", "Anirudh", "Bala", "Balaji"};
        System.out.println("--- Inserting Contacts ---");
        for (String name : contacts) {
            contactTrie.insert(name);
        }

        System.out.println("\n--- Performing Operations ---");

        // Operation 1: Search for contact "Anil"
        boolean result1 = contactTrie.search("Anil");
        System.out.println("Contact 'Anil' found → " + result1);

        // Operation 2: Search for contact "Anand"
        boolean result2 = contactTrie.search("Anand");
        System.out.println("Contact 'Anand' not found → " + result2);

        // Operation 3: Check prefix "Ani"
        boolean result3 = contactTrie.startsWith("Ani");
        System.out.println("Prefix 'Ani' exists → " + result3);

        // Operation 4: Check prefix "Bal"
        boolean result4 = contactTrie.startsWith("Bal");
        System.out.println("Prefix 'Bal' exists → " + result4);
    }
}