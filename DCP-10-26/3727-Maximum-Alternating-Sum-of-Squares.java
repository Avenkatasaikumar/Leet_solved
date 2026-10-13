class Solution {
    public long maxAlternatingSum(int[] nums) {
        long max=0;
        int n=nums.length;
        long sq[]=new long[n];
        for(int i=0;i<n;i++)
            sq[i]=nums[i]*nums[i];
        Arrays.sort(sq);
        for(int i=0;i<n;i++){
            if(i<n/2)
                max-=sq[i];
            else
                max+=sq[i];
        }
        return max;       
    }
}