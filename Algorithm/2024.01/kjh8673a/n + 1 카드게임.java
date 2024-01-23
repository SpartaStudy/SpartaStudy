import java.util.*;

class Solution {
    static int[] cards;
    static int len;
    static List<Integer> firstCard;
    static List<Integer> pickedCard;
    
    public int solution(int coin, int[] cards) {
        this.cards = cards;
        len = cards.length;
        int idx = len / 3;
        
        firstCard = new ArrayList<>();
        initGame();
        
        pickedCard = new ArrayList<>();
        
        int answer = 0;
        while(true) {
            answer++;
            if(idx == len) {
                break;
            }
            
            drawCard(idx);
            idx += 2;
            
            if(withFirstCard()) {
                continue;
            }else if(coin >= 1 && withBothCards()) {
                coin--;
                continue;
            }else if(coin >= 2 && withPickedCard()) {
                coin -= 2;
                continue;
            }else {
                break;
            }
        }
        
        return answer;
    }
    
    private static boolean withPickedCard() {
        for(int card : pickedCard) {
            if(pickedCard.contains(len + 1 - card)) {
                pickedCard.remove(Integer.valueOf(card));
                pickedCard.remove(Integer.valueOf(len + 1 - card));
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean withBothCards() {
        for(int card : firstCard) {
            if(pickedCard.contains(len + 1 - card)) {
                firstCard.remove(Integer.valueOf(card));
                pickedCard.remove(Integer.valueOf(len + 1 - card));
                return true;
            }
        }
        
        return false;
    }
    
    private static boolean withFirstCard() {
        for(int card : firstCard) {
            if(firstCard.contains(len + 1 - card)) {
                firstCard.remove(Integer.valueOf(card));
                firstCard.remove(Integer.valueOf(len + 1 - card));
                return true;
            }
        }
        
        return false;
    }
    
    private static void drawCard(int idx) {
        for(int i = idx; i < idx + 2; i++) {
            pickedCard.add(cards[i]);
        }
    }
    
    private static void initGame() {
        for(int i = 0; i < len / 3; i++) {
            firstCard.add(cards[i]);
        }
    }
}