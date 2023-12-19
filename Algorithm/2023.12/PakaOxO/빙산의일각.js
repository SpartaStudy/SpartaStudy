/**
 * CodeTree_빙산의 일각
 *  - 문제 분류: 그리디, 정렬
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/빙산의일각.txt").toString().trim().split("\n");
  const N = +input[0];
  const heights = [];
  const v = new Array(N).fill(false);
  let count = 0;
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    const height = +input[i];
    heights.push([height, i - 1]);
  }

  heights.sort((a, b) => {
    return b[0] - a[0];
  });

  for (let i = 0; i < N; i++) {
    const [h, pos] = heights[i];
    const [left, right] = [pos - 1, pos + 1];
    v[pos] = true;
    let [lFlag, rFlag] = [false, false];

    if (left >= 0 && v[left]) lFlag = true;
    if (right < N && v[right]) rFlag = true;

    if (lFlag && rFlag) {
      count--;
    } else if (lFlag || rFlag) {
    } else {
      count++;
    }

    answer = Math.max(answer, count);
  }

  /* 정답 반환 */
  return answer;
};

console.log(solution());
