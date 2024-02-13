/**
 * CodeTree_괄호 쌍의 개수
 *  - 문제 분류: LR Technique
 */
const solution = () => {
  /* 변수 관리 */
  const braces = require("fs").readFileSync("./dev/stdin/괄호쌍의개수.txt").toString().trim();
  const len = braces.length;
  let [L, R] = [0, 0];
  let answer = 0;

  /* 메인 로직 */
  for (let i = 0; i < len; i++) {
    braces.charAt(i) === "(" ? L++ : R++;
  }

  if (len % 2 > 0) return 0;
  if (L === R) return 0;
  if (L > R && braces.charAt(len - 1) === "(") return 0;
  if (R > L && braces.charAt(0) === ")") return 0;
  console.log(L, R);

  let count = 0;
  if (L > R) {
    let pointer = len - 1;
    for (let i = len - 1; i >= 0; i--) {
      const flag = braces.charAt(i) === ")";
      flag ? count++ : count--;
      if (count < 0) {
        while (pointer > i) {
          if (braces.charAt(pointer) === "(") {
            answer++;
          }
          pointer--;
        }
      }
    }
  } else if (R > L) {
    let pointer = 0;
    for (let i = 0; i < len; i++) {
      const flag = braces.charAt(i) === "(";
      flag ? count++ : count--;
      if (count < 0) {
        while (pointer < i) {
          if (braces.charAt(pointer) === ")") {
            answer++;
          }
          pointer++;
        }
      }
    }
  }

  /* 정답 반환 */
  return answer;
};

console.log(solution());
