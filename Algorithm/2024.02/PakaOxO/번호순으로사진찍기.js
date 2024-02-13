/**
 * CodeTree_번호순으로 사진찍기
 *  - 문제 분류: 정렬, 그리디
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/번호순으로사진찍기.txt").toString().trim().split("\n");
  const [N, K] = input[0].split(" ").map(Number);
  const haters = input.slice(1).map((item) => {
    const arr = item.split(" ").map(Number);
    arr.sort((a, b) => a - b);
    return arr;
  });
  let [left, right] = [0, 0];
  let answer = 1;

  /* 메인 로직 */
  haters.sort((a, b) => {
    if (a[0] === b[0]) return a[1] - b[1];
    return a[0] - b[0];
  });

  for (let i = 0; i < K; i++) {
    if (left === haters[i][0]) continue;

    if (haters[i][1] > right) {
      if (haters[i][0] < right) continue;
      answer++;
    }

    left = haters[i][0];
    right = haters[i][1];
  }

  /* 정답 반환 */
  return answer;
};

console.log(solution());
