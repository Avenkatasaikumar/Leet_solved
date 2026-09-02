class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> d=new ArrayList<>();
        int fac[] =new int[n+1];
        fac[0]=1;

        for(int i=1;i<=n;i++){
            d.add(i);
            fac[i]=fac[i-1]*i;
        }

        k--;

        StringBuilder s=new StringBuilder();

        for(int i=n;i>0;i--){
            int bs=fac[i-1];
            int index=k/bs;
            s.append(d.get(index));
            d.remove(index);
            k%=bs;
        }
        return s.toString();
    }
}