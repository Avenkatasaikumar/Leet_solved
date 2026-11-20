import java.util.*;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            // option 1: this element becomes x+1 (do this first)
            dp.put(x + 1, dp.getOrDefault(x, 0) + 1);
            // option 2: this element stays x
            dp.put(x, dp.getOrDefault(x - 1, 0) + 1);

            ans = Math.max(ans, Math.max(dp.get(x), dp.get(x + 1)));
        }
        return ans;
    }
}