class Solution {
    public long countCommas(long n) {
        if(n<1000)
           return 0;
        long tc=0,s=1000,cpn=1;
        while(s<=n){
            long e=(s*1000)-1;
            long curre=Math.min(n,e);
            tc+=(curre-s+1)*cpn;
            s*=1000;
            cpn++;
        }
        return tc;
    }
}