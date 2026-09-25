class Solution {
    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;
        Set<String> result = parseExpr();
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    // union: term (',' term)*
    private Set<String> parseExpr() {
        Set<String> result = new HashSet<>(parseTerm());
        while (i < s.length() && s.charAt(i) == ',') {
            i++; // skip ','
            result.addAll(parseTerm());
        }
        return result;
    }

    // concatenation: factor+
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");
        while (i < s.length() && s.charAt(i) != ',' && s.charAt(i) != '}') {
            Set<String> factor = parseFactor();
            Set<String> temp = new HashSet<>();
            for (String a : result)
                for (String b : factor)
                    temp.add(a + b);
            result = temp;
        }
        return result;
    }

    // atomic: letters | '{' expr '}'
    private Set<String> parseFactor() {
        if (s.charAt(i) == '{') {
            i++; // skip '{'
            Set<String> inner = parseExpr();
            i++; // skip '}'
            return inner;
        } else {
            int start = i;
            while (i < s.length() && Character.isLetter(s.charAt(i))) i++;
            Set<String> single = new HashSet<>();
            single.add(s.substring(start, i));
            return single;
        }
    }
}