class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] count = new int[2];
        for (int student : students) {
            count[student]++;
        }
        for (int i = 0; i < sandwiches.length; i++) {
            int sandwich = sandwiches[i];
            if (count[sandwich] > 0) {
                count[sandwich]--;
            } else {
                return sandwiches.length - i;
            }
        }

        return 0;
    }
}