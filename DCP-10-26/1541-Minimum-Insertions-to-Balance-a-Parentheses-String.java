class Solution {
    public int minInsertions(String s) {
       int x=0,y=0;
       for(int i=0;i<s.length();i++){
        char c=s.charAt(i);
        if(c=='('){
            if(x%2!=0){
                y++;
                x--;
            }
            x+=2;
        }
        else{
            x--;
            if(x<0){
                y++;
                x+=2;
            }
        }
       }
       return x+y; 
    }
}