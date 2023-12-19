/**
 * CodeTree_연속되는 수
 *  - 문제 분류: 구현, 시뮬레이션
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/연속되는수.txt").toString().trim().split("\n");
  const N = +input[0];
  const arr = [];
  const set = new Set();
  let answer = 0;

  /* 메인 로직 */
  // 최대 1000개의 숫자의 종류를 제거 가능
  for (let i = 1; i <= N; i++) {
    arr.push(+input[i]);
    set.add(arr[i - 1]);
  }

  // 제거 후 연속되는 숫자 세는 경우 -> 약 1000개
  // 1000 * 1000 = 100만
  for (const num of set) {
    let count = 0;
    let prev = -1;
    for (let i = 0; i < N; i++) {
      if (arr[i] === num) continue;
      if (arr[i] === prev) {
        count++;
      } else {
        count = 1;
      }
      prev = arr[i];

      if (count > answer) {
        answer = count;
      }
    }
  }

  /* 정답 반환 */
  return answer;
};

console.log(solution());
