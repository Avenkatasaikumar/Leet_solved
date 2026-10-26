class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long islandSum = dfs(grid, i, j);
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == 0) {
            return 0;
        }

        long currentVal = grid[r][c];
        grid[r][c] = 0; // Mark as visited

        currentVal += dfs(grid, r + 1, c);
        currentVal += dfs(grid, r - 1, c);
        currentVal += dfs(grid, r, c + 1);
        currentVal += dfs(grid, r, c - 1);

        return currentVal;
    }
}