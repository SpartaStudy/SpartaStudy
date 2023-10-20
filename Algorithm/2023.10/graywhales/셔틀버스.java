import java.util.*;
class Solution {
    static int n,t,m;
    static int[] times;
    public String solution(int n, int t, int m, String[] timetable) {
        
        this.n=n;
        this.t=t;
        this.m=m;
        
        int[] bus=new int[n*m];
        int[] visited=new int[n*m];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                bus[i*m+j]=60*9+t*i;
            }
        }
        int p=timetable.length;
        times=new int[p];
        for(int i=0;i<p;i++){
            times[i]=Integer.parseInt(timetable[i].split(":")[0])*60+Integer.parseInt(timetable[i].split(":")[1]);
        }
        Arrays.sort(times);
        int i=0,j=0;
        while(i<n*m&&j<p){//버스 탑승가능한 사람 태우고 그사람의 시간을 visited에 기록
            if(bus[i]>=times[j]){
                visited[i++]=times[j++];
            }else{
                i++;
            }
        }
        int possible=0;
        int max=visited[n*m-1];
        
        if(visited[n*m-1]==0){
            return numToTime(bus[n*m-1]);//만약 마지막 버스 마지막자리 비었다면 그게 답.
        }else{
            for(i=n*m-1;i>=0;i--){
                if(visited[i]<max){//마지막 자리에 탄사람보다 시간이 적은사람이 있다면 그 다음자리에 탑승가능
                    possible=Math.min(bus[i+1],max-1);
                    break;
                }
            }
        }
        possible=Math.max(possible,max-1);
        
        return numToTime(possible);
    }
    public String numToTime(int n){
        int hour=n/60;
        int min=n%60;
        String time="";
        if(hour<10) time+="0"+hour;
        else time+=hour;
        time+=":";
        if(min<10) time+="0"+min;
        else time+=min;
        return time;
    }
}
