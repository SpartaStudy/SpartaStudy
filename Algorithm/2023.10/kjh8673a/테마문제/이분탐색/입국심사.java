import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 1;
        long right = (long) times[times.length - 1] * n;
        
        return binarySearch(left, right, n, times);
    }
    
    private static long binarySearch(long l, long r, int key, int[] arr) {
        long result = 0;
        
        long mid;
        while(l <= r) {
            mid = (l + r) / 2;
            
            long tmp = 0;
            for(int i = 0; i < arr.length; i++) {
                tmp += mid / arr[i];
            }        
            
            if(tmp < key) {
                l = mid + 1;
            }else {
                result = mid;
                r = mid - 1;
            }
        }
        
        return result;
    }
}