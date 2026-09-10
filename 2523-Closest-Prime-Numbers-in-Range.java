class Solution {
    public int[] closestPrimes(int n, int m) {
        int k = (int) Math.sqrt(m);
        ArrayList<Integer> l = new ArrayList<>();
        seive(l, k);
        
        boolean prime[] = new boolean[m - n + 1];
        Arrays.fill(prime, true);
        
        for (int p : l) {
            int first = Math.max(p * p, (n + p - 1) / p * p);
            for (int i = first; i <= m; i += p) {
                prime[i - n] = false;
            }
        }
        
        int c = 0;
        for (int i = n; i <= m; i++) {
            if (prime[i - n] && i > 1) {
                c++;
            }
        }
        
        if (c < 2) {
            return new int[] { -1, -1 };
        }
        
        int[] cp = new int[c];
        int idx = 0;
        for (int i = n; i <= m; i++) {
            if (prime[i - n] && i > 1) {
                cp[idx++] = i;
            }
        }
        
        int minDiff = Integer.MAX_VALUE;
        int[] ans = new int[] { -1, -1 };
        
        for (int i = 0; i < cp.length - 1; i++) {
            int diff = cp[i + 1] - cp[i];
            if (diff < minDiff) {
                minDiff = diff;
                ans[0] = cp[i];
                ans[1] = cp[i + 1];
            }
        }
        
        return ans;
    }
    
    public static void seive(ArrayList<Integer> l, int k) {
        boolean prime[] = new boolean[k + 1];
        for (int i = 2; i <= k; i++) {
            prime[i] = true;
        }
        for (int i = 2; i * i <= k; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= k; j += i) {
                    prime[j] = false;
                }
            }
        }
        for (int i = 2; i <= k; i++) {
            if (prime[i]) {
                l.add(i);
            }
        }
    }
}