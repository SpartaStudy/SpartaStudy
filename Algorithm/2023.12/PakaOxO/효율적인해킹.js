/**
 * BaekJoon_1325, 효율적인 해킹
 *  - 문제 분류: 그래프 탐색, Strongly Connected Component
 */
const solution = () => {
  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/1325.txt").toString().split("\n");
  const [N, M] = input[0].split(" ").map(Number);
  const adjList = Array.from({ length: N + 1 }, () => []);
  const v = Array.from({ length: N + 1 }, () => [0, false]);
  let pointer = 0;
  let sccPointer = Array.from({ length: N + 1 }, () => -1);
  const scc = [];
  const stack = [];
  let answer = [];

  /* 메인 로직 */
  for (let i = 1; i <= M; i++) {
    const [from, to] = input[i].split(" ").map(Number);
    adjList[to].push(from);
  }

  for (let i = 1; i <= N; i++) {
    if (v[i][0] > 0) continue;
    dfs(i);
  }

  // scc 인접리스트 생성
  const sccAdjList = Array.from({ length: scc.length }, () => new Set());
  const inCount = Array.from({ length: scc.length }, (_, idx) => 0);

  for (let i = 1; i <= N; i++) {
    for (const next of adjList[i]) {
      if (sccPointer[i] === sccPointer[next]) continue;
      if (!sccAdjList[sccPointer[next]].has(sccPointer[i])) {
        sccAdjList[sccPointer[i]].add(sccPointer[next]);
        inCount[sccPointer[i]]++;
      }
    }
  }

  // scc dfs
  let max = 0;
  // topologicSort();
  let sccV = null;
  for (let i = 0; i < scc.length; i++) {
    sccV = Array.from({ length: scc.length }, () => false);
    sccV[i] = true;
    const count = sccDfs(i);

    if (count > max) {
      max = count;
      answer = [...scc[i]];
    } else if (count === max) {
      answer.push(...scc[i]);
    }
  }

  answer.sort((a, b) => a - b);

  /* 정답 반환 */
  return answer.join(" ");

  // dfs
  function dfs(idx) {
    v[idx][0] = ++pointer;

    let result = v[idx][0];
    stack.push(idx);

    for (const next of adjList[idx]) {
      if (v[next][0] === 0) {
        result = Math.min(result, dfs(next));
      } else if (!v[next][1]) {
        result = Math.min(result, v[next][0]);
      }
    }

    // 부모라면
    if (result === v[idx][0]) {
      const newScc = [];
      while (stack.length > 0) {
        const poped = stack.pop();

        newScc.push(poped);
        sccPointer[poped] = scc.length;
        v[poped][1] = true;

        if (poped === idx) break;
      }

      newScc.sort((a, b) => a - b);
      scc.push(newScc);
    }

    return result;
  }

  // dfs
  function sccDfs(idx) {
    let count = scc[idx].length;
    for (const next of sccAdjList[idx]) {
      if (sccV[next]) continue;
      sccV[next] = true;
      count += sccDfs(next);
    }
    return count;
  }

  // 위상정렬
  function topologicSort() {
    const q = [];
    const counts = Array.from({ length: scc.length }, (_, idx) => scc[idx].length);

    for (let i = 0; i < scc.length; i++) {
      if (inCount[i] > 0) continue;
      q.push(i);
    }
    console.log(inCount, q);

    while (q.length > 0) {
      const curr = q.shift();
      if (counts[curr] > max) {
        max = counts[curr];
        answer = [...scc[curr]];
      } else if (counts[curr] === max) {
        answer.push(...scc[curr]);
      }

      console.log(counts);

      for (const next of sccAdjList[curr]) {
        inCount[next]--;
        if (inCount[next] === 0) {
          q.push(next);
          counts[next] += counts[curr];
        } else {
          counts[next] += counts[curr];
        }
      }
    }
    console.log(scc, counts);
  }
};

console.log(solution());

