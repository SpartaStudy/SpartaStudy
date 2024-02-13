/*
완전탐색, 조합, 누적합... 
nC(n/2) * 6^n -> too big
nC(n/2) * (6^(n/2)*2) 개선
*/
class Solution {
    int n;
    int[] ans;
    boolean[] picked;
    int win;
    int[] A;
    int[] B;
    int[][] dices;

    public int[] solution(int[][] dice) {
        n = dice.length;
        dices=dice;
        picked = new boolean[n];
        pick(0);
        for(int i=0;i<ans.length;i++){
            ans[i]++;
        }
        return ans;
    }

    private void pick(int depth) {
        if (depth == n / 2) {
            int[] pickedA = new int[n / 2];
            int[] pickedB = new int[n / 2];
            int a = 0;
            int b = 0;
            for (int i = 0; i < n; i++) {
                if (picked[i]) {
                    pickedA[a++] = i;
                } else {
                    pickedB[b++] = i;
                }
            }
            roll(pickedA,pickedB);
            return;
        }
        for (int i = 0; i < n; i++) {
            if(picked[i]) continue;
            picked[i] = true;
            pick(depth + 1);
            picked[i] = false;
        }
    }

    private void roll(int[] pickedA, int[] pickedB) {
        A = new int[n / 2 * 100 + 1];
        B = new int[n / 2 * 100 + 1];

        calcSum(true,pickedA, 0, 0);
        calcSum(false,pickedB, 0, 0);
        for (int i = 1; i < n/2*100; i++) {
            B[i] += B[i-1];
        }
        int sum = 0;
        for (int i = 1; i <= n/2*100; i++) {
            sum += A[i]*B[i-1];
        }
        if(win<sum){
            win=sum;
            ans=pickedA;
        }
    }

    private void calcSum(boolean isA,int[] arr, int depth, int sum) {
        if(depth ==n/2){
            if(isA){
                A[sum]++;
            }else{
                B[sum]++;
            }
            return;
        }
        for (int i = 0; i < 6; i++) {
            calcSum(isA,arr,depth+1,sum+dices[arr[depth]][i]);
        }
    }
}
