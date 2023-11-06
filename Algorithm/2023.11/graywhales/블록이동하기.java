import java.util.*;
class Solution {
    static class Robot implements Comparable<Robot>{
        int r;
        int c;
        boolean h;
        int count;
        public Robot(int r,int c,boolean h,int count){
            this.r=r;
            this.c=c;
            this.h=h;
            this.count=count;
        }
        @Override
        public int compareTo(Robot robot){
            return this.count-robot.count;
        }
    }

    public int solution(int[][] board) {
        int answer=bfs(board);
        return answer;
    }
    static int bfs(int[][] board){
        int n=board.length;
        PriorityQueue<Robot> q=new PriorityQueue<>();
        boolean[][][] visit=new boolean[n][n][2];
        q.offer(new Robot(0,1,true,0));
        visit[0][1][0]=true;
        
        int r,c,nr,nc,count;
        boolean h;
        Robot robot;
        while(!q.isEmpty()){
            robot=q.poll();
            r=robot.r;
            c=robot.c;
            h=robot.h;
            count=robot.count;
            if(r==n-1&&c==n-1){
                return count;
            }
            if(h){
                //rotate right
                if(r-1>=0&&board[r-1][c-1]==0&&board[r-1][c]==0&&!visit[r][c-1][1]){//
                    q.offer(new Robot(r,c-1,!h,count+1));
                    visit[r][c-1][1]=true;
                }
                if(r+1<n&&board[r+1][c-1]==0&&board[r+1][c]==0&&!visit[r+1][c-1][1]){//
                    q.offer(new Robot(r+1,c-1,!h,count+1));
                    visit[r+1][c-1][1]=true;
                }
                //rotate left
                if(r-1>=0&&board[r-1][c-1]==0&&board[r-1][c]==0&&!visit[r][c][1]){//
                    q.offer(new Robot(r,c,!h,count+1));
                    visit[r][c][1]=true;
                }
                if(r+1<n&&board[r+1][c-1]==0&&board[r+1][c]==0&&!visit[r+1][c][1]){//
                    q.offer(new Robot(r+1,c,!h,count+1));
                    visit[r+1][c][1]=true;
                }
                //move horizontal
                if(c-2>=0&&board[r][c-2]==0&&!visit[r][c-1][0]){//
                    q.offer(new Robot(r,c-1,h,count+1));
                    visit[r][c-1][0]=true;
                }
                if(c+1<n&&board[r][c+1]==0&&!visit[r][c+1][0]){//
                    q.offer(new Robot(r,c+1,h,count+1));
                    visit[r][c+1][0]=true;
                }
                if(r-1>=0&&board[r-1][c-1]==0&&board[r-1][c]==0&&!visit[r-1][c][0]){
                    q.offer(new Robot(r-1,c,h,count+1));
                    visit[r-1][c][0]=true;
                }
                if(r+1<n&&board[r+1][c-1]==0&&board[r+1][c]==0&&!visit[r+1][c][0]){
                    q.offer(new Robot(r+1,c,h,count+1));
                    visit[r+1][c][0]=true;
                }
            }
            if(!h){
                //rotate down
                if(c-1>=0&&board[r-1][c-1]==0&&board[r][c-1]==0&&!visit[r-1][c][0]){//
                    q.offer(new Robot(r-1,c,!h,count+1));
                    visit[r-1][c][0]=true;
                }
                if(c+1<n&&board[r-1][c+1]==0&&board[r][c+1]==0&&!visit[r-1][c+1][0]){//
                    q.offer(new Robot(r-1,c+1,!h,count+1));
                    visit[r-1][c+1][0]=true;
                }
                //rotate up
                if(c-1>=0&&board[r-1][c-1]==0&&board[r][c-1]==0&&!visit[r][c][0]){//
                    q.offer(new Robot(r,c,!h,count+1));
                    visit[r][c][0]=true;
                }
                if(c+1<n&&board[r-1][c+1]==0&&board[r][c+1]==0&&!visit[r][c+1][0]){//
                    q.offer(new Robot(r,c+1,!h,count+1));
                    visit[r][c+1][0]=true;
                }
                // move vertical
                if(r-2>=0&&board[r-2][c]==0&&!visit[r-1][c][1]){//
                    q.offer(new Robot(r-1,c,h,count+1));
                    visit[r-1][c][1]=true;
                }
                if(r+1<n&&board[r+1][c]==0&&!visit[r+1][c][1]){//
                    q.offer(new Robot(r+1,c,h,count+1));
                    visit[r+1][c][1]=true;
                }
                if(c-1>=0&&board[r-1][c-1]==0&&board[r][c-1]==0&&!visit[r][c-1][1]){
                    q.offer(new Robot(r,c-1,h,count+1));
                    visit[r][c-1][1]=true;
                }
                if(c+1<n&&board[r-1][c+1]==0&&board[r][c+1]==0&&!visit[r][c+1][1]){
                    q.offer(new Robot(r,c+1,h,count+1));
                    visit[r][c+1][1]=true;
                }
            }
            
        }
        return -1;
    }
}
