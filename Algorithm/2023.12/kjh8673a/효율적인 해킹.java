import java.io.*;
import java.util.*;

public class Main {
    static int N, M, discoveredIndex, SCC_index;
    static int[] discoveredOrder, SCC_groupNumberArray;
    static ArrayList<ArrayList<Integer>> singleNodegraph;
    static ArrayList<TreeSet<Integer>> SCC;
    static ArrayList<Set<Integer>> SCC_groupGraph;
    static boolean[] alreadyInSCC, visitedGroup;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        singleNodegraph = new ArrayList<>();
        SCC = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            singleNodegraph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            singleNodegraph.get(b).add(a);
        }

        // SCC 만들기
        discoveredOrder = new int[N + 1];
        discoveredIndex = 0;
        SCC_index = 0;
        SCC_groupNumberArray = new int[N + 1];
        alreadyInSCC = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            if (discoveredOrder[i] == 0) {
                makeSCC(i);
            }
        }

        // SCC끼리 연결시키기
        SCC_groupGraph = new ArrayList<>();
        for (int i = 0; i < SCC_index; i++) {
            SCC_groupGraph.add(new HashSet<>());
        }
        int[] SCC_groupInDegree = new int[SCC_index];
        for (int i = 1; i < N + 1; i++) {
            for (int next : singleNodegraph.get(i)) {
                if (SCC_groupNumberArray[i] != SCC_groupNumberArray[next]
                        && !SCC_groupGraph.get(SCC_groupNumberArray[i]).contains(SCC_groupNumberArray[next])) {
                    SCC_groupInDegree[SCC_groupNumberArray[next]]++;
                    SCC_groupGraph.get(SCC_groupNumberArray[i]).add(SCC_groupNumberArray[next]);
                }
            }
        }

        // SCC 연결 그래프를 이용해 해킹할 수 있는 개수 세기
        int[] SCC_groupCount = new int[SCC_index];
        int maxHacked = 0;
        for (int i = 0; i < SCC_index; i++) {
            if (SCC_groupInDegree[i] == 0) {
                visitedGroup = new boolean[SCC_index];
                SCC_groupCount[i] = countingGroup(i);
                maxHacked = Math.max(maxHacked, SCC_groupCount[i]);
            }
        }

        // 최댓값인 그룹의 요소 출력하기
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SCC_index; i++) {
            if (SCC_groupCount[i] == maxHacked) {
                for (Integer node : SCC.get(i)) {
                    sb.append(node + " ");
                }
            }
        }

        System.out.println(sb);
    }

    private static int countingGroup(int x) {
        visitedGroup[x] = true;
        int count = SCC.get(x).size();
        for (int next : SCC_groupGraph.get(x)) {
            if (!visitedGroup[next]) {
                count += countingGroup(next);
            }
        }

        return count;
    }

    private static int makeSCC(int x) {
        discoveredOrder[x] = ++discoveredIndex;
        stack.push(x);

        int root = discoveredOrder[x];
        for (int next : singleNodegraph.get(x)) {
            if (discoveredOrder[next] == 0) {
                root = Math.min(root, makeSCC(next));
            } else if (!alreadyInSCC[next]) {
                root = Math.min(root, discoveredOrder[next]);
            }
        }

        if (root == discoveredOrder[x]) {
            SCC.add(new TreeSet<>());
            while (!stack.isEmpty()) {
                int node = stack.pop();
                SCC.get(SCC_index).add(node);
                SCC_groupNumberArray[node] = SCC_index;
                alreadyInSCC[node] = true;
                if (node == x) {
                    break;
                }
            }
            SCC_index++;
        }

        return root;
    }

}