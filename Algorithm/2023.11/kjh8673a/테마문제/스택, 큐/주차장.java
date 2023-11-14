import java.util.*;
import java.io.*;

public class Main {
    static class ParkingLot {
        int[] spot;

        public ParkingLot(int size) {
            spot = new int[size];
        }

        public boolean isEmpty() {
            for (int i = 0; i < spot.length; i++) {
                if (spot[i] == 0) {
                    return true;
                }
            }

            return false;
        }

        public void enter(int num) {
            for (int i = 0; i < spot.length; i++) {
                if (spot[i] == 0) {
                    spot[i] = num;
                    return;
                }
            }
        }

        public int out(int num) {
            int price = 0;
            for (int i = 0; i < spot.length; i++) {
                if (spot[i] == num) {
                    price = costPerUnit[i] * car[num];
                    spot[i] = 0;
                    break;
                }
            }

            return price;
        }
    }

    static int[] costPerUnit;
    static int[] car;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        costPerUnit = new int[N];
        for (int i = 0; i < N; i++) {
            costPerUnit[i] = Integer.parseInt(br.readLine());
        }

        car = new int[M + 1];
        for (int i = 1; i < M + 1; i++) {
            car[i] = Integer.parseInt(br.readLine());
        }

        ParkingLot parkingLot = new ParkingLot(N);
        int ans = 0;
        Queue<Integer> waitingList = new LinkedList<>();
        for (int i = 0; i < 2 * M; i++) {
            int ops = Integer.parseInt(br.readLine());
            if (ops > 0) {
                waitingList.add(ops);
            } else {
                ans += parkingLot.out(Math.abs(ops));
            }

            while (parkingLot.isEmpty() && !waitingList.isEmpty()) {
                parkingLot.enter(waitingList.poll());
            }
        }

        System.out.println(ans);
    }

}