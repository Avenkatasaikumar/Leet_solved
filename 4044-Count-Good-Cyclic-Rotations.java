class Solution {
    public int countGoodRotations(int[] nums) {
        int n=nums.length;
        int d[]=new int[2*n];
        for(int i=0;i<2*n;i++){
            d[i]=nums[i%n];  
        }

        long pre[]=new long[2*n+1];
        for(int i=0;i<2*n;i++)
               pre[i+1]=pre[i]+d[i];

        int h=n/2,gc=0;

        for(int i=0;i<n;i++){
            long fh=pre[i+h]-pre[i];
            long sh=pre[i+n]-pre[i+h];

            if(fh>sh)
                 gc++;
        }
        return gc;
    }
}