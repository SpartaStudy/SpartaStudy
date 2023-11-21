/* 
    효율성 테스트의 경우 최대 100만개의 배열을 어떻게 관리할 것이냐.
    그때 풀었던 광고삽입 문제처럼 누적합 풀이. 2차원 누적합
*/
class Solution {
    static int N;
    static int M;
    static int[][] sum;
    public int solution(int[][] board, int[][] skill) {
        N=board.length;
        M=board[0].length;
        
        sum = new int[N+1][M+1];
        
        for(int i=0;i<skill.length;i++){
            accumulate(skill[i]);
        }
        makePrefixSum();
        int answer = check(board);
        return answer;
    }
    private static int check(int[][] board){
        int count=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(sum[i][j]+board[i][j]>0){
                    count++;
                }
            }
        }
        return count;
    }
    private static void makePrefixSum(){
        for(int i=0;i<M;i++){
            for(int j=1;j<N+1;j++){
                sum[j][i]+=sum[j-1][i];
            }
        }
    }
    private static void accumulate(int[] skill){
        int type=skill[0];
        int r1=skill[1];
        int c1=skill[2];
        int r2=skill[3];
        int c2=skill[4];
        int degree=skill[5];
        
        type= type==1? -1:1;
      
        for(int i=c1;i<c2+1;i++){
            sum[r1][i]+=type*degree;
            sum[r2+1][i]-=type*degree;
        }
        
    }
}
