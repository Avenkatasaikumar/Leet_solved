class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long totalOps = (long) k1 + k2;
        
        // Step 1: Find absolute differences and record their frequencies
        int maxDiff = 0;
        int[] count = new int[100005];
        
        for (int i = 0; i < n; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            if (diff > maxDiff) {
                maxDiff = diff;
            }
        }
        
        // Step 2: Greedily reduce the largest differences down
        for (int i = maxDiff; i > 0 && totalOps > 0; i--) {
            if (count[i] == 0) continue;
            
            // Number of elements we can afford to reduce from i to i - 1
            long opsToUse = Math.min(totalOps, (long) count[i]);
            
            count[i] -= opsToUse;
            count[i - 1] += opsToUse;
            totalOps -= opsToUse;
        }
        
        // Step 3: Compute the final sum of squared differences
        long result = 0;
        for (int i = 1; i <= maxDiff; i++) {
            if (count[i] > 0) {
                result += (long) count[i] * i * i;
            }
        }
        
        return result;
    }
}