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
    private static class NodeInfo implements Comparable<NodeInfo> {
        int row;
        int val;

        NodeInfo(int row, int val) {
            this.row = row;
            this.val = val;
        }

        @Override
        public int compareTo(NodeInfo other) {
            if (this.row != other.row) {
                return Integer.compare(this.row, other.row);
            }
            return Integer.compare(this.val, other.val);
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, List<NodeInfo>> columnMap = new TreeMap<>();
        dfs(root, 0, 0, columnMap);

        List<List<Integer>> result = new ArrayList<>();
        for (List<NodeInfo> nodes : columnMap.values()) {
            Collections.sort(nodes);
            List<Integer> columnValues = new ArrayList<>();
            for (NodeInfo info : nodes) {
                columnValues.add(info.val);
            }
            result.add(columnValues);
        }

        return result;
    }

    private void dfs(TreeNode node, int row, int col, TreeMap<Integer, List<NodeInfo>> columnMap) {
        if (node == null) return;

        columnMap.computeIfAbsent(col, k -> new ArrayList<>()).add(new NodeInfo(row, node.val));

        dfs(node.left, row + 1, col - 1, columnMap);
        dfs(node.right, row + 1, col + 1, columnMap);
    }
}