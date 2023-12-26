/**
 * BaekJoon_16168, 수행 시간
 *  - 문제 분류: 위상정렬
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/16169.txt").toString().trim().split("\n");
  const N = +input[0];
  const rates = Array.from({ length: N + 1 }, () => []);
  const opTime = new Array(N + 1).fill(0);
  const myRate = new Array(N + 1).fill(0);
  const max = new Array(N + 1).fill(0);
  const counts = new Array(N + 1).fill(0);
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) {
    const [rate, time] = input[i].split(" ").map(Number);
    myRate[i] = rate;
    opTime[i] = time;
    rates[rate].push(i);
  }

  topologicSort();

  /* 정답 반환 */
  return answer;

  // 위상 정렬
  function topologicSort() {
    const q = [];
    for (const start of rates[1]) {
      q.push([start, opTime[start]]);
    }

    while (q.length > 0) {
      const [curr, time] = q.shift();
      answer = Math.max(answer, time);

      if (myRate[curr] + 1 > N) break;
      for (const next of rates[myRate[curr] + 1]) {
        counts[next]++;
        max[next] = Math.max(max[next], time + opTime[next] + (next - curr) ** 2);

        if (counts[next] === rates[myRate[curr]].length) {
          q.push([next, max[next]]);
        }
      }
    }
  }
};

console.log(solution());
