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
    private Map<Integer, TreeNode> rootMap = new HashMap<>();

    public TreeNode canMerge(List<TreeNode> trees) {
        Set<Integer> leaves = new HashSet<>();

        for (TreeNode tree : trees) {
            rootMap.put(tree.val, tree);
            if (tree.left != null) {
                leaves.add(tree.left.val);
            }
            if (tree.right != null) {
                leaves.add(tree.right.val);
            }
        }

        TreeNode mainRoot = null;
        for (TreeNode tree : trees) {
            if (!leaves.contains(tree.val)) {
                if (mainRoot != null) {
                    return null;
                }
                mainRoot = tree;
            }
        }

        if (mainRoot == null) {
            return null;
        }

        rootMap.remove(mainRoot.val);

        if (!buildAndValidate(mainRoot, Long.MIN_VALUE, Long.MAX_VALUE)) {
            return null;
        }

        return rootMap.isEmpty() ? mainRoot : null;
    }

    private boolean buildAndValidate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }

        if (node.val <= min || node.val >= max) {
            return false;
        }

        if (node.left == null && node.right == null && rootMap.containsKey(node.val)) {
            TreeNode subTree = rootMap.remove(node.val);
            node.left = subTree.left;
            node.right = subTree.right;
        }

        return buildAndValidate(node.left, min, node.val) && 
               buildAndValidate(node.right, node.val, max);
    }
}