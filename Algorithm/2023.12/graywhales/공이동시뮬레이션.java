/*
구현
상하랑 좌우가 독립적인
계란말이라 생각하자
*/

    
class Solution {
    
    int rf=1;//0행 갯수
    int re=1;//마지막행 갯수
    int cf=1;//0열 갯수
    int ce=1;//마지막열 갯수

    int rp =0;//가장 앞행 position
    int cp =0;//가장 앞열 position

    int rl;//현재행 길이
    int cl;//현재열 길이
    
    int mm;
    int nn;
    
    public void moveLeft(int step){
        if(cp -step<0){
            cf+=Math.abs(cp -step);
            cl-=Math.abs(cp -step);
            if(cl<=0){
                cl=1;
                cf=mm;
                ce=mm;
            }
            cp =0;
        }else{
            cp -=step;
        }
    }
    public void moveRight(int step){
        if(cp +cl+step>mm){
            ce+=(cp +cl+step-mm);
            cl-=(cp +cl+step-mm);
            cp +=step;
            if(cl<=1){
                cl=1;
                cf=mm;
                ce=mm;
            }
            if(cp >=mm){
                cp =mm-1;
            }
        }else{
            cp +=step;
        }
    }
    public void moveUp(int step){
        if(rp -step<0){
            rf+=Math.abs(rp -step);
            rl-=Math.abs(rp -step);
            if(rl<=1){
                rl=1;
                rf=nn;
                re=nn;
            }
            rp =0;
        }else{
            rp -=step;
        }
    }
    public void moveDown(int step){
        if(rp +rl+step>nn){
            re+=(rp +rl+step-nn);
            rl-=(rp +rl+step-nn);
            rp +=step;
            if(rl<=0){
                rl=1;
                rf=nn;
                re=nn;
            }
            if(rp >=nn){
                rp =nn-1;
            }
        }else{
            rp +=step;
        }
    }
    public long count(int x,int y){
        long r=0;
        long c=0;
        if(rp <=x && x< rp +rl){
            if(rp ==x){
                r=rf;
            }else if(rp +rl-1==x){
                r=re;
            }else{
                r=1;
            }
        }
        if(cp <=y && y< cp +cl){
            if(cp ==y){
                c=cf;
            }else if(cp +cl-1==y){
                c=ce;
            }else{
                c=1;
            }
        }
        return r*c;
    }
    public long solution(int n, int m, int x, int y, int[][] queries) {
        nn=n;
        mm=m;
        rl=n;
        cl=m;
        for (int[] query : queries) {
            int op=query[0];
            int step=query[1];

            if(op==0){//좌
                moveLeft(step);
            }else if(op==1){//우
                moveRight(step);
            }else if(op==2){//상
                moveUp(step);
            }else if(op==3){//하
                moveDown(step);
            }
        }
        
        return count(x,y);

    }
}
