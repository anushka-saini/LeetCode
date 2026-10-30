class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1;
        int right = 0;
        
        for (int q : quantities) {
            right = Math.max(right, q);
        }
        
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canDistribute(mid, n, quantities)) {
                ans = mid;          
                right = mid - 1;
            } else {
                left = mid + 1;    
            }
        }
        
        return ans;
    }
    
    // Helper function to check if we can distribute products with `maxAllowed` as the cap
    private boolean canDistribute(int maxAllowed, int n, int[] quantities) {
        int storesNeeded = 0;
        
        for (int q : quantities) {
            // Calculate how many stores are needed for this product type
            // Equivalent to ceil((double) q / maxAllowed) using integer arithmetic
            storesNeeded += (q + maxAllowed - 1) / maxAllowed;
            
            if (storesNeeded > n) {
                return false;
            }
        }
        
        return storesNeeded <= n;
    }
}