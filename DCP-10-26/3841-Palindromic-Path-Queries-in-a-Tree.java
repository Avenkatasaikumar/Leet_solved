import java.util.*;

class Solution {
    int n;
    int[] bit;

    void upd(int i, int v) { for (i++; i <= n + 1; i += i & -i) bit[i] ^= v; }
    int qry(int i) { int r = 0; for (i++; i > 0; i -= i & -i) r ^= bit[i]; return r; }
    void rangeXor(int l, int r, int v) { upd(l, v); upd(r + 1, v); }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        bit = new int[n + 3];

        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) { g[e[0]].add(e[1]); g[e[1]].add(e[0]); }

        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        int[][] up = new int[LOG + 1][n];
        int[] depth = new int[n], tin = new int[n], tout = new int[n], idx = new int[n];

        // iterative DFS from node 0
        int timer = 0;
        int[] stack = new int[n];
        int sp = 0;
        stack[sp++] = 0;
        up[0][0] = 0;
        tin[0] = timer++;
        while (sp > 0) {
            int u = stack[sp - 1];
            if (idx[u] < g[u].size()) {
                int v = g[u].get(idx[u]++);
                if (v == up[0][u] && u != 0) continue;
                if (u == 0 && v == 0) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;
                stack[sp++] = v;
            } else {
                tout[u] = timer - 1;
                sp--;
            }
        }
        for (int k = 1; k <= LOG; k++)
            for (int i = 0; i < n; i++)
                up[k][i] = up[k - 1][up[k - 1][i]];

        int[] ch = new int[n];
        for (int i = 0; i < n; i++) {
            ch[i] = s.charAt(i) - 'a';
            rangeXor(tin[i], tout[i], 1 << ch[i]);
        }

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int x = Integer.parseInt(p[1]);
                int c = p[2].charAt(0) - 'a';
                int d = (1 << ch[x]) ^ (1 << c);
                rangeXor(tin[x], tout[x], d);
                ch[x] = c;
            } else {
                int u = Integer.parseInt(p[1]), v = Integer.parseInt(p[2]);
                // LCA
                int a = u, b = v;
                if (depth[a] < depth[b]) { int t = a; a = b; b = t; }
                int diff = depth[a] - depth[b];
                for (int k = 0; k <= LOG; k++)
                    if ((diff >> k & 1) == 1) a = up[k][a];
                int l;
                if (a == b) l = a;
                else {
                    for (int k = LOG; k >= 0; k--)
                        if (up[k][a] != up[k][b]) { a = up[k][a]; b = up[k][b]; }
                    l = up[0][a];
                }
                int mask = qry(tin[u]) ^ qry(tin[v]) ^ (1 << ch[l]);
                ans.add(Integer.bitCount(mask) <= 1);
            }
        }
        return ans;
    }
}