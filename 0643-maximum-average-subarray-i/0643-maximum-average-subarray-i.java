class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int n = nums.length;

        long window_sum = 0;

        for (int i = 0; i < k; i++) {
            window_sum += nums[i];
        }

        long max_sum = window_sum;


        for (int j = k; j < n; j++) {

            window_sum += nums[j];
            window_sum -= nums[j - k];

            max_sum = Math.max(max_sum, window_sum);
        }

        return (double) max_sum / k;
    }
}