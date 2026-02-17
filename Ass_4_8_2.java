 import java.util.HashMap;
import java.util.Map;

// Helper Class: Represents a node in the Trie
class CourseTrieNode {
    Map<Character, CourseTrieNode> children;
    boolean isEndOfCode;

    public CourseTrieNode() {
        children = new HashMap<>();
        isEndOfCode = false;
    }
}

// Helper Class: The Trie data structure
class CourseTrie {
    private final CourseTrieNode root;

    public CourseTrie() {
        root = new CourseTrieNode();
    }

    // Insert a course code
    public void insert(String courseCode) {
        CourseTrieNode current = root;
        for (char ch : courseCode.toCharArray()) {
            current.children.putIfAbsent(ch, new CourseTrieNode());
            current = current.children.get(ch);
        }
        current.isEndOfCode = true;
    }

    // Search for a complete course code
    public boolean search(String courseCode) {
        CourseTrieNode current = root;
        for (char ch : courseCode.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isEndOfCode;
    }

    // Check if any course code starts with a given prefix
    public boolean startsWith(String prefix) {
        CourseTrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return true;
    }
}

// Main Class (Must match filename Ass_4_8_2.java)
public class Ass_4_8_2 {
    public static void main(String[] args) {
        CourseTrie courseTrie = new CourseTrie();

        // 1. Input Course Codes
        String[] courses = {"CS101", "CS102", "CS201", "EE101", "ME105"};
        System.out.println("--- Inserting Course Codes ---");
        for (String code : courses) {
            courseTrie.insert(code);
        }

        System.out.println("\n--- Performing Operations ---");

        // Operation 1: Search for course code "CS101"
        boolean result1 = courseTrie.search("CS101");
        System.out.println("Course code 'CS101' found → " + result1);

        // Operation 2: Search for course code "CS301"
        boolean result2 = courseTrie.search("CS301");
        System.out.println("Course code 'CS301' not found → " + result2);

        // Operation 3: Check prefix "CS"
        boolean result3 = courseTrie.startsWith("CS");
        System.out.println("Prefix 'CS' exists → " + result3);

        // Operation 4: Check prefix "EE"
        boolean result4 = courseTrie.startsWith("EE");
        System.out.println("Prefix 'EE' exists → " + result4);
    }
} 
    

