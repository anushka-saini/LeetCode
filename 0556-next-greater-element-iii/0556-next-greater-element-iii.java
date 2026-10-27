class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();

        int i = digits.length - 2;

        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        if (i < 0) return -1;

        int j = digits.length - 1;

        while (digits[j] <= digits[i]) {
            j--;
        }

        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;

        reverse(digits, i + 1, digits.length - 1);

        try {
            long result = Long.parseLong(new String(digits));
            return result <= Integer.MAX_VALUE ? (int) result : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void reverse(char[] digits, int left, int right) {
        while (left < right) {
            char temp = digits[left];
            digits[left] = digits[right];
            digits[right] = temp;
            left++;
            right--;
        }
    }
}