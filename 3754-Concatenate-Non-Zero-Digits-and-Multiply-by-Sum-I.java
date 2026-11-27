class Solution {
    public long sumAndMultiply(long n) {
        long x=0,y=0;
        while(n>0){
            long r=n%10;
            if(r!=0){
                x=x*10+r;
                y+=r;
            }
            n/=10;
        }
        n=0;
        while(x>0){
           n=n*10+x%10;
           x/=10;
        }
        return n*y;
    }
}