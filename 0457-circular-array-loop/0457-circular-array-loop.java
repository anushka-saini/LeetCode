class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            boolean forward = nums[i] > 0;

            int slow = i;
            int fast = i;

            while (true) {
                slow = nextIndex(nums, slow, forward);
                if (slow == -1) break;

                fast = nextIndex(nums, fast, forward);
                if (fast == -1) break;

                fast = nextIndex(nums, fast, forward);
                if (fast == -1) break;

                if (slow == fast) {
                    return true;
                }
            }
        }

        return false;
    }

    private int nextIndex(int[] nums, int index, boolean forward) {
        // Direction should remain the same
        if ((nums[index] > 0) != forward) {
            return -1;
        }

        int n = nums.length;

        int next = (index + nums[index]) % n;

        if (next < 0) {
            next += n;
        }

        if (next == index) {
            return -1;
        }

        return next;
    }
}