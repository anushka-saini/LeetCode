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
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int firstIndex = queue.peek().index;
            int lastIndex = firstIndex;

            for (int i = 0; i < size; i++) {
                Pair curr = queue.poll();
                TreeNode node = curr.node;
                int index = curr.index - firstIndex;
                lastIndex = curr.index;

                if (node.left != null) {
                    queue.offer(new Pair(node.left, 2 * index + 1));
                }
                if (node.right != null) {
                    queue.offer(new Pair(node.right, 2 * index + 2));
                }
            }

            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }

        return maxWidth;
    }
}