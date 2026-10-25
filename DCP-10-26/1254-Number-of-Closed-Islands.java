import java.util.*;
class Solution {
    public int closedIsland(int[][] grid) {
        int rows=grid.length;
        int cols=grid[0].length;
        int closedIslands=0;

        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if((r==0 || r==rows-1 || c==0 || c==cols-1) && grid[r][c]==0){
                    bfs(grid,r,c);
                }
            }
        }

        for(int r=0;r<rows;r++){
            for(int c=0;c<cols;c++){
                if(grid[r][c]==0) {
                    closedIslands++;
                    bfs(grid,r,c);
                }
            }
        }
        return closedIslands;
    }

    private void bfs(int[][] grid,int startRow,int startCol){
        int rows=grid.length;
        int cols=grid[0].length;
        Queue<int[]> queue=new LinkedList<>();
        
        queue.offer(new int[]{startRow,startCol});
        grid[startRow][startCol]=1;

        int[] dRow={-1,1,0,0};
        int[] dCol={0,0,-1,1};

        while(!queue.isEmpty()){
            int[] cell=queue.poll();
            int r=cell[0];
            int c=cell[1];

            for(int i=0;i<4;i++){
                int nr=r+dRow[i];
                int nc=c+dCol[i];

                if(nr>=0 && nr<rows && nc>=0 && nc<cols && grid[nr][nc]==0){
                    grid[nr][nc]=1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }
}