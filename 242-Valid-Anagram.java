class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
             return false;
        int arr1[]=new int[26];
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            arr1[ch-'a']++;
        }
         for(int i=0;i<t.length();i++){
            char ch=t.charAt(i);
            arr1[ch-'a']--;
        }
       for(int i=0;i<26;i++){
            if(arr1[i]!=0)
               return false;
        }
        return true;     
    }
}