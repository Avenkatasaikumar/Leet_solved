class Solution {
    public int reverseDegree(String s) {
        int sum = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int revAlphabetPos = 26 - (c - 'a');
            int stringPos = i + 1;           
            sum += revAlphabetPos * stringPos;
        }
        return sum;
    }
}