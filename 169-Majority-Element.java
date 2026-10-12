class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }    
        int max=0,ele=-1;
        for(Map.Entry<Integer,Integer> en:map.entrySet()){
                if(en.getValue()>max){
                    max=en.getValue();
                    ele=en.getKey();
                }         
        }
        return ele;
    }
}