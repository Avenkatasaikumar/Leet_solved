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
                // Extract block and its corresponding sorted target segment
                int[] block = new int[k];
                int[] target = new int[k];
                
                System.arraycopy(nums, i, block, 0, k);
                System.arraycopy(sortedNums, i, target, 0, k);
                
                // Check if 'target' is a cyclic rotation of 'block'
                if (!isCyclicRotation(block, target)) {
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
    
    private boolean isCyclicRotation(int[] block, int[] target) {
        int k = block.length;
        // Create double block for cyclic substring check
        int[] doubleBlock = new int[2 * k];
        System.arraycopy(block, 0, doubleBlock, 0, k);
        System.arraycopy(block, 0, doubleBlock, k, k);
        
        // Search for 'target' subarray inside 'doubleBlock'
        for (int i = 0; i < k; i++) {
            boolean match = true;
            for (int j = 0; j < k; j++) {
                if (doubleBlock[i + j] != target[j]) {
                    match = false;
                    break;
                }
            }
            if (match) return true;
        }
        
        return false;
    }
}