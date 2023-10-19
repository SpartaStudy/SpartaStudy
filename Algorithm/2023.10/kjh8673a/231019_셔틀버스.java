import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(String time : timetable) {
            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);
            // 시간표의 시간을 시*60+분 으로 계산해서 pq에 넣는다
            pq.add(hour * 60 + minute);
        }
        
        int start = 9 * 60;
        int cnt = 0;
        int ans = 0;
        while(cnt++ < n) {
            int seat = 0;
            // 현재 시간에 탈 수 있는 만큼 태운다.
            // 모두 태웠다면 마지막사람-1분에 도착해야 탈수있다
            while(!pq.isEmpty() && pq.peek() <= start && seat < m) {
                int tmp = pq.poll();
                seat++;
                if(seat == m) {
                    ans = tmp - 1;
                }
            }
            
            // 다 못태웠다면 출발시간에 도착해도 됨
            if(seat < m) {
                ans = start;
            }
            
            // 시간 늘려서 다음 버스로
            start += t;
        }
        
        // 형식 맞춰서 출력
        String answer = String.format("%02d:%02d", ans/60, ans%60);
        
        return answer;
    }
}