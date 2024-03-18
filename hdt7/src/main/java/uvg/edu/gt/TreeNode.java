package uvg.edu.gt;

public class TreeNode {
    String key;
    String value;
    TreeNode left, right;

    public TreeNode(String key, String value) {
        this.key = key;
        this.value = value;
        left = right = null;
    }
}