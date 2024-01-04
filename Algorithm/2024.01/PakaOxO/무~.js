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
  // 각 스테이지별 길이 계산
  for (let i = 1; i <= N; i++) {
    len[i] = len[i - 1] * 2 + i + 3; // 이전 스테이지 앞 뒤 + 중간에 moo(o의 길이는 level + 2)
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
    // 0단계까지 왔으면 moo에서 리턴
    if (level === 0) {
      return pos === 0 ? "m" : "o";
    }

    if (pos < len[level - 1]) {
      // (이전 레벨**) + (moo...) + (이전 레벨)
      return find(level - 1, pos);
    } else if (pos < len[level - 1] + level + 3) {
      // (이전 레벨) + (moo...**) + (이전 레벨)
      return pos - len[level - 1] === 0 ? "m" : "o";
    } else {
      // (이전 레벨) + (moo...) + (이전 레벨**)
      return find(level - 1, pos - (len[level - 1] + level + 3));
    }
  }
};

console.log(solution());

