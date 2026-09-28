class Solution {
    public boolean isValid(String t) {
     Stack<Character> s=new Stack<>();
     int i=0;
     while(i<t.length()){
        char c=t.charAt(i);
        if(c=='(' || c=='{' || c=='[')
             s.push(c);
        else{
             if(s.isEmpty()) return false;
             char x=s.peek();
             if((c==')' && x=='(') || (c==']' && x=='[') || (c=='}' && x=='{'))
                  s.pop();
             else
                  return false;     
        } 
        i++;    
     }
     return s.isEmpty();
    }
}