
class Solution {
    public int longestPalindrome(String s) {

        HashSet<Character> set = new HashSet<>();

        int count = 0;

        for (char ch : s.toCharArray()) {

            if (set.contains(ch)) {
                set.remove(ch);
                count += 2;
            }
            else {
                set.add(ch);
            }
        }

        if (!set.isEmpty()) {
            count++;
        }

        return count;
    }
}