/**
 * BaekJoon_1021, 회전하는 큐
 *  - 문제 분류: 자료구조(연결 리스트)
 */
const solution = () => {
  class LinkedList {
    constructor() {
      this.head = null;
      this.tail = null;
      this.size = 0;
    }

    offer(node) {
      if (this.size === 0) {
        this.head = node;
        this.tail = node;

        this.head.front = node;
        this.head.rear = node;
        this.size++;
        return;
      }

      this.tail.rear = node;
      this.head.front = node;
      node.rear = this.head;
      node.front = this.tail;

      this.tail = node;
      this.size++;
    }

    poll() {
      if (this.size === 0) return null;
      if (this.size === 1) {
        this.head = this.rear = null;
        return this.head;
      }
      const head = this.head;
      const newHead = head.rear;

      newHead.front = head.front;
      head.front.rear = newHead;
      this.head = newHead;
      this.size--;

      head.front = null;
      head.rear = null;
      return head;
    }

    find(idx) {
      let pointer = this.head;
      let count = 0;
      while (pointer.idx !== idx) {
        count++;
        pointer = pointer.rear;
      }

      return Math.abs(this.size - count) < count ? count - this.size : count;
    }

    rotate(disp) {
      while (disp !== 0) {
        if (disp > 0) {
          this.tail = this.head;
          this.head = this.head.rear;
          disp--;
        } else {
          this.head = this.tail;
          this.tail = this.tail.front;
          disp++;
        }
      }
    }
  }

  class Node {
    constructor(idx) {
      this.idx = idx;
      this.front = null;
      this.rear = null;
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/1021.txt").toString().split("\n");
  const [N, M] = input[0].split(" ").map((item) => +item);
  const pos = input[1].split(" ").map((item) => +item);
  const list = new LinkedList();
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    const node = new Node(i);
    list.offer(node);
  }

  for (let i = 0; i < M; i++) {
    const diff = list.find(pos[i]);
    answer += Math.abs(diff);

    list.rotate(diff);
    list.poll();
  }

  /* 최소 횟수 반환 */
  return answer;
};

console.log(solution());
