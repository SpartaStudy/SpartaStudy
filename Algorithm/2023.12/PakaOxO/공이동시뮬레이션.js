/**
 * Programmers_공 이동 시뮬레이션
 *  - 문제 분류: 
 */
const solution = (n, m, x, y, queries) => {
  /* 변수 관리 */
  const qLength = queries.length;
  let [rStart, rEnd] = [x, x];
  let [cStart, cEnd] = [y, y];

  /* 메인 로직 */
  for (let i=qLength - 1; i>=0; i--) {
    const [dir, dist] = queries[i];

    if (dir === 0) {
      if (cStart !== 0) {
        cStart = cStart + dist;
      }

      cEnd = cEnd + dist >= m ? m - 1 : cEnd + dist;
    } else if (dir === 1) {
      cStart = cStart - dist < 0 ? 0 : cStart - dist;

      if (cEnd !== m - 1) {
        cEnd = cEnd - dist;
      }
    } else if (dir === 2) {
      if (rStart !== 0) {
        rStart = rStart + dist;
      }

      rEnd = rEnd + dist >= n ? n - 1 : rEnd + dist;
    } else {
      rStart = rStart - dist < 0 ? 0 : rStart - dist;

      if (rEnd !== n - 1) {
        rEnd = rEnd - dist;
      }
    }

    if (rStart >= n || rEnd < 0 || cStart >= m || cEnd < 0) return 0;
  }

  /* 정답 반환 */
  return (BigInt(rEnd - rStart + 1) * BigInt(cEnd - cStart + 1)).toString();
}

// console.log(solution(2,2,0,0,[[2,1],[0,1],[1,1],[0,1],[2,1]]));
console.log(solution(2,5,0,1,[[3,1],[2,2],[1,1],[2,3],[0,1],[2,1]]));