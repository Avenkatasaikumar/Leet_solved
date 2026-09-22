class Solution {
    public int countKthRoots(int l, int r, int k) {
        if (k == 1) {
            return r - l + 1;
        }
        return countUpTo(r, k) - countUpTo(l - 1, k);
    }
    
    private int countUpTo(int limit, int k) {
        if (limit < 0) return 0;
        int count = 0;
        for (long x = 0; ; x++) {
            long power = 1;
            for (int i = 0; i < k; i++) {
                power *= x;
                if (power > limit) break;
            }
            if (power > limit) {
                break;
            }
            count++;
        }
        return count;
    }
}