import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        String explainOfNode;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
            explainOfNode = "";
        }
    }

    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insertRecursive(Word word) {
        insertRecursive(root, word, 0);
    }

    private void insertRecursive(TrieNode current, Word word, int index) {
        if (index == word.getWord_target().length()) {
            current.endOfWord = true;
            current.explainOfNode += ", " + word.getWord_explain();
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
            return "";
        }
        return searchRecursive(node, word, index + 1);
    }

    public void delete(String word) {
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            current.explainOfNode = "";
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }
        return false;
    }
}