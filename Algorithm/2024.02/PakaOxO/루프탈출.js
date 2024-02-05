/**
 * CodeTree_루프 탈출
 *  - 문제 분류: 시뮬레이션
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/루프탈출.txt").toString().trim().split("\n");
  const N = +input[0];
  const arr = [0, ...input.slice(1).map(Number)];
  const v = Array.from({ length: N + 1 }, () => false);
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    const flag = dfs(i);
    if (flag === 0) answer++;
  }

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(x) {
    if (arr[x] <= 0) return arr[x];
    if (v[x]) return -1;

    v[x] = true;
    arr[x] = dfs(arr[x]);
    return arr[x];
  }
};

console.log(solution());
