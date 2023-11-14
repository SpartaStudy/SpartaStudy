class Solution {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int playTime=strToInt(play_time);
        int advTime=strToInt(adv_time);
        if(playTime==advTime){
            return intToStr(0);   
        }
        
        long[] sum=new long[playTime];
        
        int startTime;
        int endTime;
        for(int i=0;i<logs.length;i++){
            startTime=strToInt(logs[i].split("-")[0]);
            endTime=strToInt(logs[i].split("-")[1]);
            for(int j=startTime;j<endTime;j++){ //시청시간부터 시청 끝나기시간까지 더하기
                sum[j]++;
            }
        }
        long val=0;
        //val = 누적재생시간
        //누적합으로 풀려고 했더니 도는 횟수가 더 많아서 17번 시간초과남...
        for(int i=0;i<advTime;i++){
            val+=sum[i];
        }
        
        long max=val;
        int ans=0;

        //최대시간 갱신
        for(int i=advTime;i<playTime;i++){
            val+=sum[i]-sum[i-advTime];
            if(val>max){
                max=val;
                ans=i-advTime+1;
            }
        }
        
        answer=intToStr(ans);
        return answer;
    }
    private int strToInt(String time){
        int t=0;
        t+=60*60*Integer.parseInt(time.split(":")[0]);
        t+=60*Integer.parseInt(time.split(":")[1]);
        t+=Integer.parseInt(time.split(":")[2]);
        return t;
    }
    private String intToStr(int time){
        String s="";
        s+=String.format("%02d", time/3600);
        s+=":";
        s+=String.format("%02d", (time/60)%60);
        s+=":";
        s+=String.format("%02d", time%60);
        return s;
    }
}
