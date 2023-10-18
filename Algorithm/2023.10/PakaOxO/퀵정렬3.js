/**
 * BaekJoon_24092, 퀵 정렬 3
 *  - 문제 분류: 퀵 정렬
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/24092.txt").toString().split("\n");
  const N = +input[0];
  const A = input[1].split(" ").map((item) => +item);
  const B = input[2].split(" ").map((item) => +item);
  const sorted = [...A];
  let sameCount = 0;
  let answer = 0;

  /* 메인 로직 */
  for (let i = 0; i < N; i++) {
    if (A[i] === B[i]) sameCount++;
  }
  if (sameCount === N) answer = 1;
  quickSort(0, N - 1);

  /* 정답 반환 */
  return answer;

  // 퀵 정렬
  function quickSort(left, right) {
    if (left > right) return;
    const pivot = partition(left, right);
    quickSort(left, pivot - 1);
    quickSort(pivot + 1, right);
  }

  // 분할
  function partition(left, right) {
    const pivot = sorted[right]; // lomuto partition
    let pointer = left - 1;

    for (let i = left; i < right; i++) {
      if (sorted[i] <= pivot) {
        swap(++pointer, i);
      }
    }

    swap(pointer + 1, right);
    return pointer + 1;
  }

  // swap
  function swap(i, j) {
    const [f1, f2] = [sorted[i] === B[i], sorted[j] === B[j]];
    [sorted[i], sorted[j]] = [sorted[j], sorted[i]];
    const [f3, f4] = [sorted[i] === B[i], sorted[j] === B[j]];

    if (f1 && !f3) sameCount--;
    if (f2 && !f4) sameCount--;
    if (!f1 && f3) sameCount++;
    if (!f2 && f4) sameCount++;

    if (sameCount === N) answer = 1;
  }
};

console.log(solution());
