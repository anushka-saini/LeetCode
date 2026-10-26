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
import java.util.*;

class Solution {
    List<Integer> values = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        inorder(root);

        List<Integer> result = new ArrayList<>();
        int maxCount = 0, count = 0, prev = Integer.MIN_VALUE;

        for (int val : values) {
            if (val == prev) {
                count++;
            } else {
                count = 1;
                prev = val;
            }

            if (count > maxCount) {
                maxCount = count;
                result.clear();
                result.add(val);
            } else if (count == maxCount) {
                result.add(val);
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);
        values.add(root.val);
        inorder(root.right);
    }
}