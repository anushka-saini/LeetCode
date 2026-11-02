
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        
        int left = 0;
        int right = costs.length - 1;
        
        while (left < candidates) {
            pq1.add(costs[left++]);
        }
        
        while (right >= left && right >= costs.length - candidates) {
            pq2.add(costs[right--]);
        }
        
        long totalCost = 0;
        
        for (int i = 0; i < k; i++) {
            int val1 = pq1.isEmpty() ? Integer.MAX_VALUE : pq1.peek();
            int val2 = pq2.isEmpty() ? Integer.MAX_VALUE : pq2.peek();
            
            if (val1 <= val2) {
                totalCost += pq1.poll();
                if (left <= right) {
                    pq1.add(costs[left++]);
                }
            } else {
                totalCost += pq2.poll();
                if (left <= right) {
                    pq2.add(costs[right--]);
                }
            }
        }
        
        return totalCost;
    }
}