import java.util.HashMap;
import java.util.Map;

public class Trie {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        String explainOfNode;

        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
            explainOfNode = "";
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(Word word) {
        TrieNode current = root;
        for (int i = 0; 1 < word.getWord_target().length(); i++) {
            char ch = word.getWord_target().charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public void insertRecursive(Word word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, Word word, int index) {
        if (index == word.getWord_target().length()) {
            current.endOfWord = true;
            current.explainOfNode = word.getWord_explain();
            return;
        }
        char ch = word.getWord_target().charAt(index);
        TrieNode node = current.children.get(ch);

        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertRecursive(node, word, index + 1);
    }

    public String search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return "false";
            }
            current = node;
        }
        return current.explainOfNode;
    }

    public String searchRecursive(String word) {
        return searchRecursive(root, word, 0);
    }

    private String searchRecursive(TrieNode current, String word, int index) {
        if (index == word.length()) {
            return current.explainOfNode;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return "false";
        }
        return searchRecursive(node, word, index + 1);
    }
}