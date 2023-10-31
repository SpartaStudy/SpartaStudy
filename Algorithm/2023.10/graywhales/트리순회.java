import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    트리순회
    Root를 중점으로
    전위순회(PreOrderTraversal) Root - Left - Right
    중위순회(InOrderTraversal) Left - Root - Right
    후위순회(PostOrderTraversal) Left - Right - Root
 */
public class 트리순회 {
    static class Node{
        char character;
        Node left;
        Node right;

        public Node(char character, Node left, Node right) {
            this.character = character;
            this.left = left;
            this.right = right;
        }
    }
    static Node[] tree=new Node[26];
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        String input;
        for(int i=0;i<26;i++){
            tree[i]=new Node((char)('A'+i),null,null);
        }
        int startRoot=-1;
        int root,left,right;
        for(int i=0;i<n;i++){
            input=br.readLine();
            root=input.split(" ")[0].charAt(0)-'A';
            left=input.split(" ")[1].charAt(0)-'A';
            right=input.split(" ")[2].charAt(0)-'A';
            if(i==0) startRoot=root;
            if(left>=0 && left<26) tree[root].left=tree[left];
            if(right>=0 && right<26) tree[root].right=tree[right];
        }

        preOrderTraversal(tree[startRoot]);
        sb.append("\n");
        inOrderTraversal(tree[startRoot]);
        sb.append("\n");
        postOrderTraversal(tree[startRoot]);

        System.out.println(sb);
    }
    private static void preOrderTraversal(Node root) {
        if(root==null) return;
        sb.append(root.character);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    private static void inOrderTraversal(Node root) {
        if(root==null) return;
        inOrderTraversal(root.left);
        sb.append(root.character);
        inOrderTraversal(root.right);
    }

    private static void postOrderTraversal(Node root) {
        if(root==null) return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        sb.append(root.character);
    }




}
