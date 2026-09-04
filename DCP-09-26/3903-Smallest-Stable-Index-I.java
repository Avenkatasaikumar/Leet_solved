class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n=nums.length;
        int arr[]=new int[n];
        arr[n-1]=nums[n-1];
        for(int i=n-2;i>=0;i--)
             arr[i]=Math.min(arr[i+1],nums[i]);
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max=Math.max(max,nums[i]);
            if(max-arr[i]<=k)
               return i;
        }
        return -1;     
    }
}