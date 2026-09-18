import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Find valid intervals for each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            // Only process if this is the first time we see this character
            if (i != first[c]) continue;

            int right = last[c];
            boolean valid = true;

            // Expand the interval if other characters stretch beyond current bounds

            for (int j = i; j <= right; j++) {
                int charIdx = s.charAt(j) - 'a';
                if (first[charIdx] < i) {
                    // This character appears before our current start, making this interval invalid
                    valid = false;
                    break;
                }
                right = Math.max(right, last[charIdx]);
            }

            if (valid) {
                intervals.add(new int[] { i, right });
            }
        }
        // Step 3: Sort intervals by their right endpoints (greedy approach)
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        // Step 4: Pick non-overlapping intervals
        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return result;
    }
}