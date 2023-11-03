import java.util.*;

class Solution {
    public int[] solution(int[][] edges, int[] target) {
        int[] answer = {};
        //자식들을 담아줄 List배열 만들기.
		List <Integer>[]li = new ArrayList[edges.length+1];
		for(int i = 0; i <= edges.length; i++) {
			li[i] = new ArrayList<>();
		}
		//현재 부모가 가르키고있는 부분을 확인해주는 배열하나 생성하기.
		int arr[] = new int[edges.length+1];
		for(int i = 0; i < edges.length; i++) {
			//edges[i][0] = 부모번호 edges[i][1] = 자식번호
			//즉 li의 부모번호에 해당하는 부분에 자식번호 추가해주기.
			li[edges[i][0]].add(edges[i][1]);
		}
		//1부터 자식의 개수 파악하기
		//1개면 0으로 계속 그자리에 있게 하기
		//2개 이상이면 숫자를 주어 변환하게 하기.
		//마지막 리프노드면 -1을 주기.
		for(int i = 1; i < li.length; i++) {
			if(li[i].size() == 0) {
				arr[i] = -2;
			}else if(li[i].size() == 1) {
				arr[i] = -1;
			}else {
				arr[i]  = 0;
			}
			//그러고 마지막에 내부 정렬해주기.
			Collections.sort(li[i]);
		}
		//타겟에 해당하는 수 찾기.
		boolean flag = true;
		int num = 0;
		while(flag) {
			int cnt = 1;
			//현재 넣어야 하는 숫자 찾기.
			while(true) {
				if(li[cnt].size() == 0) {
					//사이즈가 0이면 자식이 없으므로 리프노드 확인 while문 끝내.
					num = cnt;
					break;
				}else if(li[cnt].size() == 1) {
					//자식이 1개 있으므로 cnt를 자식으로 갱신후 다시 while문
					cnt = li[cnt].get(0);
				}else {
					//2개 이상이면 길이 있는 애로 가기.
					int beforecnt = cnt;
					cnt = li[cnt].get(arr[cnt]);
					//다음차례로 변경해주기.
					arr[beforecnt]++;
					if(arr[beforecnt] == li[beforecnt].size())
						arr[beforecnt] = 0;
				}
			}
			//숫자 뭐 넣을지 생각하기.
		}
        return answer;
    }
}