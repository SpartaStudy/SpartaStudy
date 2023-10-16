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
  dfs(x, y, 0, ""); // 시작점부터 bfs 탐색

  /* 정답 반환 */
  return answer || "impossible";

  /**
   * dfs
   * **bfs는 k보다 작은 모든 경로를 우선 탐색하기 때문에 시간초과
   * **heap을 사용해 풀거나 하는 방식으로 bfs 사용 가능
   */
  function dfs(cr, cc, dist, path) {
    let d = Math.abs(cr - r) + Math.abs(cc - c);
    if (d % 2 !== (k - dist) % 2) return; // 목표 이동과 실제 이동 홀짝이 다르면 return
    if (answer !== null) return;

    if (dist === k) {
      if (cr === r && cc === c) {
        answer = path;
        return;
      }
      return;
    }

    for (let i = 0; i < drc.length; i++) {
      // 다음 위치
      const [nr, nc] = [cr + drc[i][0], cc + drc[i][1]];
      if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue; // 맵 밖이면 X
      d = Math.abs(nr - r) + Math.abs(nc - c);
      if (d > k - dist - 1) continue;
      dfs(nr, nc, dist + 1, path + dir[i]);
    }
  }
};

console.log(solution(3, 4, 2, 3, 3, 1, 5));
console.log(solution(2, 2, 1, 1, 2, 2, 2));
console.log(solution(3, 3, 1, 2, 3, 3, 4));

