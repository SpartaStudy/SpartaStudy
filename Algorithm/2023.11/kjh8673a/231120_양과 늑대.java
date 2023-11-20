import java.util.*;
import java.util.stream.*;

class Solution {
    static int answer;
    public int solution(int[] info, int[][] edges) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < info.length; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++) {
            list.get(edges[i][0]).add(edges[i][1]);
        }
        
        // 이동할 수 있는 노드를 저장해서 바로 이동하기
        List<Integer> possibleNext = new ArrayList<>();
        possibleNext.add(0);
        
        answer = 0;
        dfs(0, possibleNext, list, info, 0, 0);
        
        return answer;
    }
    
    private static void dfs(int node, List<Integer> possibleNext, ArrayList<ArrayList<Integer>> list, int[] info, int sheep, int wolf) {
        if(info[node] == 0) {
            sheep++;
        }else {
            wolf++;
        }
        
        if(sheep <= wolf) {
            return;
        }
        
        answer = Math.max(answer, sheep);
        
        possibleNext = possibleNext.stream()
            .filter(item -> item != node)
            .collect(Collectors.toList());
        
        // 자식노드를 이동할 수 있는 노드 리스트에 담기
        for(int nextNode : list.get(node)) {
            possibleNext.add(nextNode);
        }
        
        // 이동
        for(int next : possibleNext) {
            dfs(next, possibleNext, list, info, sheep, wolf);
        }        
    }
}