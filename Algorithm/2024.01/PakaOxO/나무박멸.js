/**
 * CodeTree_나무 박멸, 삼성SW기출문제
 *  - 문제 분류: 구현
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/나무박멸.txt").toString().trim().split("\n");
  const [N, M, K, C] = input[0].split(" ").map(Number);
  const map = [];
  const herbicide = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
  const drc = [
    [0, 1],
    [0, -1],
    [1, 0],
    [-1, 0],
  ];
  const drc2 = [
    [1, 1],
    [-1, 1],
    [-1, -1],
    [1, -1],
  ];
  let answer = 0;

  /* 메인 로직 */
  for (let i = 1; i <= N; i++) map.push(input[i].split(" ").map(Number));
  for (let year = 0; year < M; year++) {
    treeGrowth();
    treeBreeding();
    removeTree();
    decreaseHerbicide();
  }

  /* 정답 반환 */
  return answer;

  // treeGrowth: N * N * 4 = 1600
  function treeGrowth() {
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < N; j++) {
        if (map[i][j] <= 0) continue;
        let count = 0;
        for (let k = 0; k < 4; k++) {
          const [nr, nc] = [i + drc[k][0], j + drc[k][1]];
          if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] <= 0) continue;
          count++;
        }
        map[i][j] += count;
      }
    }
  }

  // treeBreeding: N * N * 4 = 1600
  function treeBreeding() {
    const trees = [];
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < N; j++) {
        if (map[i][j] <= 0) continue;
        trees.push([i, j]);
      }
    }

    const points = new Map();
    for (const tree of trees) {
      const [r, c] = tree;
      let count = 0;
      for (let i = 0; i < 4; i++) {
        const [nr, nc] = [r + drc[i][0], c + drc[i][1]];
        if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] !== 0 || herbicide[nr][nc] > 0) continue;
        count++;
      }

      if (count > 0) {
        for (let i = 0; i < 4; i++) {
          const [nr, nc] = [r + drc[i][0], c + drc[i][1]];
          if (nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] !== 0 || herbicide[nr][nc] > 0) continue;
          const key = `${nr} ${nc}`;
          const size = points.get(key) || 0;
          points.set(key, size + Math.floor(map[r][c] / count));
        }
      }
    }

    for (const [key, val] of points) {
      const [r, c] = key.split(" ").map(Number);
      map[r][c] += val;
    }
  }

  // removeTree
  function removeTree() {
    const pos = [];
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < N; j++) {
        if (map[i][j] <= 0) continue;
        const arr = [map[i][j], i, j, [[i, j]]];
        for (let k = 0; k < 4; k++) {
          let [nr, nc] = [i, j];
          for (let x = 0; x < K; x++) {
            [nr, nc] = [nr + drc2[k][0], nc + drc2[k][1]];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
            if (map[nr][nc] > 0) {
              arr[0] += map[nr][nc];
            }
            arr[3].push([nr, nc]);
            if (map[nr][nc] <= 0) break;
          }
        }
        pos.push(arr);
      }
    }

    pos.sort((a, b) => b[0] - a[0]);
    if (pos.length > 0) {
      for (const [r, c] of pos[0][3]) {
        herbicide[r][c] = C + 1;
        if (map[r][c] > 0) {
          map[r][c] = 0;
        }
      }
      answer += pos[0][0];
    } else {
      herbicide[0][0] = C + 1;
    }
  }

  // decreaseHerbicide
  function decreaseHerbicide() {
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < N; j++) {
        if (herbicide[i][j] <= 0) continue;
        herbicide[i][j] -= 1;
      }
    }
  }
};

console.log(solution());
