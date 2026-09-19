class Solution {
    public String removeOccurrences(String s, String part) {

        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {

            sb.append(c);

            if (sb.length() >= part.length()) {

                int start = sb.length() - part.length();

                if (sb.substring(start).equals(part)) {
                    sb.delete(start, sb.length());
                }
            }
        }

        return sb.toString();
    }
}