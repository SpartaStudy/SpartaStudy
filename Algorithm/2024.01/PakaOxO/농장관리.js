/**
 * BaekJoon_1245, 농장관리
 *  - 문제 분류: 그래프 탐색
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/농장관리.txt").toString().trim().split("\n");
  const [N, M] = input[0].split(" ").map(Number);
  const drc = [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
    [1, 1],
    [-1, -1],
    [-1, 1],
    [1, -1],
  ];
  const map = [];
  const v = [];
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    map.push(input[i].split(" ").map(Number));
    v.push(Array.from({ length: M }, () => false));
  }

  // 연결된 능선을 찾는 경우의 수: N * M * 8 -> 능선을 찾으면서 주변에 자신보다 높은 애가 있는지 알 수도 있음
  // N: 100, M : 70
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (v[i][j]) continue;
      bfs(i, j);
    }
  }

  /* 정답 반환 */
  return answer;

  // bfs
  function bfs(r, c) {
    const q = [[r, c]];
    let flag = true;
    while (q.length > 0) {
      const [cr, cc] = q.shift();
      for (let i = 0; i < drc.length; i++) {
        const [nr, nc] = [cr + drc[i][0], cc + drc[i][1]];
        if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
        if (map[nr][nc] > map[cr][cc]) {
          flag = false;
          continue;
        }
        if (v[nr][nc] || map[nr][nc] < map[cr][cc]) continue;

        q.push([nr, nc]);
        v[nr][nc] = true;
      }
    }

    if (flag) answer++;
  }
};

console.log(solution());
