class Solution {
    public int minOperations(int[] nums) {
        int n=nums.length;
        if(n==1) return 0;
        int ze=n-1,drop=nums[n-1]>nums[0]?1:0;
        
        for(int i=0;i<n-1;i++){
            if(nums[i]>nums[i+1])
                drop++;
            if(nums[i]==0)
                 ze=i;    
        }

        if(drop==0) return 0;
        if(drop==1) return Math.min(ze,2+n-ze);
        if(drop==n-1) return Math.min(ze+2,n-ze);

        return -1;
    }
}