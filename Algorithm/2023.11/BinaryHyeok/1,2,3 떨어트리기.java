import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) { 
        // tree 초기화
        int n = edges.length + 1;
        List<Integer>[] tree = new ArrayList[n];
        for(int i = 0; i < n; i++){
            tree[i] = new ArrayList<>();
        }
        
        // tree 값 입력
        for(int i = 0; i < edges.length; i++){
            int p = edges[i][0];
            int c = edges[i][1];
            // 넣어줄 때 배열 인덱스에 맞춰서 저장
            tree[p - 1].add(c - 1);
        }
        
        int TC = 0;
        // tree 오름차순 정렬
        for(int i = 0; i < n; i++){
            Collections.sort(tree[i]);
            // 해당 노드가 리프노드이면서 타겟이 있다면 숫자를 떨어뜨려야 하는 노드
            if(tree[i].isEmpty() && target[i] > 0){
                TC++;
            }
        }
        
        // 현재 노드가 몇 번 지나갔는지 체크
        int[] pass = new int[n];
        // 현재 노드가 보유하고 있는 숫자 개수
        int[] count = new int[n];
        // 방문 노드인지 저장
        boolean[] visited = new boolean[n];
        // 리프 노드 저장
        List<Integer> leafNode = new ArrayList<>();

        while(TC > 0){
            // 루트노드
            int node = 0;
            
            // 숫자떨어뜨리면서 리프노드까지 내려가기
            while(tree[node].size() > 0){
                /*
                    노드의 순서를 정하는 방법
                    노드의 작은 순서대로 이동 -> 정렬해놨으니 0번부터 시작하면된다.
                    방문횟수 % 자식노드의 수 -> 0 ~ 자식노드의 횟수 - 1 까지 차례대로 나옴
                */
                node = tree[node].get(pass[node]++ % tree[node].size());
            }
            
            // 리프 노드에 떨어진 숫자 증가
            count[node]++;
            leafNode.add(node);
            
            // 리프 노드의 숫자가 모두 1이라고 할 때, target보다 크다면 조건 만족 X
            if(count[node] > target[node]){
                return new int[]{-1};
            }
            
            // 리프노드를 방문하지 않았고, 노드의 총 합이 target보다 크다면 해당 노드는 만족 O
            // 개수 * 3이 타겟보다 크다면 해당 리프노드는 target 값을 만족 O
            if(!visited[node] && count[node] * 3 >= target[node]){
                visited[node] = true;
                TC--;
            }
        }
        
        // 정답을 넣을 배열
        List<Integer> result = new ArrayList<>();
        
        // 리프노드를 하나씩 꺼내서 숫자 대입
        for(int i = 0; i < leafNode.size(); i++){
            int num = leafNode.get(i);
            count[num]--;
            
            for(int j = 1; j <= 3; j++){
                // 개수 * 1이 target - j 보다 작거나 같고, 개수 * 3이 target - j보다 클 경우 target값 만족 가능
                if(count[num] <= target[num] - j && count[num] * 3 >= target[num] - j){
                    target[num] -= j;
                    result.add(j);
                    break;
                }
            }
        }
        
        // List -> Array
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}