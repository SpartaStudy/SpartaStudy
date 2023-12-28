/**
 * BaekJoon_27067, 데이터 순서 복원
 *  - 문제 분류: 정렬
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/27067.txt").toString().trim().split("\n");
  const N = +input[0];
  const min = Array.from({ length: N + 1 }, () => N + 1);
  const sum = Array.from({ length: N + 1 }, () => 0);
  const answer = Array.from({ length: N }, (_, idx) => idx + 1);

  /* 메인 로직 */
  for (i = 1; i <= 3; i++) {
    const arr = input[i].split(" ").map(Number);
    for (let j = 0; j < N; j++) {
      sum[arr[j]] += j + 1;
      min[arr[j]] = Math.min(min[arr[j]], j + 1);
    }
  }
  answer.sort((a, b) => sum[a] - min[a] - (sum[b] - min[b]));

  /* 정답 반환 */
  return answer.join(" ");
};

console.log(solution());
