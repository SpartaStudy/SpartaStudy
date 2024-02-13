/**
 * BaekJoon_달력
 *  - 문제 분류: 투포인터, 그리디
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/달력.txt").toString().trim().split("\n");
  const N = +input[0];
  const MAX = 1001;
  const acc = Array.from({ length: MAX + 1 }, () => 0);
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    const [a, b] = input[i].split(" ").map(Number);
    acc[a]++;
    acc[b + 1]--;
  }

  let temp = 0;
  let len = 0;
  for (let i = 1; i <= MAX; i++) {
    acc[i] += acc[i - 1];
    temp = Math.max(acc[i], temp);

    if (acc[i] > 0) len++;

    if (acc[i] === 0) {
      answer += len * temp;
      temp = 0;
      len = 0;
    }
  }
  /* 정답 반환 */
  return answer;
};

console.log(solution());
