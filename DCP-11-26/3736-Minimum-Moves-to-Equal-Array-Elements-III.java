class Solution {
    public int minMoves(int[] nums) {
        int max=nums[0];
        for(int n:nums)
            if(n>max)
               max=n;
        int sum=0;
        for(int n:nums)
            sum+=max-n;
        return sum;           
    }
}