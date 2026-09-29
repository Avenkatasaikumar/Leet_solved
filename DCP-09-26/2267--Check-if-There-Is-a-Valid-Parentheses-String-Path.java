import java.util.Arrays;

class Solution {
    private Boolean[][][] memo;
    private int m, n;

    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // Quick parity check: the length of any path must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // Max possible balance count can't exceed (m + n)
       memo = new Boolean[m][n][m + n];
        return dfs(grid, 0, 0, 0);
    }

    private boolean dfs(char[][] grid, int r, int c, int count) {
        // If out of bounds or balance goes negative
        if (r >= m || c >= n) return false;
        
        // Update count based on current cell
        if (grid[r][c] == '(') {
            count++;
        } else {
            count--;
        }
        
        // If balance drops below 0, invalid bracket sequence
        if (count < 0) return false;
        
        // If we reached the bottom-right cell, check if balance is 0
        if (r == m - 1 && c == n - 1) {
            return count == 0;
        }
        
        // Check memo table
        if (memo[r][c][count] != null) {
            return memo[r][c][count];
        }
        
        // Move down or right
        boolean down = dfs(grid, r + 1, c, count);
        boolean right = dfs(grid, r, c + 1, count);
        
        return memo[r][c][count] = down || right;
    }
}