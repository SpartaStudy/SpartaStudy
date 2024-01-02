/**
 * CodeTree_무~
 *  - 문제 분류: 분할 정복
 */
const solution = () => {
  /* 변수 관리 */
  const N = +require("fs").readFileSync("./dev/stdin/무~.txt").toString().trim();
  let len = [3];
  let t = 1;
  let answer = "";

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    len[i] = len[i - 1] * 2 + i + 3;
    if (len[i] >= N) {
      t = i;
      break;
    }
  }
  answer = find(t, N - 1);

  /* 정답 반환 */
  return answer;

  // find
  function find(level, pos) {
    if (level === 0) {
      return pos === 0 ? "m" : "o";
    }

    if (pos < len[level - 1]) {
      return find(level - 1, pos);
    } else if (pos < len[level - 1] + level + 3) {
      return pos - len[level - 1] === 0 ? "m" : "o";
    } else {
      return find(level - 1, pos - (len[level - 1] + level + 3));
    }
  }
};

console.log(solution());

