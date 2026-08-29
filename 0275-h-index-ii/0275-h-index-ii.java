class Solution {
    public int hIndex(int[] citations) {

        int n = citations.length;
        int l = 0;
        int r = n - 1;

        while (l <= r) {

            int mid = l + (r - l) / 2;

            int papers = n - mid;

            if (citations[mid] >= papers) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return n - l;
    }
}