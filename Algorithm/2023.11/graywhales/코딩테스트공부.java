import java.util.*;
/*
그리디? 냅색? ...
---
우선 풀 수 있는지 체크가 가장 우선
풀 수 있으면 그때 1step 가보기
---
visit 도입해보니 사실상 냅색문제다.
alp*cop가 가방공간이고 그 공간까지 가기위해서 최소의 time을 갖게끔 탐색하는것
---
최적부분 구조를 가지는 dp로 분류
*/


class Solution {
    int mal = 0;
    int mco = 0;
    int ans = 300;
    int n;
    int[][] visit=new int[151][151];
    
    public int solution(int alp, int cop, int[][] problems) {
        n=problems.length;
        
        for(int i=0;i<n;i++){
            mal=Math.max(mal,problems[i][0]);
            mco=Math.max(mco,problems[i][1]);
        }
        
        for(int i=0;i<151;i++){ //visit 초기화
            for(int j=0;j<151;j++){
                visit[i][j]=ans;
            }
        }
        
        dfs(alp,cop,0,problems);
        
        return ans;
    }
    
    private void dfs(int alp,int cop, int t,int[][] prob){

        if(t>=ans){ //가지치기1. 
            return;
        }
        
        if(alp>=mal&&cop>=mco){
            ans = Math.min(ans, t);
            return;
        }
        
        if(visit[alp][cop]<=t){ //가지치기2. 냅색
            return;
        }else{
            visit[alp][cop]=t;
        }
        
        for(int i=0;i<n;i++){
            int areq = prob[i][0];
            int creq = prob[i][1];
            int arwd = prob[i][2];
            int crwd = prob[i][3];
            int cost = prob[i][4];
            if(areq<=alp&&creq<=cop){ //풀 수 있는 문제라면
                dfs(alp+arwd>=150?150:alp+arwd,cop+crwd>=150?150:cop+crwd,t+cost,prob); //풀 수 있다면 풀기 대신 150을 넘어가면 visit 배열넘어가서 런타임에러
            }
        }
        
        if(alp < mal){ //알고력 올리기
            dfs(alp+1,cop,t+1,prob);
        }
        if(cop < mco){ //코딩력 올리기
            dfs(alp,cop+1,t+1,prob);
        }
        
    }
}
