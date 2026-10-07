class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res=new ArrayList<>();
        if(s==null) return res;
        Set<String> vis=new HashSet<>();
        Queue<String> q=new LinkedList<>();

        q.add(s);
        vis.add(s);
        boolean foun=false;
        while(!q.isEmpty()){
            int size=q.size();
            List<String> val=new ArrayList<>();
            for(int i=0;i<size;i++){
                String curr=q.poll();
                if(isvalid(curr)){
                    res.add(curr);
                    foun=true;
                }
                if(foun) continue;

                for(int j=0;j<curr.length();j++){
                    if(curr.charAt(j)!='(' && curr.charAt(j)!=')')      continue;
                    String next=curr.substring(0,j)+curr.substring(j+1);
                    if(!vis.contains(next)){
                        vis.add(next);
                        q.add(next);
                    }
                }
            }
            if(foun) break;
        }
        return res;
    }
    
    public boolean isvalid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

}