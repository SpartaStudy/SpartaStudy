/**
 * Programmers_n + 1 카드게임
 *  - 문제 분류: 그리디
 */
const solution = (coin, cards) => {
  /* 변수 관리 */
  const n = cards.length;
  let LAST = n / 3 + 1;
  const target = n + 1;
  let answer = 1;

  /* 메인 로직 */
  // n의 최대값: 996
  // n / 3 = 332
  // res = 996 - 332 = 664 -> 2개씩 뽑는다치면 332회,  (둘다 & 하나만x2 & 둘다X) = 4^332
  // if X회차까지 올수 있는지?
  // -> n/3 + (X회차 * 뽑는 2장)
  canPlay();

  /* 정답 반환 */
  return answer;

  // canPlay
  function canPlay() {
    const v = Array.from({ length: n + 1 }, () => -1); // 1: 씀, -1: 안뽑음, 0: 들고 있음
    for (let i = 0; i < n / 3; i++) v[i] = 0;

    let len = n / 3 + 2;
    let life = coin;

    for (let i = 1; i <= LAST; i++) {
      const comb = [];
      answer = Math.max(answer, i);

      for (let j = 0; j < len - 1; j++) {
        if (v[j] === 1) continue;
        for (let k = j + 1; k < len; k++) {
          if (v[k] === 1) continue;
          if (cards[j] + cards[k] !== target) continue;
          comb.push([j, k, life + v[j] + v[k]]);
        }
      }

      if (comb.length === 0) return false;
      comb.sort((a, b) => b[2] - a[2]);
      if (comb[0][2] < 0) return false;

      v[comb[0][0]] = v[comb[0][1]] = 1;
      life = comb[0][2];
      len += 2;
    }
    return true;
  }
};

console.log(solution(4, [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4]));
console.log(solution(3, [1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12]));
console.log(solution(2, [5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7]));
console.log(solution(10, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18]));
