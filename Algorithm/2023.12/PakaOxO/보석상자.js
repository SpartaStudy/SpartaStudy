/**
 * BaekJoon_2792, 보석상자
 *  - 문제 분류: Parametric Search
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/2792.txt").toString().split("\n");
  const [N, M] = input[0].split(" ").map(Number);
  const gems = [];

  /* 메인 로직 */
  for (let i = 0; i < M; i++) {
    const count = +input[i + 1];
    gems.push(count);
  }
  gems.sort((a, b) => b - a);
  answer = binarySearch(1, gems[0]);

  /* 정답 반환 */
  return answer;

  // 이진 탐색
  function binarySearch(left, right) {
    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      if (check(mid)) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  // check
  function check(target) {
    let count = 0;
    for (let i = 0; i < M; i++) {
      const share = Math.floor(gems[i] / target);
      const res = gems[i] % target;

      count += share + (res > 0 ? 1 : 0);
      if (count > N) return false;
    }

    return true;
  }
};

console.log(solution());
