class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum>9*num) return "";
        StringBuilder sb=new StringBuilder();
        int remaining=sum;
        for(int i=0;i<num;i++){
            int digit=Math.min(9,remaining);
            sb.append((char)('0'+digit));
            remaining-=digit;
        }
        return sb.toString();
    }
}