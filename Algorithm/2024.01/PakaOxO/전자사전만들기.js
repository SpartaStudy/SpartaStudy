/**
 * CodeTree_전자사전 만들기
 *  - 문제 분류: 이분 탐색
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/전자사전만들기.txt").toString().trim().split("\n");
  const [N, T] = input[0].split(" ").map(Number);
  const dict = [];
  let pointer = 1;
  const answer = [];

  /* 메인 로직 */
  while (pointer <= N) {
    dict.push([input[pointer].trim(), pointer++]);
  }
  dict.sort((a, b) => (a[0] < b[0] ? -1 : 1));

  while (pointer <= N + T) {
    const [idx, target] = input[pointer++].trim().split(" ");
    const start = binarySearch(target, 0);
    const pos = start + +idx - 1;
    const regex = new RegExp(`^${target}`);

    if (!dict[start][0].match(regex)) {
      answer.push(-1);
    } else {
      if (pos >= N) {
        answer.push(-1);
      } else {
        answer.push(dict[pos][0].match(regex) ? dict[pos][1] : -1);
      }
    }
  }

  /* 정답 반환 */
  return answer.join("\n");

  // binarySearch
  function binarySearch(target) {
    let [left, right] = [0, N - 1];

    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      if (dict[mid][0] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return left;
  }
};

console.log(solution());
