class Solution {
    public int countRotations(String s, int k) {
        int n=s.length();
        String d=s+s;
        int t=0;

        for(int i=0;i<n;i++){
            int sc=0;
            for(int j=0;j<n-1;j++)
                  if(d.charAt(i+j)==d.charAt(i+j+1))
                    sc++;
            if(sc==k)
              t++;
        }
        return t;
    }
}