class Solution {
    public int longestConsecutive(int[] nums) {
        if(nums==null || nums.length==0)
           return 0;
        HashSet<Integer> hs=new HashSet<>();
        for(int n:nums)
            hs.add(n);
        PriorityQueue<Integer> h=new PriorityQueue<>(hs);
        int max=0,c=0;
        Integer prev=null;
        while(!h.isEmpty()){
            int curr=h.poll();
            if(prev==null)
               c++;
            else if(curr-prev==1)
               c++;
            else
                c=1;
            max=Math.max(max,c);
            prev=curr;          
        }
        return max;     
    }
}