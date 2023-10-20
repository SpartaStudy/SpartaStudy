/**
 * Programmers_셔틀버스
 *  - 문제 분류: 이분탐색?
 */
const solution = (n, t, m, timeTable) => {
  /* 변수 관리 */
  let answer = 0;
  const timeTableNumber = timeTable.map((time) => getMinutes(time));
  const FIRST_BUS = getMinutes("09:00");
  const busTimeTable = Array.from({ length: n }, (_, idx) => FIRST_BUS + idx * t);

  /* 메인 로직 */
  timeTableNumber.sort((a, b) => a - b);
  let [left, right] = [1, 1439];

  while (left <= right) {
    const mid = Math.floor((left + right) / 2);
    if (check(mid)) {
      left = mid + 1;
    } else {
      right = mid - 1;
    }
  }

  answer = getTime(right);

  /* 정답 반환 */
  return answer;

  // 해당 시간에 줄 서면 탈 수 있는지
  function check(time) {
    const queue = [];
    let myIdx = -1;
    for (let i = 0; i < timeTableNumber.length; i++) {
      if (myIdx < 0 && timeTableNumber[i] > time) {
        queue.push(time);
        myIdx = i;
      }
      queue.push(timeTableNumber[i]);
    }

    if (myIdx < 0) {
      queue.push(time);
      myIdx = queue.length - 1;
    }

    let [bPointer, cPointer] = [0, 0];
    while (bPointer < busTimeTable.length) {
      let cnt = 0;
      while (cnt < m && cPointer < queue.length && queue[cPointer] <= busTimeTable[bPointer]) {
        cnt++;
        cPointer++;
      }
      if (cPointer > myIdx) return true;
      bPointer++;
    }

    return false;
  }

  // 시간(String)을 분(Number)으로
  function getMinutes(time) {
    const [h, m] = time.split(":").map((item) => +item);
    return h * 60 + m;
  }

  // 분(Number)을 시간(String)으로
  function getTime(minute) {
    const h = Math.floor(minute / 60);
    const m = minute - h * 60;

    return `${h < 10 ? "0" + h : h}:${m < 10 ? "0" + m : m}`;
  }
};

// console.log(solution(1, 1, 5, ["08:00", "08:01", "08:02", "08:03"]));
console.log(solution(2, 10, 2, ["09:10", "09:09", "08:00"]));
