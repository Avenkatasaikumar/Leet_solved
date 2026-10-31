class Solution{
    public int uniquePaths(int[][] grid){
        final int MOD=1_000_000_007;
        int m=grid.length,n=grid[0].length;
        int[][] R=new int[m+1][n+1];
        int[][] D=new int[m+1][n+1];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==0){
                    int w=(i==0 && j==0) ? 1 : (R[i][j]+D[i][j])%MOD;
                    if(i==m-1 && j==n-1) return w;
                    R[i][j+1]=(R[i][j+1]+w)%MOD;   // move right
                    D[i+1][j]=(D[i+1][j]+w)%MOD;   // move down
                }
                else{
                    // entered moving right -> turn down
                    D[i+1][j]=(D[i+1][j]+R[i][j])%MOD;
                    // entered moving down -> turn right
                    R[i][j+1]=(R[i][j+1]+D[i][j])%MOD;
                }
            }
        }
        return 0;
    }
}