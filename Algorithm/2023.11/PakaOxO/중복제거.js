/**
 * BaekJoon_13701, 중복 제거
 *  - 문제 분류: 비트마스킹, 비트연산
 */
const solution = () => {
  /* 변수 관리 */
  // js에서는 이 부분에서 메모리 초과가 나는 듯 하다...
  const arr = require("fs").readFileSync("./dev/stdin/13701.txt").toString().trim().split(" ").map(Number);
  const MAX_SIZE = 21; // 2^25 -> 32 = 2^5 -> 2^20 -> 0~31(0), 32~63(1)
  const LENGTH = arr.length;
  let bits = Array.from({ length: MAX_SIZE }, () => 0);
  // [0, 10, 0, ... // 2^20 + 1] // 몫, 나머지 -> 33 -> 1, 1
  // 100 -> 32, 몫 : 3, 나머지 : 4
  // [0, 0, 1000b, 0, ...] -> 8
  // 32bit -> 4byte
  let answer = "";

  /* 메인 로직 */
  // 일단 arr내의 모든 수를 순회 -> 500만
  // 순회하면서 현재 숫자가 이전에 등장한적이 있는지 확인해야 함 -> 이미 등장했던 수를 순회하면 500만 -> X
  // 체크 로직의 시간 복잡도가 O(1)이어야 할듯 -> visited 배열은 사용불가(최대 3300만) -> 메모리 초과
  // visited 배열 대신 비트를 사용해 메모리를 줄이는게 관건일듯
  for (let i=0; i<LENGTH; i++) {
    // 숫자를 32로 나누었을 때, 몫은 idx(32의 몇 배수인지), 나머지는 해당 범위 숫자 중 몇 번째인지를 가리킴
    const [idx, shift] = [Math.floor(arr[i] / 32), arr[i] % 32];
    if (bits[idx] & (1 << shift)) continue;
    bits[idx] += (1 << shift);
    answer += arr[i] + " ";
  }

  /* 정답 반환 */
  return answer.trim();
}

console.log(solution());