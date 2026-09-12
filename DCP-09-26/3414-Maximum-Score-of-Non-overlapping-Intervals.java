class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        long[][] arr = new long[n][4]; // start, end, weight, originalIndex

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[3], b[3]);
        });

        // Step 1: for each interval, binary search the count of usable predecessors
        int[] prevCount = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = 0, hi = i - 1, res = -1;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid][1] < arr[i][0]) { res = mid; lo = mid + 1; }
                else hi = mid - 1;
            }
            prevCount[i] = res + 1;
        }

        // Step 2: dp[i][k] = best (score, sorted index-list) using first i sorted
        // intervals, choosing exactly k of them
        long NEG = Long.MIN_VALUE;
        long[][] score = new long[n + 1][5];
        @SuppressWarnings("unchecked")
        List<Integer>[][] chosen = new List[n + 1][5];

        for (long[] row : score) Arrays.fill(row, NEG);
        for (int i = 0; i <= n; i++) {
            score[i][0] = 0;
            chosen[i][0] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            long[] cur = arr[i - 1];
            for (int k = 0; k <= 4; k++) {
                long best = score[i - 1][k];
                List<Integer> bestList = chosen[i - 1][k]; // skip this interval

                if (k >= 1) {
                    int p = prevCount[i - 1];
                    if (score[p][k - 1] != NEG) {
                        long cand = score[p][k - 1] + cur[2];
                        List<Integer> candList = new ArrayList<>(chosen[p][k - 1]);
                        candList.add((int) cur[3]);
                        Collections.sort(candList);

                        if (cand > best || (cand == best && isLexSmaller(candList, bestList))) {
                            best = cand;
                            bestList = candList;
                        }
                    }
                }
                score[i][k] = best;
                chosen[i][k] = bestList;
            }
        }

        // Step 3: pick the best across k = 0..4
        long bestScore = NEG;
        List<Integer> bestAns = new ArrayList<>();
        for (int k = 0; k <= 4; k++) {
            if (score[n][k] > bestScore ||
                (score[n][k] == bestScore && isLexSmaller(chosen[n][k], bestAns))) {
                bestScore = score[n][k];
                bestAns = chosen[n][k];
            }
        }

        int[] ans = new int[bestAns.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = bestAns.get(i);
        return ans;
    }

    private boolean isLexSmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i);
        }
        return a.size() < b.size();
    }
}