class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bc=new int[32];
        for (int n : nums) {
            for (int i=0;i<32;i++) {
                if ((n&(1<<i))!=0){
                    bc[i]++;
                }
            }
        }
        long mod = 1_000_000_007;
        long ts = 0;
        for (int i=0;i<k;i++){
            long curr=0;
            for (int j=0;j<32;j++){
                if (bc[j]>0){
                    curr|=(1L<<j);
                    bc[j]--;
                }
            }
            ts=(ts+(curr*curr)%mod)%mod;
        }
        return (int)ts;
    }
}