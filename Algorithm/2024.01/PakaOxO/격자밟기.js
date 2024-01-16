/**
 * CodeTree_격자 밟기
 *  - 문제 분류: 백트래킹
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/격자밟기.txt").toString().trim().split("\n");
  const N = 5;
  const K = +input[0];
  const map = Array.from({ length: N + 1 }, () => Array.from({ length: N + 1 }, () => 0));
  const drc = [
    [0, 1],
    [1, 0],
    [-1, 0],
    [0, -1],
  ];
  let answer = 0;

  /* 메인 로직 */
  map[1][1] = 1;
  map[N][N] = 1;
  for (let i = 1; i <= K; i++) {
    const [r, c] = input[i].split(" ").map(Number);
    map[r][c] = 2;
  }
  dfs(1, 1, N, N, false, 2);

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(r1, c1, r2, c2, turn, count) {
    const flag = count >= N * N - K;
    if (flag) {
      if (Math.abs(r2 - r1) + Math.abs(c2 - c1) === 1) answer++;
      return;
    }

    for (let i = 0; i < drc.length; i++) {
      let [nr, nc] = [(turn ? r2 : r1) + drc[i][0], (turn ? c2 : c1) + drc[i][1]];
      if (nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc] > 1) continue;

      if (map[nr][nc] === 1) continue;
      map[nr][nc] = 1;
      if (turn) {
        dfs(r1, c1, nr, nc, !turn, count + 1);
      } else {
        dfs(nr, nc, r2, c2, !turn, count + 1);
      }
      map[nr][nc] = 0;
    }
  }
};

console.log(solution());
