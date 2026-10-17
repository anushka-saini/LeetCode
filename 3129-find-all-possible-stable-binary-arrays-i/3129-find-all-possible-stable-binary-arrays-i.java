
class Solution {
    static final int MOD = 1_000_000_007;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero + 1][one + 1][2];

        for (int i = 0; i <= Math.min(zero, limit); i++) {
            dp[i][0][0] = 1;
        }

        for (int j = 0; j <= Math.min(one, limit); j++) {
            dp[0][j][1] = 1;
        }

        for (int i = 1; i <= zero; i++) {
            for (int j = 1; j <= one; j++) {
                long zerosEnding = (long) dp[i - 1][j][0] + dp[i - 1][j][1];
                long onesEnding = (long) dp[i][j - 1][0] + dp[i][j - 1][1];

                if (i - limit - 1 >= 0) {
                    zerosEnding -= (long) dp[i - limit - 1][j][1];
                }

                if (j - limit - 1 >= 0) {
                    onesEnding -= (long) dp[i][j - limit - 1][0];
                }

                dp[i][j][0] = (int) ((zerosEnding % MOD + MOD) % MOD);
                dp[i][j][1] = (int) ((onesEnding % MOD + MOD) % MOD);
            }
        }

        return (int) (((long) dp[zero][one][0] + dp[zero][one][1]) % MOD);
    }
}

