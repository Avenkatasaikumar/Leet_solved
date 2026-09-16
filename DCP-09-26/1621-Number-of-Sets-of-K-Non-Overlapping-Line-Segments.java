class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1000000007;
        int totalItems = n + k - 1;
        int r = 2 * k;
        
        if (r > totalItems || r < 0) return 0;
        long[][] dp = new long[totalItems + 1][r + 1];
        
        for (int i = 0; i <= totalItems; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(i, r); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        
        return (int) dp[totalItems][r];
    }
}