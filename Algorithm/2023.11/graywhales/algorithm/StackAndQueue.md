# 스택과 큐

## 스택
### abstract
스택(Stack)은 쌓아 올린다는 것을 의미하는 자료구조이다.
후입선출 (LIFO, Last-In-First-Out) 구조로, 가장 마지막에 삽입된 자료가 가장 먼저 삭제된다는 구조를 가진다.

### 활용 예시
- 웹 브라우저 방문기록 (뒤로가기) : 가장 나중에 열린 페이지 부터 다시 보여준다.
- 역순 문자열 만들기 : 가장 나중에 입력된 문자부터 출력한다.
- 실행 취소(undo) : 가장 나중에 실행된 것부터 실행을 취소한다.
- 후위 표기법 계산
- 수식의 괄호 검사 (연산자 우선순위 표현을 위한 괄호 검사)

### 구현 (Java)
배열로 직접 구현하게 되면 다음과 같다.
```java
public class ArrayStack {
    int top;
    int size;
    int [] stack;
    public ArrayStack(int size) {
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    public void push(int item) {
        stack[++top] = item;
    }
    public int pop() {
        int pop = stack[top];
        stack[top--] = 0;
        return pop;
    }
    public int peek() {
        return(stack[top]);
    }
    public int size() {
		return top+1;
	}
}
```
### Java 라이브러리 Stack
```java
import java.util.Stack;

Stack<Interger> stack = new Stack<>();
stack.push(1);
stack.push(2);
stack.push(3);
stack.pop(); // 3출력
stack.pop(); // 2출력
stack.pop(); // 1출력

```

## 큐

### abstract
Queue의 사전적 의미는 줄,혹은 줄을 서서 기다리는 것을 의미한다. 따라서 줄을 서서 기다린 사람들을 처리하는 것과 같이 선입선출 (FIFO, First In First Out) 방식의 자료구조로 먼저 삽입된 자료가 먼저 삭제된다.


### 활용예시 
- 우선순위가 같은 작업 예약
- 은행 업무
- 콜센터 고객 대기시간
- 프로세스 관리
- 너비 우선 탐색(BFS) 구현
- 캐시(Cache) 구현

### 구현(Java)
배열로 구현 poll 할때 front값을 계속하여 증가시키므로 배열의 낭비 발생, 이를 해결하기 위해 원형 버퍼를 주로 사용함
```java
public class ArrayQueue {
    int size;
    int front; 
    int rear;  
    int [] queue;
    public ArrayQueue(int size) {
	    this.size=size;
        front = rear = 0;
        queue = new int[size];
    }
    public boolean isEmpty() { 
        return front == rear;
    }

	public boolean isFull() {
        if(rear == MAX-1) {
            return true;
        }else 
            return false;
    }

    public int size() { 
        return front-rear;
    }
    public void offer(int value) {
        if(isFull()) {
            return;
        }
        queue[rear++] = value;
    }
    public int poll() {
        if(isEmpty()) {
            return -1;
        }
        return queue[front++];
    }
    public int peek() {
        if(isEmpty()) {
            return -1;
        }
        return queue[front];
    }
}
```


### Java 라이브러리 Queue
```java
import java.util.Queue;
import java.util.ArrayDeque;

Queue<Integer> queue = new ArrayDeque<>();
queue.offer(1);
queue.offer(2);
queue.offer(3);
queue.poll();// 1출력
queue.poll();// 2출력
queue.poll();// 3출력
```

### 우선순위큐
우선순위 큐는 말 그대로 원소들에게 우선순위를 매겨서 넣을 때의 순서와 상관없이 뺄 때에는 우선순위가 높은 원소부터 빼내는 자료구조. 대표적인 예시로 heap이 있다.


## 데크
### abstract
Deque : Double Ended Queue 
일반적인 큐는 뒤에서만 삽입이 가능하고 앞에서만 인출이 가능한 데 비해, 데크는 양쪽에서 모두 삽입/인출이 가능하다.
스택과 큐의 특징을 모두 갖고 있다.
### 구현 
데크의 구현은 일반적으로 이중 연결 리스트로 구현하며, 양쪽으로 head와 tail 두 포인터를 갖고 있다가 새로운 아이템이 추가될 때마다 앞쪽 또는 뒤쪽으로 연결시켜 준다.
