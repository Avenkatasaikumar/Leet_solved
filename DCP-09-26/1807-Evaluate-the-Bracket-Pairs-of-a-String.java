class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                // Extract the key inside the brackets
                StringBuilder key = new StringBuilder();
                i++;
                while (i < n && s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }
                sb.append(map.getOrDefault(key.toString(), "?"));
            } else {
                sb.append(c);
            }
            i++;
        }
        
        return sb.toString();
    }
}