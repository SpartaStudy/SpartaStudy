/**
 * Programmers_미로 탈출 명령어
 *  - 문제 분류: 그래프 탐색, 백트래킹
 */
const solution = (n, m, x, y, r, c, k) => {
  /* 변수 관리 */
  const drc = [
    [1, 0],
    [0, -1],
    [0, 1],
    [-1, 0],
  ]; // d, l, r, u(알파벳 순서)
  const dir = ["d", "l", "r", "u"];
  let answer = null;

  /* 메인 로직 */
  x--, y--, r--, c--; // 입력값에서 -1씩
  bfs(x, y, 0, ""); // 시작점부터 bfs 탐색

  /* 정답 반환 */
  return answer || "impossible";

  /**
   * dfs
   */
  function bfs(sr, sc) {
    const queue = [[sr, sc, 0, ""]];
    const MAX = 2500; // 최대 이동가능 거리는 k의 최대값(~2500)
    const dists = Array.from({ length: n }, () =>
      Array.from({ length: m }, () => Array.from({ length: MAX + 1 }, () => false))
    );

    while (queue.length > 0) {
      const [cr, cc, dist, path] = queue.shift();
      if (dist === k) {
        // 알파벳 순으로 탐색했으므로 처음 도착한 경우가 정답
        if (cr === r && cc === c) {
          answer = path;
          break;
        }
        continue;
      }

      for (let i = 0; i < drc.length; i++) {
        // 다음 위치
        const [nr, nc] = [cr + drc[i][0], cc + drc[i][1]];
        if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue; // 맵 밖이면 X
        if (dists[nr][nc][dist]) continue; // 동일한 거리로 이미 방문했으면 X
        dists[nr][nc][dist] = true;
        queue.push([nr, nc, dist + 1, path + dir[i]]);
      }
    }
  }
};

console.log(solution(3, 4, 2, 3, 3, 1, 5));
console.log(solution(2, 2, 1, 1, 2, 2, 2));
console.log(solution(3, 3, 1, 2, 3, 3, 4));

