class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        backtrack(res,new StringBuilder(),0,0,n);
        return res;
    }
    public void backtrack(List<String> res,StringBuilder curr,int o,int c,int n){
        if(curr.length()==n*2){
            res.add(curr.toString());
            return;
        }
        if(o<n){
            curr.append('(');
            backtrack(res,curr,o+1,c,n);
            curr.deleteCharAt(curr.length()-1);
        }
        if(c<o){
            curr.append(')');
            backtrack(res,curr,o,c+1,n);
            curr.deleteCharAt(curr.length()-1);
        }
    }
}