/**
 * BaekJoon_5464, 주차장
 *  - 문제 분류: 큐
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/5464.txt").toString().trim().split("\n");
  const [N, M] = input[0].split(" ").map(Number);
  const costs = [];
  const weights = [];
  const inout = [];
  const queue = [];
  const pos = Array.from({ length: N }, () => -1);
  const isFill = Array.from({ length: N }, () => false);
  let fillCnt = 0;
  let answer = 0;

  for (let i=1; i<=N; i++) costs.push(+input[i]);
  for (let i=N+1; i<=N+M; i++) weights.push(+input[i]);
  for (let i=N+M+1; i<=N+M+(M*2); i++) inout.push(+input[i]);

  /* 메인 로직 */
  for (let i=0; i<inout.length; i++) {
    const curr = inout[i];
    if (curr < 0) {
      // 출차
      const outCar = (-inout[i]) - 1;
      const vacancy = exit(outCar);

      // 기다리고 있던 차가 있으면 이 자리에 주차
      if (queue.length > 0) {
        const carIdx = queue.shift();
        park(vacancy, carIdx);
      }
    } else {
      // 주차장이 다 차있으면 차를 큐에 넣음
      if (fillCnt === N) {
        queue.push(curr - 1);
        continue;
      }

      // 아니면 주차장에 주차
      const vacancy = getVacancy();
      park(vacancy, curr - 1);
    }
  }

  /* 전체 수익 반환 */
  return answer;

  // 남은 차리 찾기
  function getVacancy() {
    for (let i=0; i<N; i++) {
      if (!isFill[i]) return i;
    }
    return -1;
  }

  // 주차장 차 넣기
  function park(posIdx, carIdx) {
    isFill[posIdx] = true;
    fillCnt++;
    pos[carIdx] = posIdx;

    answer += costs[posIdx] * weights[carIdx];
  }

  // 차 번호를 기준으로 주차장에서 출차
  function exit(carIdx) {
    const vacancy = pos[carIdx];
    fillCnt--;
    isFill[vacancy] = false;
    pos[carIdx] = -1;

    return vacancy;
  }
}

console.log(solution());