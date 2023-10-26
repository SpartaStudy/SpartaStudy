import java.util.*;

class Solution {
    
    class Node{
        String name, referral;
        int profit;
        
        public Node(String enroll, String referral, int profit){
            this.name = name;
            this.referral = referral;
            this.profit = profit;
        }

    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Node> groupMap = new HashMap<>();
        
        // map 정보 입력
        for(int i = 0; i < enroll.length; i++){
            String name = enroll[i];
            String ref = referral[i];
            groupMap.put(name, new Node(name, ref, 0));
        }
        
        for(int i = 0; i < seller.length; i++){
            String name = seller[i];
            int price = amount[i] * 100;
            
            // 추천인에게 10%의 이익을 준다.
            while(true){
                int parentProfit = price / 10;
                int myProfit = price - parentProfit;
                
                // 자기 자신의 이익을 추가
                Node node = groupMap.get(name);
                node.profit += myProfit;
                groupMap.put(name, node);
                
                // 추천인 정보로 갱신
                name = node.referral;
                price = parentProfit;
                
                // 추천인이 없거나 줄 이익이 없으면 종료
                if("-".equals(name) || price < 1){
                    break;
                }
            }
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < answer.length; i++){
            answer[i] = groupMap.get(enroll[i]).profit;
        }
        
        return answer;
    }
}