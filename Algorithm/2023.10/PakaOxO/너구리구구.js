/**
 * BaekJoon_18126, 너구리 구구
 *  - 문제 분류: 트리, 그래프 탐색
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/18126.txt").toString().split("\n");
  const N = +input[0];
  const adjList = Array.from({ length: N + 1 }, () => []);
  let answer = 0;

  /* 메인 로직 */
  for (let i=1; i<N; i++) {
    const [from, to, dist] = input[i].split(" ").map((item) => +item);
    adjList[from].push([to, dist]);
    adjList[to].push([from, dist]);
  }

  bfs(1);

  /* 최대 거리 반환 */
  return answer;

  // bfs
  function bfs(rootIdx) {
    const queue = [[rootIdx, 0]];
    const INF = Number.MAX_VALUE;
    const dist = Array.from({ length: N + 1 }, () => INF);

    while (queue.length > 0) {
      const curr = queue.shift();
      if (curr[1] > answer) answer = curr[1];

      for (const next of adjList[curr[0]]) {
        if (dist[next[0]] <= curr[1] + next[1]) continue;
        dist[next[0]] = curr[1] + next[1];
        queue.push([next[0], curr[1] + next[1]]);
      }
    }
  }
}

console.log(solution());