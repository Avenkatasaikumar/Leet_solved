class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE);
        int sum = 0, left = 0;
        int minLenSoFar = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int currLen = right - left + 1;
                if (left > 0 && dp[left - 1] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + dp[left - 1]);
                }
                minLenSoFar = Math.min(minLenSoFar, currLen);
            }
            dp[right] = minLenSoFar;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}