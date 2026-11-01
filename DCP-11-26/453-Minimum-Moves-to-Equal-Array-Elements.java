class Solution {
    public int minMoves(int[] nums) {
        int min=nums[0];
        int sum=0;
        for(int n:nums){
            sum+=n;
            if(n<min)
                min=n;
        }
        return sum-(nums.length*min);
    }
}