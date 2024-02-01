/**
 * BaekJoon_18115, 카드 놓기
 *  - 문제 분류: type
 */
const solution = () => {
  class Node {
    constructor(val) {
      this.val = val;
      this.prev = null;
      this.next = null;
    }
  }

  class LinkedList {
    constructor() {
      this.top = null;
      this.bottom = null;
      this.size = 0;
    }

    addFirst(num) {
      if (this.size === 0) {
        this.addWhenZero(num);
        return;
      }

      const node = new Node(num);
      node.next = this.top;
      if (this.size > 1) {
        this.top.next.prev = node;
      }
      this.top = node;
      this.size++;
    }

    addAtSecond(num) {
      if (this.size < 1) return;

      const node = new Node(num);
      node.next = this.top.next;
      node.prev = this.top;
      if (this.size > 1) {
        this.top.next.prev = node.next;
      } else {
        this.bottom = node;
      }
      this.top.next = node;
      this.size++;
    }

    addLast(num) {
      if (this.size === 0) {
        this.addWhenZero(num);
        return;
      }

      const node = new Node(num);
      node.prev = this.bottom;
      this.bottom.next = node;
      this.bottom = node;
      this.size++;
    }

    addWhenZero(num) {
      this.top = new Node(num);
      this.bottom = this.top;
      this.size++;
    }

    print() {
      const arr = [];
      let curr = this.top;
      while (!!curr) {
        arr.push(curr.val);
        curr = curr.next;
      }
      return arr.join(" ");
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/카드놓기.txt").toString().trim().split("\n");
  const N = +input[0];
  const techs = input[1].split(" ").map(Number);
  const list = new LinkedList();
  let pointer = 1;

  /* 메인 로직 */
  while (techs.length > 0) {
    const type = techs.pop();
    const number = pointer++;

    if (type === 1) tech01(number);
    else if (type === 2) tech02(number);
    else tech03(number);
  }

  /* 정답 반환 */
  return list.print();

  // 기술 1번
  function tech01(num) {
    list.addFirst(num);
  }

  // 기술 2번
  function tech02(num) {
    list.addAtSecond(num);
  }

  // 기술 3번
  function tech03(num) {
    list.addLast(num);
  }
};

console.log(solution());
