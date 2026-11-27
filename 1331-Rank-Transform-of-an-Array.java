class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n=arr.length;
        if(n==0)
           return new int[0];
           int sor[]=arr.clone();
           Arrays.sort(sor);
           Map<Integer,Integer> m=new HashMap<>();
           int rank=1;
           for(int i=0;i<n;i++){
            if(!m.containsKey(sor[i]))
                m.put(sor[i],rank++);
           }
           int ans[]=new int[n];
           for(int i=0;i<n;i++)
               ans[i]=m.get(arr[i]);

           return ans;    
    }
}