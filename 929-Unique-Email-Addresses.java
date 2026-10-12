import java.util.*;
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> s=new HashSet<>();
        for(String e:emails){
            String[] arr=e.split("@");
            String local=arr[0],domain=arr[1];
            int i=local.indexOf('+');
            if(i!=-1){
                local=local.substring(0,i);
            }
            local=local.replace(".","");
            s.add(local+"@"+domain);
        } 
        return s.size();
    }
}