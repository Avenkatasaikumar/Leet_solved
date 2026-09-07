class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;
        long[] end = new long[26];

        for (char ch : s.toCharArray()) {
            long total = 0;
            for (long e : end) total = (total + e) % MOD;

            long newEnd = (total + 1) % MOD;   // +1 accounts for appending to the empty subsequence
            end[ch - 'a'] = newEnd;             // REPLACE, not add
        }

        long answer = 0;
        for (long e : end) answer = (answer + e) % MOD;

        return (int) answer;
    }
}