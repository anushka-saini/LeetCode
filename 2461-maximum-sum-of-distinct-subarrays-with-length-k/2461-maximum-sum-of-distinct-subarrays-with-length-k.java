class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long sum =0;
        long max=0;
        Map<Integer,Integer> map= new HashMap<>();
        int dups=0;
        for(int i=0;i<k;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],0);
            }
            map.put(nums[i],map.get(nums[i])+1);
            sum+=nums[i];
            if(map.get(nums[i])>1){
                dups+=1;

            }

        }
        if(dups==0){
            max=Math.max(max,sum);
        }
        for (int i=k;i<nums.length;i++){
            int numstoadd=nums[i];
            int numstoremove=nums[i-k];
            if(!map.containsKey(numstoadd)){
                map.put(numstoadd,0);

            }
            map.put(numstoadd,map.get(numstoadd)+1);
            if (map.get(numstoadd)>1){
                dups+=1;
            }
        
            sum+=numstoadd;
            if(map.get(numstoremove)>1){
                dups=dups-1;
            }
            map.put(numstoremove,map.get(numstoremove)-1);
            sum-=numstoremove;
            if(dups==0){
                max=Math.max(sum,max);
            }

        }
        return max;
    }
}

