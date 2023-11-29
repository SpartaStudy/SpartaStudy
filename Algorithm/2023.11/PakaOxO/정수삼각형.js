/**
 * Programmers_정수삼각형
 *  - 문제 분류: 다이나믹 프로그래밍
 */
const solution = (triangle) => {
  /* 변수 관리 */
  const N = triangle.length;
  const dp = Array.from({ length: N }, (_, depth) => Array.from({ length: depth }));
  let answer = 0;

  /* 메인 로직 */
  for (let i=0; i<N; i++) {
    answer = Math.max(answer, getDp(N - 1, i));
  }

  /* 정답 반환 */
  return answer;

  // 깊이 depth의 0~N-1까지의 최대 합
  function getDp(depth, x) {
    if (depth < 0 || x < 0 || x > depth) return 0;
    if (dp[depth][x] !== undefined) return dp[depth][x];
    dp[depth][x] = triangle[depth][x] + Math.max(getDp(depth - 1, x - 1), getDp(depth - 1, x));
    return dp[depth][x];
  }
}

console.log(solution([[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]));