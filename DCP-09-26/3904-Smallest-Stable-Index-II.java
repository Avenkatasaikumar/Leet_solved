class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n=nums.length;
        int min[]=new int[n];
        min[n-1]=nums[n-1];
        for(int i=n-2;i>=0;i--){
            min[i]=Math.min(min[i+1],nums[i]);
        }
        int max1=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
           max1=Math.max(max1,nums[i]);
           if(max1-min[i]<=k)
              return i;
        }
        return -1;
    }
}