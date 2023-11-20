import java.util.*;
class 양과늑대 {
    int answer=0;
    List<List<Integer>> tree;
    public int solution(int[] info, int[][] edges) {
        tree= new ArrayList<>();
        for(int i=0;i<info.length;i++){
            tree.add(new ArrayList<>());
        }
        for(int i=0;i<edges.length;i++){
            tree.get(edges[i][0]).add(edges[i][1]);
        }

        dfs(info,edges,new HashSet<>(),0,0,0);

        return answer;
    }
    void dfs(int[] info,int[][] edges,Set<Integer> set,int now,int sheep,int wolf){
        if(info[now]==0){
            sheep++;
        }else{
            wolf++;
        }

        if(sheep<=wolf) return;

        answer=Math.max(answer,sheep);

        Set<Integer> newSet = new HashSet<>(set);

        newSet.addAll(tree.get(now));
        newSet.remove(now);

        for(int i:newSet){
            dfs(info,edges,newSet,i,sheep,wolf);
        }
    }

    public void main(String[] args) {
        System.out.println(solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}}));
    }
}
