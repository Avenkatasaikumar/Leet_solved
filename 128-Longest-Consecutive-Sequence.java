class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length==0)
           return 0;
        HashSet<Integer> hs=new HashSet<>();
        for(int n:nums)
            hs.add(n);

        int max=0;
        for(int n:hs){
            if(!hs.contains(n-1)){
                int curr=n,c=1;
                while(hs.contains(curr+1)){
                    curr+=1;
                    c++;
                }
                max=Math.max(max,c);
            }
        }
        return max;
   }
}