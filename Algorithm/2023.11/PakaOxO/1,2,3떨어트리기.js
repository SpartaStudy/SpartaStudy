/**
 * Programmers_1,2,3 떨어트리기
 *  - 문제 분류: 트리
 */
const solution = (edges, target) => {
  /* 변수 관리 */
  const N = edges.length;
  const count = Array.from({ length: N + 1 }, () => 0);
  const children = Array.from({ length: N + 1 }, () => []);
  let visit = [];
  let answer = [];

  /* 메인 로직 */
  for (let i=0; i<edges.length; i++) {
    const [parent, child] = edges[i];
    // 노드에 자식노드 정보 추가
    children[parent - 1].push(child - 1);
  }
  for (let i=0; i<=N; i++) children[i].sort((a, b) => a - b);

  while (true) {
    const flag = check(); // 리프노드 방문개수 유효성 체크
    if (flag < 0) { // target이 0인데 방문 되었을 경우
      visit = null;
      break;
    }
    if (flag > 0) break; // 조건에 맞는 최소 방문 횟수를 찾음

    let leaf = 0; // 방문 리프노드를 찾아보자
    while (children[leaf].length > 0) {
      const len = children[leaf].length;
      leaf = children[leaf][count[leaf]++ % len];
    }
    count[leaf]++;
    visit.push(leaf);
  }

  let pointer = 0; // 리프노드에 삽입할 숫자를 정해보자
  while (visit !== null && pointer < visit.length) {
    const leaf = visit[pointer++];
    for (let i=1; i<=3; i++) {
      if ((target[leaf] - i) > (count[leaf] - 1) * 3) continue; // 이 숫자를 넣으면 target 못 채움
      answer.push(i);
      target[leaf] -= i;
      count[leaf]--;
      break;
    }
  }

  if (answer.length === 0) answer.push(-1);
  /* 정답 반환 */
  return answer;

  // check
  function check() {
    for (let i=0; i<=N; i++) {
      if (children[i].length > 0) continue;
      if (target[i] === 0 && count[i] > 0) return -1;
      if (target[i] < count[i]) return -1;
      if (!(target[i] >= count[i] && target[i] <= count[i] * 3)) return 0;
    }
    return 1;
  }
}

console.log(solution([
  [2, 4], [1, 2], [6, 8], [1, 3], [5, 7], [2, 5], [3, 6], [6, 10], [6, 9]
],	[0, 0, 0, 3, 0, 0, 5, 1, 2, 3]));
console.log(solution([[1, 2], [1, 3]],	[0, 7, 3]));
console.log(solution([[1, 3], [1, 2]],	[0, 7, 1]));