import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        
        // Find all divisors of n
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i * i != n) {
                    divisors.add(n / i);
                }
            }
        }
        
        // Create the fully sorted reference array
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        int totalSum = 0;
        
        for (int k : divisors) {
            boolean isSortable = true;
            
            // Check each block of length k
            for (int i = 0; i < n; i += k) {
                // If the block already matches the sorted segment directly, it's valid instantly
                if (isDirectMatch(nums, sortedNums, i, k)) {
                    continue;
                }
                
                // Otherwise, check if it's a valid cyclic shift
                if (!isCyclicMatch(nums, sortedNums, i, k)) {
                    isSortable = false;
                    break;
                }
            }
            
            if (isSortable) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }
    
    private boolean isDirectMatch(int[] nums, int[] sortedNums, int start, int k) {
        for (int j = 0; j < k; j++) {
            if (nums[start + j] != sortedNums[start + j]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isCyclicMatch(int[] nums, int[] sortedNums, int start, int k) {
        // Quick frequency check using a sliding window or hash-like validation
        // Find the shift offset if the first element matches anywhere in the target block
        int firstVal = nums[start];
        
        for (int offset = 0; offset < k; offset++) {
            if (sortedNums[start + offset] == firstVal) {
                // Verify if this rotation matches
                boolean match = true;
                for (int j = 0; j < k; j++) {
                    if (nums[start + j] != sortedNums[start + ((offset + j) % k)]) {
                        match = false;
                        break;
                    }
                }
                if (match) return true;
            }
        }
        return false;
    }
}