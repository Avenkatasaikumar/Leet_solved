class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> l=new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target)
                 l.add(i);
        }
        return l;
    }
}