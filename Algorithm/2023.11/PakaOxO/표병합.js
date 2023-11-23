/**
 * Programmers_표 병합
 *  - 문제 분류: 구현, union-find
 */
const solution = (commands) => {
  /* 변수 관리 */
  const LENGTH = commands.length;
  const TABLE_ROW = 2;
  const TABLE_COL = 2;
  const table = Array.from({ length: TABLE_ROW * TABLE_COL }, () => null);
  const parents = Array.from({ length: TABLE_ROW * TABLE_COL }, (_, idx) => idx);
  const answer = [];

  /* 메인 로직 */
  for (let i=0; i<LENGTH; i++) {
    runCommand(commands[i]);
  }

  /* 정답 반환 */
  return answer;

  // command 문자열로 함수와 매칭
  function runCommand(command) {
    const [type, ...data] = command.split(" ");

    if (type === "UPDATE") {
      update(data);
    } else if (type === "MERGE") {
      merge(data);
    } else if (type === "UNMERGE") {
      unmerge(data);
    } else if (type === "PRINT") {
      print(data);
    }
  }

  // update
  function update(data) {
    if (data.length === 3) {
      let [r, c, value] = data;
      r--; c--;
      const idx = r * TABLE_COL + c;
      const parent = find(idx);
      table[parent] = value;
    } else if (data.length === 2) {
      const [target, value] = data;
      for (let i=0; i<TABLE_ROW*TABLE_COL; i++) {
        if (table[i] !== target) continue;
        table[i] = value;
      }
    }
  }

  // merge
  function merge(data) {
    let [r1, c1, r2, c2] = data;
    r1--; c1--; r2--; c2--;
    if (r1 === r2 && c1 === c2) return;
    const [i, j] = [r1 * TABLE_COL + c1, r2 * TABLE_COL + c2];
    const value = table[i] || table[j];
    union(i, j);
    const parent = find(i);
    for (let i=0; i<TABLE_ROW*TABLE_COL; i++) {
      if (parents[i] !== parent) continue;
      table[i] = value;
    }
    table[parent] = value;
  }

  // unmerge
  function unmerge(data) {
    let [r, c] = data;
    r--; c--;
    const idx = r * TABLE_COL + c;
    const parent = find(idx);
    const value = table[parent];

    for (let i=0; i<TABLE_ROW*TABLE_COL; i++) {
      const p = find(i);
      if (parent !== p) continue;
      parents[i] = i;
      table[i] = null;
    }
    table[idx] = value;
  }

  // print
  function print(data) {
    let [r, c] = data;
    r--; c--;
    const idx = r * TABLE_COL + c;
    const parent = find(idx);
    answer.push(table[parent] === null ? "EMPTY" : table[idx]);
  }

  // find
  function find(idx) {
    if (parents[idx] === idx) return parents[idx];
    parents[idx] = find(parents[idx]);
    return parents[idx];
  }

  // union
  function union(i, j) {
    const [pi, pj] = [find(i), find(j)];
    parents[pj] = pi;
  }
}

// console.log(solution(["UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"]));
console.log(solution(["UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"]));