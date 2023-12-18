/**
 * BaekJoon_11561, 징검다리
 *  - 문제 분류: 이분 탐색
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/11561.txt").toString().split("\n");
  const T = +input[0];
  let answer = [];

  /* 메인 로직 */
  for (let tc = 1; tc <= T; tc++) {
    const N = +input[tc];
    const max = binarySearch(N);
    answer.push(max.toString());
  }

  /* 정답 반환 */
  return answer.join("\n");

  // 이진탐색
  function binarySearch(n) {
    let left = 1;
    let right = n;

    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      if (check(mid, n)) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return right;
  }

  // 해당 개수만큼 밟을 수 있는지
  function check(target, n) {
    let [left, right] = [1, target];

    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      if ((mid * (mid + 1)) / 2 <= n) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return right >= target;
  }
};

console.log(solution());
