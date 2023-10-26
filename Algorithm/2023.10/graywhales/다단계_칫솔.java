/*
    referral이 존재할때 까지 타고 올라가기
    시간초과 -> distribute 메서드에서 money==0일때도 프루닝하기로 해결
*/
import java.util.*;
class Solution {
    static HashMap<String,String> parent = new HashMap<>();
    static HashMap<String,Integer> profit = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for(int i=0;i<enroll.length;i++){
            parent.put(enroll[i],referral[i]);
            profit.put(enroll[i],0);
        }
        
        for(int i=0;i<seller.length;i++){
            distribute(seller[i],100*amount[i]);
        }
        int[] answer=new int[enroll.length];
        for(int i=0;i<enroll.length;i++){
            answer[i]=profit.get(enroll[i]);
        }
        
        return answer;
    }
    private static void distribute(String seller, int money){
        if(seller.equals("-")||money==0){
            return;
        }
        int referralFee=money/10;
        profit.put(seller,profit.get(seller)+money-referralFee);
        distribute(parent.get(seller),referralFee);
    }
}
