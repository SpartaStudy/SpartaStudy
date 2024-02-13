import java.util.*;
import java.io.*;

public class Main {
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};
    static int[][] drc = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    static int[][] board;
    static int[][] herbicide;
    static int n, m, kYear, c;
    static int[] maxLoc;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        kYear = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        herbicide = new int[n][n];

        for(int i = 0; i < n; i++){
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(str[j]);
            }
        }

        int year = 0;
        int answer = 0;
        while(year < m){
            // 나무의 성장
            growTree();
 
            // 나무를 번식
            board = breadTree();
        
            // 제초제 위치 선정
            maxLoc = new int[3];
            setLocation();
            // 남은 제초제 기간 감소
            decreaseHerbicide();
            
            // 제초제 살포
            startHerbicide();
            
            // 결과
            answer += maxLoc[2];
            year++;
        }

        System.out.println(answer);

        // for(int[] i : board){
        //     System.out.println(Arrays.toString(i));
        // }
        // System.out.println();
        // for(int[] i : herbicide){
        //      System.out.println(Arrays.toString(i));
        // }
        //  System.out.println();
    }

    public static void growTree(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] <= 0)
                    continue;
                int nearTree = 0;
                for(int k = 0; k < 4; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] <= 0 || herbicide[nr][nc] > 0)
                        continue;
                    
                    nearTree++;
                }

                board[i][j] += nearTree;
            }
        }
    }

    public static int[][] breadTree(){
        int[][] nboard = new int[n][n];
        Queue<int[]> q = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == -1){
                    nboard[i][j] = -1;
                    continue;
                }
                else if(board[i][j] == 0) continue;
                nboard[i][j] = board[i][j];
                for(int k = 0; k < 4; k++){
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] > 0 || board[nr][nc] == -1 || herbicide[nr][nc] > 0)
                        continue;
                        
                    q.offer(new int[]{nr, nc});
                }
                
                int cnt = q.size();

                while(!q.isEmpty()){
                    int[] rc = q.poll();
                   
                    nboard[rc[0]][rc[1]] += board[i][j] / cnt;
                }
            }
        }

        return nboard;
    }

    public static void setLocation(){
        for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(board[i][j] <= 0) continue;
                    int treeCnt = board[i][j];
                    for(int k = 0; k < 4; k++){
                        treeCnt += countTree(i, j, k);
                    }
                    if(treeCnt > maxLoc[2]){
                        maxLoc[0] = i;
                        maxLoc[1] = j;
                        maxLoc[2] = treeCnt;
                    }
                }
            }
    }

    public static int countTree(int row, int col, int dir){
        int treeCnt = 0;
        int nr = row;
        int nc = col;
        for(int i = 0; i < kYear; i++){
            nr += drc[dir][0];
            nc += drc[dir][1];
            if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] <= 0)
                return treeCnt;     

            treeCnt += board[nr][nc];
        }

        return treeCnt;
    }

    public static void startHerbicide(){
        int row = maxLoc[0];
        int col = maxLoc[1];

        board[row][col] = 0;
        herbicide[row][col] = c;

        for(int i = 0; i < 4; i++){
             spreadHerbicide(row, col, i);
        }
    }

    public static void spreadHerbicide(int row, int col, int dir){
        int nr = row; 
        int nc = col;

        for(int i = 0; i < kYear; i++){
            nr += drc[dir][0];
            nc += drc[dir][1];

            if(nr < 0 || nr >= n || nc < 0 || nc >= n || board[nr][nc] == -1)
                return;

            herbicide[nr][nc] = c;

            if(board[nr][nc] == 0)
                return;

            board[nr][nc] = 0;
            
        }
    }

    public static void decreaseHerbicide(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(herbicide[i][j] > 0){
                    herbicide[i][j]--;
                }
            }
        }
    }
}