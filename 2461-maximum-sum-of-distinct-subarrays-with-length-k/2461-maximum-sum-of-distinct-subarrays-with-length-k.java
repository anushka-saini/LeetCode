class Solution {
    public long maximumSubarraySum(int[] arr, int k) {

        int n = arr.length;

        long window_sum = 0;
        long max_ans = 0;

        HashSet<Integer> set = new HashSet<>();

        int left = 0;

        for (int right = 0; right < n; right++) {


            while (set.contains(arr[right])) {
                set.remove(arr[left]);
                window_sum -= arr[left];
                left++;
            }

            set.add(arr[right]);
            window_sum += arr[right];

            if (right - left + 1 > k) {
                set.remove(arr[left]);
                window_sum -= arr[left];
                left++;
            }

            if (right - left + 1 == k) {
                max_ans = Math.max(max_ans, window_sum);
            }
        }

        return max_ans;
    }
}