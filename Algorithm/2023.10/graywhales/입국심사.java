/*
  시간을 binarySearch 탐사대상으로 두고
  조건에 부합할때 right를 mid로 부합하지 않을때 left를 mid+1로 옮겨가면서 탐색
  초기 right는 min*n으로 설정
*/
class Solution {
    public long solution(int n, int[] times) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<times.length;i++){
            min=Math.min(min,times[i]);
        }
        long left=1;
        long right=(long)min*n; //min*n integer범위임을 주의
        long mid;
        long sum;
        while(left<right){
            mid=(left+right)/2;
            sum=0;
            for(int i=0;i<times.length;i++){
                sum+=mid/times[i];
            }
            if(sum>=n){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return right;
    }
}
