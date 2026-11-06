import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        int[][] q = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        Arrays.sort(q, (a, b) -> a[0] - b[0]);
        int[] count = new int[n + 1];
        int[] ans = new int[queries.length];
        int left = 0, right = 0, active = 0;
        for (int[] query : q) {
            int t = query[0], start = t - x;
            while (right < logs.length && logs[right][1] <= t) {
                int server = logs[right][0];
                if (count[server] == 0) active++;
                count[server]++;
                right++;
            }
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                count[server]--;
                if (count[server] == 0) active--;
                left++;
            }
            ans[query[1]] = n - active;
        }
        return ans;
    }
}