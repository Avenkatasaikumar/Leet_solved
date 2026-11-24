class Solution {
    public int maxLength(int[] nums) {
        int n=nums.length;
        int ans=0;

        for(int i=0;i<n;i++){
            long product=1;
            long gcd=0;
            long lcm=1;

            for(int j=i;j<n;j++){
                product*=nums[j];
                gcd=gcd(gcd,nums[j]);
                lcm=lcm(lcm,nums[j]);

                if(product==gcd*lcm){
                    ans=Math.max(ans,j-i+1);
                }
            }
        }
        return ans;
    }

    public long gcd(long a,long b){
        while(b!=0){
            long temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }

    public long lcm(long a,long b){
        return (a/gcd(a,b))*b;
    }
}
