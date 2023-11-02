import java.util.*;

class Solution {
    
    static int[][] list;
    static int[] route;
    static int[] cnt;
    static ArrayList<Integer> ansList;
    
    public int[] solution(int[][] edges, int[] target) {
        int len = target.length;
        
        // 인접행렬로 저장. 길을 설정할 때 1부터 len까지 탐색하여 열릴 길을 설정해야 하기 때문에 배열구조가 나을것으로 판단함.
        list = new int[len + 1][len + 1];
        for(int i = 0; i < edges.length; i++) {
            list[edges[i][0]][edges[i][1]] = 1;
        }
        
        // 초기 길을 설정.
        route = new int[len + 1];
        for(int i = 1; i < len + 1; i++) {
            for(int j = 1; j < len + 1; j++) {
                if(list[i][j] == 1) {
                    route[i] = j;
                    break;
                }
            }
        }
        
        // 리프노드에 도달할 때 해당 번호 카운트.
        cnt = new int[len + 1];
        
        // 매번 시행마다 도달한 리프 번호를 순서대로 저장.
        ansList = new ArrayList<>();
        
        boolean isPossible = true;
        while(!check(target, cnt)) {
            // 현재 상태를 검사하고, ansList 마지막에 -1이 담겼다면 불가능한것.
            if(!ansList.isEmpty() && ansList.get(ansList.size() - 1) == -1) {
                isPossible = false;
                break;
            }
            doTree();
        }
        
        if(!isPossible) {
            int[] answer = {-1};
            return answer;
        }else {
            int[] answer = new int[ansList.size()];
            for(int i = 0; i < ansList.size(); i++) {
                int num = ansList.get(i);
                if(cnt[num] == 1) {
                    answer[i] = target[num - 1];
                }else {
                    // 1을 넣어도 되는 경우
                    if((target[num - 1] - 1) <= (cnt[num] - 1) * 3) {
                        answer[i] = 1;
                    }
                    // 2를 넣어도 되는 경우
                    else if((target[num - 1] - 2) <= (cnt[num] - 1) * 3) {
                        answer[i] = 2;
                    }
                    // 아니면 3
                    else {
                        answer[i] = 3;
                    }
                }
                target[num - 1] -= answer[i];
                cnt[num]--;
            }
            return answer;
        }
    
    }
    
    private static void doTree() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        
        while(!queue.isEmpty()) {
            int num = queue.poll();
            
            if(route[num] == 0) {
                cnt[num]++;
                ansList.add(num);
                return;
            }
            
            queue.add(route[num]);
            // 루트 재설정
            moveRoute(num);            
        }
    }
    
    private static void moveRoute(int num) {
        // 인접행렬을 현재번호+1 부터 돌면서 다음 길 열기
        for(int i = route[num] + 1; i < list[num].length; i++) {
            if(list[num][i] == 1) {
                route[num] = i;
                return;
            }
        }
        
        for(int i = 1; i <= route[num]; i++) {
            if(list[num][i] == 1) {
                route[num] = i;
                return;
            }
        }
    }
    
    private static boolean check(int[] target, int[] cnt) {
        for(int i = 0; i < target.length; i++) {
            // 타겟이 0인데 도착한 횟수가 1이상이면 불가능
            if(target[i] == 0 && cnt[i + 1] > 0) {
                ansList.add(-1);
                return false;
            }
            
            // 타겟이 0 이상인데
            if(target[i] > 0) {
                // 아직 도착한적없으면 계속 진행
                if(cnt[i + 1] == 0) {
                    return false;
                }
                // 도착한 횟수가 너무 많아서 1로 다 채워도 타겟보다 크면 불가능
                else if(target[i] / cnt[i + 1] < 1) {
                    ansList.add(-1);
                    return false;
                }
                // 3으로 다 채워도 타겟을 못채우면 계속 진행
                else if(target[i] / cnt[i + 1] > 3) {
                    return false;
                }else if(target[i] / cnt[i + 1] == 3 && target[i] % cnt[i + 1] > 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}