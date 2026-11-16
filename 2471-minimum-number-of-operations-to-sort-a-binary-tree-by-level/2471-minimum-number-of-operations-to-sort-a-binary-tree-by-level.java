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
class Solution {
    public int minimumOperations(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int totalSwaps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int[] level = new int[size];

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                level[i] = curr.val;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }

            totalSwaps += minSwapsToSort(level);
        }

        return totalSwaps;
    }

    private int minSwapsToSort(int[] arr) {
        int n = arr.length;
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }

        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != sorted[i]) {
                swaps++;

                int originalVal = arr[i];
                int targetIndex = indexMap.get(sorted[i]);

                arr[i] = sorted[i];
                arr[targetIndex] = originalVal;

                indexMap.put(originalVal, targetIndex);
                indexMap.put(sorted[i], i);
            }
        }

        return swaps;
    }
}