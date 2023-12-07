/*
비트연산자 + 분할정복
비트연산할때 연산순서와 범위를 조심하자
*/
public class Solution {
    int n;

    public int[] solution(long[] numbers) {
        n = numbers.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            if (numbers[i] == 1){ // 1은 되니까 추가
                answer[i] = 1;
            }else{
                answer[i] = check(numbers[i]);
            }
        }
        return answer;
    }
    private int check(long number) {
        for (int i = 5; i > 0; i--) {
            int root = (1<<i)-1;// mid값이 될 수 있는 bit 자릿수는 2^n -1 이다. 0,1,3,7,15,31
            
            if(i!=5&&number>=((long)1<<(root*2+1))) break; // number가 2^(mid*2+1) 보다 클 수는 없어 i=5일때는 long값 넘어가서 곤란
            
            if ((number & ((long)1<<root)) != 0 ){
                if(dc(number, i, root, true)) return 1;
            }
            
        }
        return 0;
    }

    private boolean dc(long number, int r, int now, boolean root) {//분할정복
        
        if (r == 0) //leaf는 신경안씀
            return true;
            
        int left = now + (1 << (r - 1)); //왼쪽 노드
        int right = now - (1 << (r - 1)); //오른쪽 노드
        
        boolean leftRoot = (number & (long)1 << left) != 0;
        boolean rightRoot = (number & (long)1 << right) != 0;

        if (!root && (leftRoot || rightRoot)) { //부모가 0인데 자식이 1이면 false
            return false;
        }

        return dc(number, r - 1, left, leftRoot) && dc(number, r - 1, right, rightRoot);

    }

}

