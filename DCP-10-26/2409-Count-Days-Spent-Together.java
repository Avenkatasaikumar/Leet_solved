class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aa=fun(arriveAlice),al=fun(leaveAlice),ba=fun(arriveBob),bl=fun(leaveBob);
        int ols=Math.max(aa,ba);
        int ole=Math.min(al,bl);

        return Math.max(0,ole-ols+1);
    }

    public static int fun(String x){
        int days[]={31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int m=Integer.parseInt(x.substring(0,2));
        int d=Integer.parseInt(x.substring(3,5));
        int td=d;
        for(int i=0;i<m-1;i++){
            td+=days[i];
        }
        return td;
    }
}