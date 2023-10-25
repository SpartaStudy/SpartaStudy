import java.util.*;

class Solution {

    public long solution(int n, int[] times) {
        int[] a = times;
        long hi = times[0] * (long)n;
        return binarySearch(1, hi, n, a);
        
    }
    
    public long binarySearch(long lo, long hi, int n, int[] times){
       
        while(lo < hi){
            long mid = (lo + hi) / 2;
            
            long personCnt = 0;
            for(int time : times){
                personCnt += mid / time;
            }
            
            if(personCnt >= n){
                hi = mid;
            }
            else{
                lo = mid + 1;
            }
        }

        return hi;
    }
}