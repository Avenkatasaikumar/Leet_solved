import java.util.*;

class Solution {
    int m;
    long[] c, s;   // BIT: c = counts, s = sums, indexed by value rank

    // add (dc, ds) at rank i
    void upd(int i, int dc, long ds) {
        for (; i <= m; i += i & -i) { c[i] += dc; s[i] += ds; }
    }

    public long minOperations(int[] a, int x, int k) {
        int n = a.length;

        // 1. coordinate compression: unique sorted values
        int[] v = Arrays.stream(a).distinct().sorted().toArray();
        m = v.length;
        c = new long[m + 1];
        s = new long[m + 1];
        int hi = Integer.highestOneBit(m);

        // 2. cost of every window of length x
        long[] cost = new long[n - x + 1];
        long tot = 0;

        for (int i = 0; i < n; i++) {
            // add new element
            upd(Arrays.binarySearch(v, a[i]) + 1, 1, a[i]);
            tot += a[i];

            // remove element leaving the window
            if (i >= x) {
                upd(Arrays.binarySearch(v, a[i - x]) + 1, -1, -a[i - x]);
                tot -= a[i - x];
            }

            if (i < x - 1) continue;   // window not full yet

            // find median: the ceil(x/2)-th smallest element
            long t = (x + 1) / 2;
            int pos = 0;
            for (int p = hi; p > 0; p >>= 1) {
                if (pos + p <= m && c[pos + p] < t) {
                    pos += p;
                    t -= c[pos];
                }
            }
            long med = v[pos];

            // count and sum of elements <= median
            long cl = 0, sl = 0;
            for (int j = pos + 1; j > 0; j -= j & -j) {
                cl += c[j];
                sl += s[j];
            }

            // cost = (below median) + (above median)
            cost[i - x + 1] = med * cl - sl + (tot - sl) - med * (x - cl);
        }

        // 3. DP: choose k non-overlapping windows with minimum total cost
        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];          // 0 windows -> cost 0

        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = 1; i <= n; i++) {
                cur[i] = cur[i - 1];            // skip element i
                if (i >= x && prev[i - x] < INF) {   // end a window at i
                    cur[i] = Math.min(cur[i], prev[i - x] + cost[i - x]);
                }
            }
            prev = cur;
        }
        return prev[n];
    }
}