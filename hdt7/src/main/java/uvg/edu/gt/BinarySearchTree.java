package uvg.edu.gt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BinarySearchTree {
    public TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(String key, String value) {
        root = insertRec(root, key.toLowerCase(), value.toLowerCase());
    }

    public TreeNode insertRec(TreeNode root, String key, String value) {
        if (root == null) {
            root = new TreeNode(key, value);
            return root;
        }

        if (key.compareTo(root.key) < 0)
            root.left = insertRec(root.left, key, value);
        else if (key.compareTo(root.key) > 0)
            root.right = insertRec(root.right, key, value);

        return root;
    }

    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.println("(" + root.key + ", " + root.value + ")");
            inorder(root.right);
        }
    }

    public String translate(String sentence) {
        StringBuilder translatedSentence = new StringBuilder();
        Pattern pattern = Pattern.compile("\\b(\\w+)\\b");
        Matcher matcher = pattern.matcher(sentence.toLowerCase());
    
        while (matcher.find()) {
            String word = matcher.group(1);
            String translation = translateRec(root, word);
            if (translation.startsWith("*") && translation.endsWith("*")) {
                translatedSentence.append("*").append(word).append("*").append(" ");
            } else {
                translatedSentence.append(translation).append(" ");
            }
        }
    
        return translatedSentence.toString();
    }    

    public String translateRec(TreeNode root, String word) {
        if (root == null)
            return "*" + word + "*";
    
        if (word.equalsIgnoreCase(root.key))
            return root.value;
    
        int compareResult = word.compareToIgnoreCase(root.key);
        if (compareResult < 0)
            return translateRec(root.left, word);
        else
            return translateRec(root.right, word);
    }
}