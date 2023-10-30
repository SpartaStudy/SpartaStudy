import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int len = enroll.length;
        
        Map<String, Integer> map = new HashMap<>();
        for(int i = 1; i < len + 1; i++) {
            map.put(enroll[i - 1], i);
        }
        map.put("-", 0);
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= len + 1; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < len; i++) {
            list.get(map.get(enroll[i])).add(map.get(referral[i]));
        }
        
        int[] answer = new int[len + 1];
        
        for(int i = 0; i < seller.length; i++) {
            int prev = map.get(seller[i]);
            int earn = amount[i] * 100;
            int give = Math.round(earn / 10);
            answer[prev] += earn - give;
            
            while(give > 0 && prev > 0) {
                prev = list.get(prev).get(0);
                earn = give;
                give = Math.round(earn / 10);
                answer[prev] += earn - give;
            }
            
        }
        
        int[] result = new int[len];
        System.arraycopy(answer, 1, result, 0, len);
        
        return result;
    }
}