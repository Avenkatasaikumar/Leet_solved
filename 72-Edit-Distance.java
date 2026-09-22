class Solution {
    public int minDistance(String s1, String s2) {
    int n=s1.length();
    int m=s2.length();
    int dp[][]=new int[n][m];

    for(int i=0;i<n;i++)
      Arrays.fill(dp[i],-1);
    int x=fun(n-1,m-1,s1,s2,dp);
    return x;
    }  
    
    public static int fun(int i,int j,String s1,String s2,int dp[][]){
    if(i<0)
      return j+1;
    if(j<0)
      return i+1;

    if(dp[i][j]!=-1)
      return dp[i][j];
    if(s1.charAt(i)==s2.charAt(j)){
      dp[i][j]=0+fun(i-1,j-1,s1,s2,dp);
      return dp[i][j];
    }
    else{
      int in=1+fun(i,j-1,s1,s2,dp);
      int re=1+fun(i-1,j-1,s1,s2,dp);
      int de=1+fun(i-1,j,s1,s2,dp);
      dp[i][j]=Math.min(in,Math.min(re,de));
      return dp[i][j];
    }
  }
}