import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long totalSubarrays = 0;
        long currentOps = 0;
        
        Deque<long[]> deque = new ArrayDeque<>();
        int r = n - 1;
        
        for (int l = n - 1; l >= 0; l--) {
            long count = 1;
            
            while (!deque.isEmpty() && deque.peekFirst()[0] <= nums[l]) {
                long[] prev = deque.pollFirst();
                long val = prev[0];
                long cnt = prev[1];
                
                currentOps += (nums[l] - val) * cnt;
                count += cnt;
            }
            
            deque.addFirst(new long[]{nums[l], count});
            
            while (currentOps > k) {
                long[] back = deque.peekLast();
                long val = back[0];
                long cnt = back[1];
                
                currentOps -= (val - nums[r]);
                
                if (cnt == 1) {
                    deque.pollLast();
                } else {
                    back[1]--;
                }
                r--;
            }
            
            totalSubarrays += (r - l + 1);
        }
        
        return totalSubarrays;
    }
}