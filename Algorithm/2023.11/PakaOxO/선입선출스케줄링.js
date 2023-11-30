/**
 * Programmers_선입선출 스케줄링
 *  - 문제 분류: Parametric search(이분 탐색)
 */
const solution = (n, cores) => {
  /* 변수 관리 */
  const coreCount = cores.length;
  const max = Math.max(...cores);
  let [left, right] = [0, n * max + 1];
  let answer = 0;

  /* 메인 로직 */
  // 프로세스 최대 개수: 5만
  // 코어 최소 개수: 1
  // 프로세스별 최대 시간: 1만
  // ex. 1개의 코어로 1만짜리 프로세스 5만개 = 5000만 -> 시간을 제거하면 5만
  // ex. 1만개의 코어로 1만짜리 프로세스 5만개 = 시간을 제외해도 5000만 -> 일단 여기서부터 힙큐는 안되는구나
  // 그럼 최대 시간 범위는? 1개의 코어로 1만짜리 프로세스 5만개를 하는 것 -> 5000만 -> 이진탐색하면?
  // console.log(Math.log2(50000000)); // 약 25.5
  if (coreCount >= n) {
    answer = n;
  } else {
    while (left <= right) {
      const mid = Math.floor((left + right) / 2);
      const processEnd = check(mid);
      if (processEnd >= n) {
        right = mid - 1;
        answer = getLastCore(mid);
      } else {
        left = mid + 1;
      }
    }
  }

  /* 정답 반환 */
  return answer;

  // 해당 시간(time)에 완료할 수 있는 작업의 개수
  function check(time) {
    let count = coreCount;
    for (let i=0; i<coreCount; i++) {
      count += Math.floor(time / cores[i]);
      if (count >= n) return count;
    }
    return count;
  }

  // 해당 시간(time)에 마지막으로 작업을 할당받는 코어
  function getLastCore(time) {
    let count = n - coreCount;

    const res0 = []; // 딱 해당 시간에 새로 작업을 시작할 코어들
    for (let i=0; i<coreCount; i++) {
      count -= Math.floor((time - 1) / cores[i]);
      if (count < 0) return -1;
      if (time % cores[i] === 0) res0.push(i + 1);
    }

    return res0[count - 1];
  }
}


console.log(solution(6,	[1,2,3]));