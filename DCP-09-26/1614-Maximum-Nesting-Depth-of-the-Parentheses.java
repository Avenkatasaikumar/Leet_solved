class Solution {
    public int maxDepth(String s) {
        int md=0,curd=0;
        for(char c:s.toCharArray()){
            if(c=='(')
               curd++;
            else if(c==')')
                curd--;
            md=Math.max(md,curd);       
        }
        return md;
    }
}