/**
 * Programmers_경주로 건설
 *  - 문제 분류: 그래프 탐색
 */
const solution = (board) => {
  /* 변수 관리 */
  const INF = Number.MAX_SAFE_INTEGER;
  const drc = [
    [0, 1],
    [1, 0],
    [-1, 0],
    [0, -1],
  ];
  const N = board.length;
  // 해당 위치, 방향으로 방문했을 때 이전 방문 코스트 메모이제이션
  const v = Array.from({ length: N }, () => Array.from({ length: N }, () => Array.from({ length: 4 }, () => INF)));
  let answer = INF;

  /* 메인 로직 */
  if (board[0][1] === 0) {
    // (0, 1) 방향으로 스타트
    v[0][1][0] = 100;
    dfs(0, 1, 0, 100);
  }

  if (board[1][0] === 0) {
    // (1, 0) 방향으로 스타트
    v[1][0][1] = 100;
    dfs(1, 0, 1, 100);
  }

  /* 최소 비용 반환 */
  return answer;

  // dfs 비용 계산
  function dfs(r, c, dir, cost) {
    if (cost >= answer) return;
    if (r === N - 1 && c === N - 1) {
      answer = cost;
      return;
    }

    for (let i = 0; i < drc.length; i++) {
      const [nr, nc] = [r + drc[i][0], c + drc[i][1]];
      const price = dir === i ? 100 : 600; // 꺾였으면 600원, 직선도로면 100원
      if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] === 1 || v[nr][nc][dir] < cost + price) continue;
      v[nr][nc][dir] = cost + price; // 메모
      dfs(nr, nc, i, cost + price);
    }
  }
};

console.log(
  solution([
    [0, 1, 0],
    [0, 0, 0],
    [1, 0, 0],
  ])
);
// console.log(
//   solution([
//     [0, 0, 0],
//     [0, 0, 0],
//     [0, 0, 0],
//   ])
// );
// console.log(
//   solution([
//     [0, 0, 0, 0, 0, 0, 0, 1],
//     [0, 0, 0, 0, 0, 0, 0, 0],
//     [0, 0, 0, 0, 0, 1, 0, 0],
//     [0, 0, 0, 0, 1, 0, 0, 0],
//     [0, 0, 0, 1, 0, 0, 0, 1],
//     [0, 0, 1, 0, 0, 0, 1, 0],
//     [0, 1, 0, 0, 0, 1, 0, 0],
//     [1, 0, 0, 0, 0, 0, 0, 0],
//   ])
// );
// console.log(
//   solution([
//     [0, 0, 1, 0],
//     [0, 0, 0, 0],
//     [0, 1, 0, 1],
//     [1, 0, 0, 0],
//   ])
// );
// console.log(
//   solution([
//     [0, 0, 0, 0, 0, 0],
//     [0, 1, 1, 1, 1, 0],
//     [0, 0, 1, 0, 0, 0],
//     [1, 0, 0, 1, 0, 1],
//     [0, 1, 0, 0, 0, 1],
//     [0, 0, 0, 0, 0, 0],
//   ])
// );
