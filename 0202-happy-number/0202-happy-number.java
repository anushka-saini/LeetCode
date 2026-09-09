class Solution {
    public int summ(int n){
        int sum=0;

        while(n>0){
            int d=n%10;
            sum=sum+(d*d);
            n=n/10;
        }
        return sum;
    }
    public boolean isHappy(int n) {
        int slow=n;
        int fast=n;
        while(fast!=1){
            slow=summ(slow);
            fast=summ(summ(fast));
            if(fast==1){
                return true;
            }
            if(slow==fast){
                return false;
            }
        }
        return true;
    }
}