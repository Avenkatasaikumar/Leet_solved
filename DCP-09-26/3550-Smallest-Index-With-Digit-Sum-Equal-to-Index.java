class Solution {
    public int smallestIndex(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int x= (nums[i]>9) ?fun(nums[i]):nums[i];
            if(x==i)
               return i;   
        }
        return -1;
    }

    public static int fun(int n){
        int x=0;
        while(n>0){
            int r=n%10;
            x+=r;
            n/=10;
        }
        return x;
    }
}