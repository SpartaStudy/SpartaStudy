import java.io.*;

public class Main {
    static int N, answer;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        answer = 0;
        boolean[] visited = new boolean[1_000_001];
        for(int i = 0; i < N; i++) {
            if(visited[arr[i]]) {
                continue;
            }

            visited[arr[i]] = true;
            countContinuousNumbers(arr[i]);
        }

        System.out.println(answer);
    }

    private static void countContinuousNumbers(int num) {
        int prev = -1;
        int count = 0;
        for(int i = 0; i < N; i++) {
            if(arr[i] == num) {
                continue;
            }else if(prev == arr[i]) {
                count++;
            }else {
                prev = arr[i];
                count = 1;
            }
            answer = Math.max(answer, count);
        }
    }
}