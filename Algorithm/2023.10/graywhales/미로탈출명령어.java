class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // d l r u
        // 움직이는걸 먼저 할건데 d l r u 순으로... 어라 이러면 안되겠네
        String answer = "";
        int dx=Math.abs(r-x);
        int dy=Math.abs(c-y);
        //딱떨어질때
        if(dx+dy==k) {
            //도착지점이 아래쪽일경우
            if(r-x>0){
                for(int i=0;i<dx;i++){
                    answer+="d";
                }
            }
            //도착지점이 왼쪽일경우
            if(c-y<0){
                for(int i=0;i<dy;i++){
                    answer+="l";
                }
            }
            //도착지점이 오른쪽일경우
            if(c-y>0){
                for(int i=0;i<dy;i++){
                    answer+="r";
                }
            }
            //도착지점이 윗쪽일경우
            if(r-x<0){
                for(int i=0;i<dx;i++){
                    answer+="u";
                }
            }
        //떨어지는거 + 짝수배 (헛걸음 왕복)
        }else if(dx+dy<k&&(k-dx-dy)%2==0){
            int diff=(k-dx-dy)/2;
            int nx=x;
            int ny=y;
            int u=0;
            int l=0;
            if(r-x>0){
                for(int i=0;i<dx;i++){
                    answer+="d";
                }
            }
            //아랫쪽 헛걸음
            if(n>r){
                while(diff-->0){
                    answer+="d";
                    answer+="u";
                }
            }
            if(c-y<0){
                for(int i=0;i<dy;i++){
                    answer+="l";
                }
            }
            //왼쪽 헛걸음
            if(c!=1){
                while(diff-->0){
                    answer+="l";
                    answer+="r";
                }
            }
            if(c-y>0){
                for(int i=0;i<dy;i++){
                    answer+="r";
                }
            }
            //오른쪽 헛걸음
            if(c!=m){
                while(diff-->0){
                    answer+="r";
                    answer+="l";
                }
            }
            if(r-x<0){
                for(int i=0;i<dx;i++){
                    answer+="u";
                }
            }
            //윗쪽 헛걸음
            if(r>1){
                while(diff-->0){
                    answer+="u";
                    answer+="d";
                }
            }
        }else{
            answer="impossible";
        }
        return answer;
    }
}
