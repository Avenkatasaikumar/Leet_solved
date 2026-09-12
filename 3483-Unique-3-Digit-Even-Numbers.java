class Solution {
    public int totalNumbers(int[] digits) {
        int[] available = new int[10];
        for (int d : digits) {
            available[d]++;
        }
        
        int count = 0;
   
        for (int num = 100; num < 1000; num += 2) {
            int h = num / 100;
            int t = (num / 10) % 10;
            int u = num % 10;
            
            int[] needed = new int[10];
            needed[h]++;
            needed[t]++;
            needed[u]++;
            
            boolean possible = true;
            for (int i = 0; i < 10; i++) {
                if (needed[i] > available[i]) {
                    possible = false;
                    break;
                }
            }
            
            if (possible) {
                count++;
            }
        }
        
        return count;
    }
}