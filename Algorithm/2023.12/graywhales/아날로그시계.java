/*
세개 다 겹칠때 0시0분0초, 12시0분0초 
1초씩 늘려가면서 체크하기.
*/
class Solution {
    int hour = 3600;
    int min = 60;
    int ans = 0;
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int t1 = h1*hour + m1*min + s1;
        int t2 = h2*hour + m2*min + s2;
        
        int diff = t2 - t1;
        
        boolean flag1=false; // 1초전에 체크한걸 체크하는 중복 방지 ... (누더기)
        boolean flag2=false;
        for(int i=0;i<=diff;i++){
            int time = t1+i;
            
            double h = ((double)time/(hour/5))%60; //시침은 60단위로 쪼갬
            double m = ((double)time/min)%60; 
            int s = time%60;
            
            //시침
            if(s>(h-1) && s<=h && (s==h||i!=diff)&&!flag1){ // 정확히 만났거나 1초후에 만날 수 있으면 
                ans++;
                flag1=true;
            }else{
                flag1=false;
            }
            
            //분침
            if(s>(m-1) && s<=m && (m==h||i!=diff)&&!flag2){
                ans++;
                flag2=true;
            }else{
                flag2=false;
            }
            
            if(time==0||time==12*hour) ans--; // 시침,분침,초침 같이 만날때 중복 제거
            
        }
        return ans;
    }
}
