/**
 * CodeTree_외주 수익 최대화하기
 *  - 문제 분류:
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/외주수익최대화하기.txt").toString().trim().split("\n");
  const N = +input[0];
  const works = input.slice(1).map((item) => item.split(" ").map(Number));
  let answer = 0;
  const dp = new Array(N + 1).fill(0);

  /* 메인 로직 */
  for (let i = 0; i < N; i++) {
    const [day, period, cost] = [i + 1, works[i][0], works[i][1]];
    if (day + works[0] >= N) continue;
    for (let j = day; j < day + period; j++) {
      dp[j] = Math.max(dp[j], dp[day - 1] + cost);
    }
  }
  console.log(dp);

  /* 정답 반환 */
  return answer;
};

console.log(solution());
