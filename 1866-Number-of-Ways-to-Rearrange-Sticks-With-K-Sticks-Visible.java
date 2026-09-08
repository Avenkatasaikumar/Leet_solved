class Solution {
    public int rearrangeSticks(int n, int k) {
        long [][]dp=new long[n+1][k+1];
        long mod=1000000007;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=k && j<=i;j++){
                if(i==j)
                   dp[i][j]=1;
                else
                   dp[i][j]=(dp[i-1][j-1]+(i-1)*dp[i-1][j])%mod;   
            }
        }

        return (int)dp[n][k];
    }
}