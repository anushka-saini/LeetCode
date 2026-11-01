class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];

        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int end = start + len - 1;
                int minCost = Integer.MAX_VALUE;

                for (int x = start; x <= end; x++) {
                    int leftCost = (x > start) ? dp[start][x - 1] : 0;
                    int rightCost = (x < end) ? dp[x + 1][end] : 0;
                    
                    int cost = x + Math.max(leftCost, rightCost);
                    minCost = Math.min(minCost, cost);
                }

                dp[start][end] = minCost;
            }
        }

        return dp[1][n];
    }
}