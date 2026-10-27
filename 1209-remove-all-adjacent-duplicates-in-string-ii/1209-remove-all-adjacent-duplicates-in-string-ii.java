
class Solution {
    public String removeDuplicates(String s, int k) {
        char[] chars = s.toCharArray();
        int[] count = new int[chars.length];
        int size = 0;

        for (char ch : chars) {
            chars[size] = ch;
            count[size] = (size > 0 && chars[size - 1] == ch)
                    ? count[size - 1] + 1 : 1;

            size++;

            if (count[size - 1] == k) {
                size -= k;
            }
        }

        return new String(chars, 0, size);
    }
}
