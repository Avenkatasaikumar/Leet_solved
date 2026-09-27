import java.util.*;
class Solution {
    public String reverseParentheses(String s) {
        Stack<Character> st=new Stack<>();
        for(char c:s.toCharArray()){
            if(c==')'){
                List<Character> temp=new ArrayList<>();
                while(!st.isEmpty() && st.peek()!='(')
                       temp.add(st.pop());
                if(!st.isEmpty())
                    st.pop();     
                for(char ch:temp)
                    st.push(ch);      
            }
            else
                st.push(c);
        }

        StringBuilder result = new StringBuilder();
        for (char c : st) {
            result.append(c);
        }
        
        return result.toString();
    }
}