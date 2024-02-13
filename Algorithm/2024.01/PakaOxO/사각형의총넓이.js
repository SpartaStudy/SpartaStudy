/**
 * CodeTree_사각형의 총 넓이
 *  - 문제 분류: Plane Sweeping
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/사각형의총넓이.txt").toString().trim().split("\n");
  const N = +input[0];
  const RANGE = 10000;
  const squares = input.slice(1).map((item) => {
    const [x1, y2, x2, y1] = item.split(" ").map(Number);
    return {
      x1,
      x2,
      y1,
      y2,
    };
  });
  const heights = Array.from({ length: RANGE * 2 }, () => []);
  let answer = 0;

  /* 메인 로직 */
  for (let i = 0; i < N; i++) {
    const { x1, x2, y1, y2 } = squares[i];
    for (let j = x1; j < x2; j++) {
      const pos = j + RANGE;
      let [min, max] = [y1, y2];
      for (let k = 0; k < heights[pos].length; k++) {
        if (heights[pos][k] === null) continue;
        if (isOverwrap(min, max, heights[pos][k][0], heights[pos][k][1])) {
          min = Math.min(min, heights[pos][k][0]);
          max = Math.max(max, heights[pos][k][1]);
          heights[pos][k] = null;
        }
      }

      heights[pos] = heights[pos].filter((item) => item !== null);
      heights[pos].push([min, max]);
    }
  }

  for (let i = 0; i < heights.length; i++) {
    for (const [min, max] of heights[i]) {
      answer += max - min;
    }
  }

  /* 정답 반환 */
  return answer;

  // 겹치는지?
  function isOverwrap(y1, y2, min, max) {
    if (y2 < min || y1 > max) return false;
    return true;
  }
};

console.log(solution());
