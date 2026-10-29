import java.util.*;

class Solution {
    private int n;
    private List<List<Integer>> adj;
    private char[] chars;
    

    private int timer;
    private int[] inTime, outTime, nodeAtTime;
    private int[] depth;
    private int[][] up; 
    private int LOG;
    
   
    private int[] bit;

    private void dfs(int u, int p, int d) {
        depth[u] = d;
        up[u][0] = p;
        for (int i = 1; i <= LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        
        inTime[u] = ++timer;
        nodeAtTime[timer] = u;
        
        for (int v : adj.get(u)) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        outTime[u] = timer;
    }

    private void updateBIT(int idx, int val) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= val;
        }
    }

    private int queryBIT(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    private void rangeUpdate(int l, int r, int val) {
        updateBIT(l, val);
        updateBIT(r + 1, val);
    }

    private int getMask(int u) {
        return queryBIT(inTime[u]);
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOG; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.chars = s.toCharArray();
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        LOG=0;
        while((1<<LOG)<=n) LOG++;

        inTime=new int[n+1];
        outTime=new int[n+1];
        nodeAtTime=new int[n+1];
        depth=new int[n];
        up=new int[n][LOG+1];
        bit=new int[n+2];
        timer=0;

        dfs(0,0,0);

        int[] pref=new int[n];
        calculatePrefixXor(0,0,0,pref);
        for(int i=0;i<n;i++){
            rangeUpdate(inTime[i],inTime[i],pref[i]);
        }

        List<Boolean> result=new ArrayList<>();

        for(String qStr:queries){
            String[] parts=qStr.split(" ");
            if(parts[0].equals("update")){
                int u=Integer.parseInt(parts[1]);
                char newChar=parts[2].charAt(0);
                char oldChar=chars[u];
                if(oldChar!=newChar){
                    chars[u]=newChar;
                    int diff=(1<<(oldChar-'a')) ^ (1<<(newChar-'a'));
                    rangeUpdate(inTime[u],outTime[u],diff);
                }
            } else{
                int u=Integer.parseInt(parts[1]);
                int v=Integer.parseInt(parts[2]);
                int lca=getLCA(u,v);

                int maskU=getMask(u);
                int maskV=getMask(v);
                int maskParentLca=(lca==0)?0:getMask(up[lca][0]);

                int pathMask=maskU^maskV^maskParentLca^(1<<(chars[lca]-'a'));

                if(pathMask==0 || (pathMask & (pathMask-1))==0){
                    result.add(true);
                }
                else{
                    result.add(false);
                }
            }
        }
        return result;
    }

    private void calculatePrefixXor(int u,int p,int currentXor,int[] pref){
        currentXor^=(1<<(chars[u]-'a'));
        pref[u]=currentXor;
        for(int v:adj.get(u)){
            if(v!=p){
                calculatePrefixXor(v,u,currentXor,pref);
            }
        }
    }
}