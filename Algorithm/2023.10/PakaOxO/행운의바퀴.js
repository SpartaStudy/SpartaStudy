/**
 * BaekJoon_2840, 행운의 바퀴
 *  - 문제 분류: 자료구조(연결 리스트)
 */
const solution = () => {
  class LinkedList {
    constructor() {
      this.size = 0;
      this.head = null;
      this.tail = null;
    }

    offer(node) {
      if (this.size === 0) {
        this.head = node;
        this.tail = node;
        node.next = node;
        node.prev = node;
        this.size++;
        return;
      }

      this.tail.next = node;
      this.head.prev = node;
      node.next = this.head;
      node.prev = this.tail;
      this.tail = node;
      this.size++;
    }

    rotate(cnt) {
      while (cnt > 0) {
        this.head = this.head.next;
        cnt--;
      }
    }

    findWheel() {
      let pointer = this.head;
      const arr = [];
      for (let i = 0; i < this.size; i++) {
        arr.push(pointer.char);
        pointer = pointer.prev;
      }

      return arr.join("");
    }
  }

  class Node {
    constructor(char) {
      this.char = char;
      this.next = null;
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/2840.txt").toString().split("\n");
  const [N, K] = input[0].split(" ").map((item) => +item);
  const list = new LinkedList();
  const v = Array.from({ length: 26 }, () => false);
  let flag = true;

  /* 메인 로직 */
  for (let i = 0; i < N; i++) {
    list.offer(new Node("?"));
  }

  for (let i = 1; i <= K; i++) {
    let [cnt, c] = input[i].split(" ");
    c = c.trim();
    list.rotate(+cnt);

    if (list.head.char === "?") {
      const key = c.charCodeAt() - 65;
      if (v[key]) {
        flag = false;
        break;
      }
      v[key] = true;
      list.head.char = c;
    } else {
      if (list.head.char !== c) {
        flag = false;
        break;
      }
    }
  }

  /* 바퀴 반환 */
  return !flag ? "!" : list.findWheel();
};

console.log(solution());
