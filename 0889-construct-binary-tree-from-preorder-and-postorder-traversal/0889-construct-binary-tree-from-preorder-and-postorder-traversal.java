/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.HashMap;
import java.util.Map;

class Solution {
    private int preIndex = 0;
    private Map<Integer, Integer> postorderMap = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            postorderMap.put(postorder[i], i);
        }

        return build(preorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int postStart, int postEnd) {
        if (postStart > postEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex++]);

        if (postStart == postEnd) {
            return root;
        }

        int leftRootVal = preorder[preIndex];
        int leftRootPostIndex = postorderMap.get(leftRootVal);

        root.left = build(preorder, postStart, leftRootPostIndex);
        root.right = build(preorder, leftRootPostIndex + 1, postEnd - 1);

        return root;
    }
}