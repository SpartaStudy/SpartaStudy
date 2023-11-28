/**
 * Programmers_등굣길
 *  - 문제 분류: 다이나믹 프로그래밍
 */
const solution = (m, n, puddles) => {
  /* 변수 관리 */
  const INF = 1000000007;
  const isPuddles = Array.from({ length: m }, () => Array.from({ length: n }, () => false));
  const dp = Array.from({ length: m }, () => Array.from({ length: n }, () => 0));

  /* 메인 로직 */
  for (const [r, c] of puddles) {
    isPuddles[r - 1][c - 1] = true;
  }
  dp[0][0] = 1;

  /* 정답 반환 */
  return getMinPath(m - 1, n - 1);

  // getMinPath
  function getMinPath(r, c) {
    if (r < 0 || c < 0) return 0;
    if (isPuddles[r][c]) return 0;
    if (dp[r][c] > 0) return dp[r][c];

    dp[r][c] = (getMinPath(r - 1, c) + getMinPath(r, c - 1)) % INF;
    return dp[r][c];
  }
}

console.log(solution(4,	3,	[[2, 2]]));