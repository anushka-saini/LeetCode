class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] prefix = new long[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        return mergeSort(prefix, 0, prefix.length, lower, upper);
    }

    private int mergeSort(long[] sum, int left, int right, int lower, int upper) {
        if (right - left <= 1) return 0;

        int mid = left + (right - left) / 2;
        int count = mergeSort(sum, left, mid, lower, upper)
                  + mergeSort(sum, mid, right, lower, upper);

        int low = mid, high = mid;

        for (int i = left; i < mid; i++) {
            while (low < right && sum[low] - sum[i] < lower) low++;
            while (high < right && sum[high] - sum[i] <= upper) high++;
            count += high - low;
        }

        long[] merged = new long[right - left];
        int i = left, j = mid, k = 0;

        while (i < mid && j < right) {
            if (sum[i] <= sum[j]) merged[k++] = sum[i++];
            else merged[k++] = sum[j++];
        }

        while (i < mid) merged[k++] = sum[i++];
        while (j < right) merged[k++] = sum[j++];

        System.arraycopy(merged, 0, sum, left, merged.length);

        return count;
    }
}
