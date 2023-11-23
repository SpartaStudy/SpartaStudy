import java.util.*;
/*
유니온 파인드로 구현
50*50 1차원배열로 변환
*/
class Solution {
    String[] table = new String[50 * 50];
    int[] parent = new int[50 * 50];
    List<String> list = new ArrayList<>();

    public String[] solution(String[] commands) {

        initParent();

        for (int i = 0; i < commands.length; i++) {
            String[] tokens = commands[i].split(" ");
            String op = tokens[0];
            if (op.equals("UPDATE")) {
                if (tokens.length == 4) {
                    int r = Integer.parseInt(tokens[1])-1;
                    int c = Integer.parseInt(tokens[2])-1;
                    String val = tokens[3];
                    update(r, c, val);
                } else {
                    String val1 = tokens[1];
                    String val2 = tokens[2];
                    update(val1, val2);
                }
            } else if (op.equals("MERGE")) {
                int r1 = Integer.parseInt(tokens[1])-1;
                int c1 = Integer.parseInt(tokens[2])-1;
                int r2 = Integer.parseInt(tokens[3])-1;
                int c2 = Integer.parseInt(tokens[4])-1;
                merge(r1, c1, r2, c2);
            } else if (op.equals("UNMERGE")) {
                int r = Integer.parseInt(tokens[1])-1;
                int c = Integer.parseInt(tokens[2])-1;
                unmerge(r, c);
            } else if (op.equals("PRINT")) {
                int r = Integer.parseInt(tokens[1])-1;
                int c = Integer.parseInt(tokens[2])-1;
                printCell(r, c);
            }
        }
        String[] ans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public void initParent() {
        for (int i = 0; i < 50 * 50; i++) {
            parent[i] = i;
        }
    }

    public int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }

    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parent[pb] = pa;
        return true;
    }

    public void update(int r, int c, String val) {//
        int p = find(r * 50 + c);
        table[p] = val;
    }

    public void update(String val1, String val2) {//
        //여기에서 한번 초기화 해야되네
        for (int i = 0; i < 50 * 50; i++) {
            int p = find(i);
            table[i] = table[p];
            if (table[i] != null && table[i].equals(val1)) {
                table[i] = val2;
            }
        }
    }

    public void merge(int r1, int c1, int r2, int c2) {//
        int pa = find(r1 * 50 + c1);
        int pb = find(r2 * 50 + c2);

        if (pa == pb) return;

        if (table[pa] == null && table[pb] != null) {
            table[pa] = table[pb];
        }

        union(pa, pb);

    }

    public void unmerge(int r, int c) {//
        int p = find(r * 50 + c);
        String val = table[p];
        List<Integer> tmp = new ArrayList<>();
      //바로 초기화시에 망가져버림
        for (int i = 0; i < 50 * 50; i++) {
            if (find(i) == p) {
                tmp.add(i);
            }
        }
        tmp.stream().forEach(i -> {
            parent[i] = i;
            table[i] = null;
        });
        table[r * 50 + c] = val;
    }

    public void printCell(int r, int c) {//
        int p = find(r * 50 + c);

        if (table[p] == null) {
            list.add("EMPTY");
        } else {
            list.add(table[p]);
        }
    }
}
