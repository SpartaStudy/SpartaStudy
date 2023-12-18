/**
 * CodeTree_Carry 피하기
 *  - 문제 분류: 백트래킹
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/Carry피하기.txt").toString().trim().split("\n");
  const N = +input[0];
  const arr = input.slice(1).map(Number);
  let answer = 0;

  /* 메인 로직 */
  dfs(0, 0, 0, 0);

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(depth, start, sum, num) {
    if (N - start + depth <= answer) return;
    if (depth === 1) {
      sum += num;
    } else if (depth > 1) {
      const flag = check(sum, num);
      if (!flag) return;
      sum += num;
      answer = Math.max(answer, depth);
    }

    for (let i = start; i < N; i++) {
      dfs(depth + 1, i + 1, sum, arr[i]);
    }
  }

  // check
  function check(sum, num) {
    const len = Math.min((sum + "").length, (num + "").length);
    for (let i = 0; i < len; i++) {
      const [a, b] = [sum % 10, num % 10];
      if (a + b >= 10) return false;

      sum = Math.floor(sum / 10);
      num = Math.floor(num / 10);
    }
    return true;
  }
};

console.log(solution());
