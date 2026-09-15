class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2 || isPal[i + 1][j - 1]) {
                        isPal[i][j] = true;
                    }
                }
            }
        }
        
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i]; 
            for (int j = 0; j <= i; j++) {
                if (isPal[j][i] && (i - j + 1) >= k) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
        }
        
        return dp[n];
    }
}