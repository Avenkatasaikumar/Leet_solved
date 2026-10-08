class Solution {
    public String removeOuterParentheses(String s) {
        int x=0;
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c=='('){
                if(x>0)
                   sb.append(c);
                x++;   
            }
            else{
                x--;
                if(x>0)
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}