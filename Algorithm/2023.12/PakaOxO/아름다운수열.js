/**
 * CodeTree_아름다운 수열
 *  - 문제 분류: 정렬
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/아름다운수열.txt").toString().trim().split("\n");
  const N = +input[0];
  const M = +input[N + 1];
  const A = input.slice(1, N + 1).map(Number);
  const B = input.slice(N + 2).map(Number);
  let answer = [0];

  /* 메인 로직 */
  B.sort((a, b) => a - b);

  loop: for (let i = 0; i <= N - M; i++) {
    // 길이 M의 서브트리 GET
    const subArr = A.slice(i, i + M);
    subArr.sort((a, b) => a - b); // 오름차순 정렬

    let pointer = 0;
    let diff = B[pointer] - subArr[pointer]; // 맨 앞에 친구, 순열 B와 차이값 GET

    for (pointer = pointer + 1; pointer < M; pointer++) {
      if (B[pointer] - subArr[pointer] !== diff) continue loop; // 이후 차이값이 다르면 다음 서브트리로 이동
    }

    answer[0]++;
    answer.push(i + 1);
  }

  /* 정답 반환 */
  return answer.join("\n");
};

console.log(solution());
