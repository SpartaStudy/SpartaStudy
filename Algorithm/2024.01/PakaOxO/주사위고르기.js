/**
 * Programmers_주사위 고르기
 *  - 문제 분류: 조합론
 */
const solution = (dice) => {
  /* 변수 관리 */
  const N = dice.length;
  const INF = parseInt("1111100000", 2);
  const v = Array.from({ length: INF + 1 }, () => null);
  let max = 0;
  let bits = 0;
  let answer = [];

  /* 메인 로직 */
  const initComb = new Map();
  initComb.set(0, 1);
  dfs(0, 0, [initComb], 0);

  /* 정답 반환 */
  for (let i = 0; i < N; i++) {
    if ((bits & (1 << i)) > 0) answer.push(i + 1);
  }

  return answer;

  // dfs
  function dfs(depth, count, comb, bit) {
    if (count === N / 2) {
      v[bit] = comb[comb.length - 1];
      const reversed = parseInt("1".repeat(N), 2) - bit;
      if (v[reversed]) {
        let win = 0;
        let loss = 0;
        for (const [key1, val1] of v[bit]) {
          for (const [key2, val2] of v[reversed]) {
            if (key1 > key2) {
              win += val1 * val2;
            } else if (key1 < key2) {
              loss += val1 * val2;
            }
          }
        }

        if (win > max) {
          bits = bit;
          max = win;
        }
        if (loss > max) {
          bits = reversed;
          max = loss;
        }
      }
      return;
    }

    for (let i = depth; i < N; i++) {
      if ((bit & (1 << i)) > 0) continue;
      const newComb = new Map();
      for (const [key, val] of comb[comb.length - 1]) {
        for (const num of dice[i]) {
          const newKey = key + num;
          const sum = newComb.get(newKey);
          if (sum) {
            newComb.set(newKey, sum + val);
          } else {
            newComb.set(newKey, val);
          }
        }
      }

      comb.push(newComb);
      dfs(i + 1, count + 1, comb, bit | (1 << i));
      comb.pop();
    }
  }
};

console.log(
  solution([
    [1, 2, 3, 4, 5, 6],
    [3, 3, 3, 3, 4, 4],
    [1, 3, 3, 4, 4, 4],
    [1, 1, 4, 4, 5, 5],
  ])
);
