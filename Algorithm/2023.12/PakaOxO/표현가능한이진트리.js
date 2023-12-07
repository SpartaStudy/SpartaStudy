/**
 * Programmers_표현 가능한 이진트리
 *  - 문제 분류: 트리, 이진탐색, 수학
 */
const solution = (numbers) => {
  /* 변수 관리 */
  const N = numbers.length;
  const answer = Array.from({ length: N }, () => 0);

  /* 메인 로직 */
  for (let i=0; i<N; i++) {
    const flag = isTreeValid(numbers[i]);
    if (flag) answer[i] = 1;
  }

  /* 정답 반환 */
  return answer;

  /****** 메서드 ******/
  // 숫자를 표현 가능한 이진수로
  function isTreeValid(num) {
    const size = getTreeSize(num);
    const binary = binarySearch(0, size - 1, num);
    return num === binary;
  }

  // 숫자를 포화 이진트리로 만들기 위한 길이
  function getTreeSize(num) {
    let pointer = 1; // 1, 2, 3
    let size = Math.pow(2, pointer); // 2, 4, 8

    while (num >= Math.pow(2, size - 1)) {
      pointer++;
      size = Math.pow(2, pointer);
    }

    return size;
  }

  // 이진 탐색으로 숫자 만들기
  function binarySearch(left, right, target) {
    if (left > right) return 0;
    const mid = Math.floor((left + right) / 2);
    const pow = Math.pow(2, mid);

    if (pow > target) return 0;

    const rightPow = binarySearch(mid + 1, right, target - pow);
    const leftPow = binarySearch(left, mid - 1, target - pow - rightPow);
    return leftPow + pow + rightPow;
  }
}

console.log(solution([7, 42, 5, 63, 111, 95, 1, 2]));