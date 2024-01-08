/**
 * BaekJoon_산 모양 타일링
 *  - 문제 분류: 다이나믹 프로그래밍
 */
const solution = (n, tops) => {
  /* 변수 관리 */
  const end = n * 2;
  const dp = Array.from({ length: end + 1 }, () => Array.from({ length: 4 }, () => 0));
  const INF = 10007;

  /* 메인 로직 */
  dp[0][0] = 1;
  dp[0][1] = 1;

  for (let i = 1; i <= end; i++) {
    if (i % 2 === 0) {
      dp[i][0] = (dp[i - 1][0] + dp[i - 2][1] + dp[i - 1][2]) % INF;
      if (i < end) {
        dp[i][1] = dp[i][0];
      }
    } else {
      dp[i][0] = (dp[i - 1][0] + (i - 2 >= 0 ? dp[i - 2][3] : 0)) % INF;
      if (tops[Math.floor(i / 2)] > 0) {
        dp[i][2] = dp[i][0];
      }
      dp[i][3] = (dp[i - 1][0] + (i - 2 >= 0 ? dp[i - 2][3] : 0)) % INF;
    }
  }

  /* 정답 반환 */
  return (dp[end][0] + dp[end - 1][3]) % INF;
};

console.log(solution(2, [0, 1]));
console.log(solution(4, [1, 1, 0, 1]));
console.log(solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]));
