/*
구현
상하랑 좌우가 독립적인 계란말이라 생각하자
*/
class Solution {
    long ans=0;
    
    int rf=1;//0행 뭉침
    int re=1;//n-1행 뭉침
    int cf=1;//0열 뭉침
    int ce=1;//m-1열 뭉침
    
    int r=0;//가장 앞행 position
    int c=0;//가장 앞열 position
    
    int rl;//현재행 길이
    int cl;//현재열 길이
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        rl=n;
        cl=m;
        for (int[] query : queries) {
            int op=query[0];
            int step=query[1];

            if(op==0){//좌
                if(c-step<0){
                    cf+=Math.abs(c-step);
                    cl-=Math.abs(c-step);
                    if(cl<=0){
                        cl=1;
                        cf=m;
                        ce=m;
                    }
                    c=0;
                }else{
                    c-=step;
                }
            }else if(op==1){//우
                if(c+cl+step>m){
                    ce+=(c+cl+step-m);
                    cl-=(c+cl+step-m);
                    c+=step;
                    if(cl<=1){
                        cl=1;
                        cf=m;
                        ce=m;
                    }
                    if(c>=m){
                        c=m-1;
                    }
                }else{
                    c+=step;
                }
            }else if(op==2){//상
                if(r-step<0){
                    rf+=Math.abs(r-step);
                    rl-=Math.abs(r-step);
                    if(rl<=1){
                        rl=1;
                        rf=n;
                        re=n;
                    }
                    r=0;
                }else{
                    r-=step;
                }
            }else if(op==3){//하
                if(r+rl+step>n){
                    re+=(r+rl+step-n);
                    rl-=(r+rl+step-n);
                    r+=step;
                    if(rl<=0){
                        rl=1;
                        rf=n;
                        re=n;
                    }
                    if(r>=n){
                        r=n-1;
                    }
                }else{
                    r+=step;
                }
            }
            
        }
        long rCount=0;
        long cCount=0;
        if(r<=x && x<r+rl){
            if(r==x){
                rCount=rf;
            }else if(r+rl-1==x){
                rCount=re;
            }else{
                rCount=1;
            }
        }
        if(c<=y && y<c+cl){
            if(c==y){
                cCount=cf;
            }else if(c+cl-1==y){
                cCount=ce;
            }else{
                cCount=1;
            }
        }
        
        ans=rCount*cCount;
        return ans;
    }
}
