/**
 * BaekJoon_233883, 선택 정렬 3
 *  - 문제 분류: 정렬 알고리즘
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/233883.txt").toString().split("\n");
  const [N, K] = input[0].split(" ").map((item) => +item);
  const arr = input[1].split(" ").map((item) => +item);
  const sorted = [...arr];
  let count = 0;
  const map = new Map(); // 이전 위치를 기억해둘 map(최대 50만개 데이터, 키 중복 X)
  let answer = [];

  /* 메인 로직 */
  // 초기 위치 map에 저장
  for (let i = 0; i < N; i++) {
    map.set(arr[i], i);
  }
  // 정렬된 배열 만들어두기
  sorted.sort((a, b) => a - b);

  // 맨 끝부터 정렬 시작
  for (let i = N - 1; i > 0; i--) {
    // 이미 정렬되어 있는 녀석이면 continue
    if (arr[i] === sorted[i]) continue;

    // 원래 위치와 현재 위치(i)를 swap, swap 횟수(count) + 1
    const prevPos = map.get(sorted[i]);
    swap(prevPos, i);
    count++;

    // map에서도 위치 갱신
    map.set(sorted[i], i);
    map.set(arr[prevPos], prevPos);

    // K번 swap 되었으면 해당 두 숫자를 answer에 추가
    if (count === K) {
      answer.push(arr[prevPos]);
      answer.push(arr[i]);
      break;
    }
  }

  // answer는 오름차순으로
  answer.sort((a, b) => a - b);

  /* 정답 반환 */
  return answer.length === 0 ? -1 : answer.join(" "); // K번째 swap이 없으면 -1 반환

  // swap
  function swap(i, j) {
    const temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
};

console.log(solution());
