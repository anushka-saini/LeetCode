class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];

        for (char c : t.toCharArray()) {
            need[c]++;
        }

        int left = 0;
        int count = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            if (need[c] > 0) {
                count++;
            }

            need[c]--;

            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char ch = s.charAt(left);
                need[ch]++;

                if (need[ch] > 0) {
                    count--;
                }

                left++;
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLen);
    }
}