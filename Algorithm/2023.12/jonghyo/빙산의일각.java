import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		//현재 위치
		int idx;
		//현재 높이
		int h;
		public Node(int idx, int h) {
			this.idx = idx;
			this.h = h;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			//더 큰 순으로 정렬하기 위해 사용.
			return o.h - this.h;
		}

	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//빙산의 개수
		int N = Integer.parseInt(br.readLine());
		int ice[] = new int[N];
		List<Node> list = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			ice[i] = Integer.parseInt(br.readLine());
			list.add(new Node(i,ice[i]));
		}
		
		Collections.sort(list);
		//방문체크를 해줄 빙산입니다.
		boolean check[] = new boolean[N];
		
		int cnt = 0;
		int dir = 0;
		int height = 0;
		
		boolean left = false;
		boolean right = false;
		int answer = 0;
		//for문을 돌아서 list위치에 있는거 뽑은 후
		//현재 위치 양옆에 빙산이 없으면 +1
		//만약 양옆에 빙산이 있으면 그냥 그대로.
		//방문했으니 visited true로 변경.
		for(int i = 0; i < N; i++) {
			left = false;
			right = false;
			//현재 위치 뽑고, 
			dir = list.get(i).idx;
			height = list.get(i).h;
			check[dir] = true;
			//만약 왼쪽이 이미 방문이 되었다면?
			if(dir-1 >= 0 && check[dir-1]) {
				//left true로 변경.
				left = true;
			}
			//오른쪽이 이미 방문이 되어있다면?
			if(dir + 1 < N && check[dir+1]) {
				right = true;
			}
			
			//둘다 true라면?
			if(left && right) {
				//1 0 1 이 1 1 1로 이어지니 1개가 줄어드니 -1.
				cnt--;
			//둘다 false라면?
			}else if(!(left || right)) {
				cnt++;
			}
			//빙산의 개수 계속 최대값 찾기.
			//내 다음 list의 위치에 빙하의 높이 봐야함.
			if(i < N-1 && list.get(i+1).h != height) {
				answer = Math.max(answer, cnt);				
			}
		}
		
		System.out.println(answer);
	}
}
