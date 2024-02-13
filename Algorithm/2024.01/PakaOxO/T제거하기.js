/**
 * CodeTree_T 제거하기
 *  - 문제 분류: 문자열, 스택
 */
const solution = () => {
  /* 변수 관리 */
  const [S, T] = require("fs").readFileSync("./dev/stdin/T제거하기.txt").toString().trim().split("\n");
  const stack = [];
  let top = -1;
  const tLen = T.length;
  let subPointer = tLen - 1;

  /* 메인 로직 */
  for (let i = 0; i < S.length; i++) {
    const char = S.charAt(i);
    stack.push(char);
    top++;

    let flag = false;
    while (top >= 0 && subPointer >= 0) {
      if (stack[top] !== T.charAt(subPointer)) break;
      subPointer--;
      top--;

      if (subPointer < 0) flag = true;
    }

    if (flag) {
      for (let j = 0; j < tLen; j++) stack.pop();
    }
    top = stack.length - 1;
    subPointer = tLen - 1;
  }

  /* 정답 반환 */
  return stack.join("");
};

console.log(solution());
