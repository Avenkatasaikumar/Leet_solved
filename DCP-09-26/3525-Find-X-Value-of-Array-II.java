class Solution {
    int k, n;
    int[][][] cnt;
    int[] segProd;

    private static class QRes {
        int[][] cnt;
        int prod;
        QRes(int[][] cnt, int prod) { this.cnt = cnt; this.prod = prod; }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        cnt = new int[4 * n][][];
        segProd = new int[4 * n];
        build(1, 0, n - 1, nums);

        int rIn0 = 1 % k;
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1];
            int start = queries[i][2], x = queries[i][3];

            update(1, 0, n - 1, idx, val);
            QRes res = query(1, 0, n - 1, start, n - 1);
            result[i] = res.cnt[rIn0][x];
        }
        return result;
    }

    private int[][] makeLeaf(int val) {
        int a = val % k;
        int[][] m = new int[k][k];
        for (int rIn = 0; rIn < k; rIn++) {
            m[rIn][(rIn * a) % k] = 1;
        }
        return m;
    }

    private int[][] merge(int[][] leftCnt, int leftProd, int[][] rightCnt) {
        int[][] res = new int[k][k];
        for (int rIn = 0; rIn < k; rIn++) {
            int mid = (rIn * leftProd) % k;
            for (int x = 0; x < k; x++) {
                res[rIn][x] = leftCnt[rIn][x] + rightCnt[mid][x];
            }
        }
        return res;
    }

    private void pull(int node) {
        int left = 2 * node, right = 2 * node + 1;
        segProd[node] = (segProd[left] * segProd[right]) % k;
        cnt[node] = merge(cnt[left], segProd[left], cnt[right]);
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            cnt[node] = makeLeaf(nums[l]);
            segProd[node] = nums[l] % k;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pull(node);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            cnt[node] = makeLeaf(val);
            segProd[node] = val % k;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2 * node, l, mid, idx, val);
        else update(2 * node + 1, mid + 1, r, idx, val);
        pull(node);
    }

    private QRes query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return null;
        if (ql <= l && r <= qr) return new QRes(cnt[node], segProd[node]);
        int mid = (l + r) / 2;
        QRes leftRes = query(2 * node, l, mid, ql, qr);
        QRes rightRes = query(2 * node + 1, mid + 1, r, ql, qr);
        if (leftRes == null) return rightRes;
        if (rightRes == null) return leftRes;
        int[][] m = merge(leftRes.cnt, leftRes.prod, rightRes.cnt);
        int p = (leftRes.prod * rightRes.prod) % k;
        return new QRes(m, p);
    }
}