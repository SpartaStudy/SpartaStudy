import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class 행운의바퀴 {
    /*
        단방향 링크드 리스트를 만들고 뺄때 stack 사용, set 사용해서 중복검사
     */
    static class Node{
        char character;
        Node next;

        public Node(char character, Node next) {
            this.character = character;
            this.next = next;
        }
    }
    static Node header;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int n=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());

        header=new Node('?',null);
        Node next=header;
        for(int i=1;i<n;i++){
            next.next=new Node('?',null);
            next=next.next;
        }
        next.next=header;



        int s;
        char character;
        boolean flag=true;
        for(int i=0;i<k;i++){
            st=new StringTokenizer(br.readLine());
            s=Integer.parseInt(st.nextToken());
            character=st.nextToken().charAt(0);
            for(int j=0;j<s;j++){
                header=header.next;
            }
            if(header.character=='?'||header.character==character){
                header.character=character;
            }else{
                flag=false;
            }
        }
        StringBuilder sb=new StringBuilder();
        if(flag){
            Stack<Character> stk=new Stack<>();
            Set<Character> set=new HashSet<>();
            for(int i=0;i<n;i++){
                header=header.next;
                stk.add(header.character);
                if(header.character!='?'&&set.contains(header.character)) {
                    flag=false;
                }else{
                    set.add(header.character);
                }
            }
            if(flag)
                while(!stk.isEmpty()){
                    sb.append(stk.pop());
                }
            else{
                sb.append("!");
            }
        }else{
            sb.append("!");
        }
        System.out.println(sb);

    }
}
