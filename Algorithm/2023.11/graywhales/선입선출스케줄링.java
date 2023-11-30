//이분탐색
class Solution {
    int m;
    int count;
    int max=0;
    public int solution(int n, int[] cores) {
        m=cores.length;

        if(m>=n){
            return n;
        }
        
        for(int i=0;i<m;i++){
            max=Math.max(max,cores[i]);
        }
        int left=1;
        int right=max*n/m;
        int mid;
        int time=0;
        int sum;
      //이분탐색으로 time 찾기
        while(left<right){
            mid=(left+right)/2;
            sum=m;
            for(int i=0;i<m;i++){
                sum+=(mid/cores[i]);
            }
            if(sum>=n){
                right=mid;
            }else{
                left=mid+1;
            }
            time=right;
        }
      //순서를 찾기위해 time-1에서 sum을 먼저 구하고
        time--;
        sum=m;
        for(int i=0;i<m;i++){
            sum+=(time/cores[i]);
        }
      //다시 원래 time에서 순서 찾아내기
        time++;
        for(int i=0;i<m;i++){
            if(time%cores[i]==0){
                if(++sum==n) return i+1;
            }

        }
        return -1;
    }
}
