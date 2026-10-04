class Solution {
    public boolean checkValidString(String s) {
        int cmin=0,cmax=0;
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('){
                cmin++;
                cmax++;
            }
            else if(c==')'){
                cmin--;
                cmax--;
            }
            else{
                cmin--;
                cmax++;
            }
            if(cmax<0)
               return false;
            cmin=Math.max(0,cmin);   
        }
        return cmin==0;
    }
}