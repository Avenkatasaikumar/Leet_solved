class Solution {
    public int findPairs(int[] nums, int k) {
        if(k<0) return 0;
        int count=0;
        if(k==0){
            Map<Integer,Integer> map=new HashMap<>();
            for(int n:nums){
                map.put(n,map.getOrDefault(n,0)+1);
            }    
            for(Map.Entry<Integer,Integer> en:map.entrySet()){
                if(en.getValue()>=2)
                    count++;     
            }
            
        }
        else{
            Set<Integer> un=new HashSet<>();
            Set<Integer> vis=new HashSet<>();

           for (int n:nums) {
                un.add(n);
            }
            
            for (int n:un) {
                if (un.contains(n + k)) {
                    count++;
                }
            }
        }
        return count;
    }
}