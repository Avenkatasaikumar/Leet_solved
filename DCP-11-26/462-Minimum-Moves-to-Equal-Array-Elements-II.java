class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        int me=0;
        while(i<j){
            
            me+=nums[j]-nums[i];
            i++;
            j--;
        }
        return me;
    }
}