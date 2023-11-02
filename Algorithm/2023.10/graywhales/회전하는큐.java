import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 회전하는큐 {
    static class Node{
        int num;
        Node before;
        Node next;

        public Node(int num, Node before, Node next) {
            this.num = num;
            this.before = before;
            this.next = next;
        }
    }
    static Node header;
    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());

        header=new Node(0,null,null);
        Node before=header;
        Node next=header; //n 크기가 1일때 null로 만들어두면 밑에 로직에서 next찾을때 NullPointError 발생
        for(int i=1;i<n;i++){
            next=new Node(i,before,null);
            before.next=next;
            before=next;
        }
        next.next=header;
        header.before=next;


        st=new StringTokenizer(br.readLine());
        int pick;
        int count=0;
        for(int i=0;i<m;i++){
            pick=Integer.parseInt(st.nextToken())-1;
            count+=findHeaderAndPoll(pick);
        }
        System.out.println(count);

    }

    private static int findHeaderAndPoll(int pick) {
        if(header.num==pick){
            poll();
            return 0;
        }
        int leftCounter=0;
        int rightCounter=0;
        Node now=header;
        while(now.num!=pick){
            now=now.next;
            leftCounter++;
        }
        now=header;
        while(now.num!=pick){
            now=now.before;
            rightCounter++;
        }

        if(leftCounter<=rightCounter){
            while(header.num!=pick){
                header=header.next;
            }
            poll();
            return leftCounter;
        }else{
            while(header.num!=pick){
                header=header.before;
            }
            poll();
            return rightCounter;
        }
    }

    private static void poll() {
        Node before=header.before;
        header=header.next;
        header.before=before;
        before.next=header;
    }
}
