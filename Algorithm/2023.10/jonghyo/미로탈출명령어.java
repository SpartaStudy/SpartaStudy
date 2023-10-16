import java.util.*;

class Solution {
    static class Node {
		int r;
		int c;
		int dis;
		String order;
		public Node(int r, int c, int dis,String order) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.order = order;
		}
	}
	static int map[][];
	static int dr[] = {1,0,0,1};
	static int dc[] = {0,-1,1,0};
	static List<String> list = new ArrayList<>();
	static Queue<Node> q = new LinkedList<>();

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        q.add(new Node(x-1, y-1,0, ""));
        go(n,m,r-1,c-1,k);
        Collections.sort(list);
        if(list.size() == 0) {
            answer = "impossible";
        }else {
            answer = list.get(0);
        }
        return answer;
    }
    private static void go(int n, int m, int r, int c,int k) {
			Node nd = q.poll();
			if(nd.dis == k) {
				if(nd.r == r && nd.c == c) {
					list.add(nd.order);
					return;
				}
			}else if(nd.dis > k) {
				return;
			}
			for(int d = 0; d < 4; d++) {
				int nr = nd.r + dr[d];
				int nc = nd.c + dc[d];
				if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
					switch(d) {
					case 0: //하
						q.add(new Node(nr,nc,nd.dis + 1, nd.order+"d"));
						go(n,m,r,c,k);
						break;
					case 1: //좌
						q.add(new Node(nr,nc,nd.dis + 1, nd.order+"l"));
						go(n,m,r,c,k);
						break;
					case 2: //우
						q.add(new Node(nr,nc,nd.dis + 1, nd.order+"r"));
						go(n,m,r,c,k);
						break;
					case 3: //상
						q.add(new Node(nr,nc,nd.dis + 1, nd.order+"u"));
						go(n,m,r,c,k);
						break;
					}
				}
			}
	}
}