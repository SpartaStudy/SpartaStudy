import java.util.*;

class Solution {
    /*
    타임 테이블 배열의 String 원소를 int형의 분단위로 변경하여 시간순 정렬한 타임리스트를 생성한다.
    버스 도착시간에 맞춰 버스에 탈 수 있는 시간대의 사람을 타임리스트에서 찾는다.
    콘은 마지막 버스를 타는 것이 가장 늦은 시각이다.
    1. 마지막 버스를 탈 수 있다면, 버스 도착시간이 가장 늦은 시간
    2. 마지막 버스를 탈 수 없다면, 가장 마지막에 탄 사람의 시간에서 1분을 뺀 시간이 가장 늦은 시간
    */
    
    public String solution(int n, int t, int m, String[] timetable) {
        List<Integer> timeList = new ArrayList<>();
        int busTime = 540;
        int conTime = 0;
    
        // 크루들이 대기열에 도착하는 시간을 분 단위로 변경 후 시간 순서대로 정렬
        for(int i = 0; i < timetable.length; i++){
            String[] time = timetable[i].split(":");
            int min = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            timeList.add(min);
        }
        Collections.sort(timeList);
        
        int idx = 0;
        for(int i = 1; i <= n; i++){
            int sits = m;
            while(sits > 0 && idx < timeList.size()){
                // 탑승 가능한 인원이 있다면 탑승
                if(timeList.get(idx) <= busTime){
                    idx++;
                    sits--;
                }
                // 없다면 종료
                else{
                    break;
                }
            }
            
            if(i == n){
                // 마지막 버스일 때 탈 수 있다면 콘은 버스시간이 가장 늦은 시간
                if(sits > 0){
                    conTime = busTime;
                }
                // 탈 수 없다면 이전 크루의 대기열 시간에서 1분 뺀 시간이 가장 늦은 시간
                else{
                    conTime = timeList.get(idx - 1) - 1;
                }
            }
            busTime += t;
        }
        
        int hour = conTime / 60;
        int min = conTime % 60;
        String conHour = hour < 10 ? "0" + Integer.toString(hour) : Integer.toString(hour);
        String conMin = min < 10 ? "0" + Integer.toString(min) : Integer.toString(min);
        return conHour + ":" + conMin;
    }
}