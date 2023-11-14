/**
 * BaekJoon_16120, PPAP
 *  - 문제 분류: 스택
 */
const solution = () => {
  // PPAP class
  class stack_ppap {
    constructor() {
      this.arr = [];
      this.size = 0;
      this.top = -1;
      this.PPAP = ["P", "A", "P", "P"]; // reversed
    }

    // 문자 추가
    push(item) {
      this.arr.push(item);
      this.size++;
      this.top++;

      // 4글자 이상이고 PPAP를 충족하면 스택에서 PPAP 제거하고 P추가
      if (this.size >= 4) {
        for (let i=0; i<4; i++) {
          if (this.PPAP[i] !== this.arr[this.top - i]) return;
        }
        for (let i=0; i<4; i++) this.pop();
        this.push("P");
      }
    } 

    pop() {
      this.arr.pop();
      this.size--;
      this.top--;
    }

    peek() {
      if (this.size <= 0) return null;
      return this.arr[this.top];
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/16120.txt").toString().trim();
  const len = input.length;
  const ppap = new stack_ppap();

  /* 메인 로직 */
  for (let i=0; i<len; i++) {
    const char = input.charAt(i);
    ppap.push(char);
  }

  /*  */
  return ppap.size === 1 && ppap.peek() === "P" ? "PPAP" : "NP";
}

console.log(solution());