/**
 * Programmers_1,2,3 떨어트리기
 *  - 문제 분류: 트리
 */
const solution = (edges, target) => {
  class Node {
    constructor(value) {
      this.value = value;
      this.sum = 0;
      this.children = [];
      this.nextChildIdx = -1;
    }
  }

  /* 변수 관리 */
  const N = edges.length;
  const nodes = Array.from({ length: N + 1 }, (_, idx) => new Node(idx));
  const INF = Number.MAX_SAFE_INTEGER;
  let maxDepth = INF;
  const comb = [];
  let answer = [];

  /* 메인 로직 */
  for (let i=0; i<edges.length; i++) {
    const [parent, child] = edges[i];
    // 노드에 자식노드 정보 추가
    nodes[parent - 1].children.push(nodes[child - 1]);
  }

  for (let i=0; i<N; i++) {
    if (nodes[i].children.length > 0) {
      // 번호가 작은 순서대로 자식 노드 정렬
      nodes[i].children.sort((a, b) => a.value - b.value);
      nodes[i].nextChildIdx = 0;
    }
  }
  dfs(0);


  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(depth) {
    if (depth > maxDepth) return;
    if (check()) {
      maxDepth = depth;
      answer.push([...comb]);
      return;
    }

    for (let i=3; i>=1; i--) {
      const flag = push(i);

      if (flag) {
        comb.push(i);
        dfs(depth + 1);
        comb.pop();
        pop(i);
      }
    }
  }

  // push
  function push(num) {
    let currNode = nodes[0];
    while (currNode.children.length > 0) {
      const currChild = currNode.nextChildIdx;
      currNode = currNode.children[currChild];
    }

    if (currNode.sum + num > target[currNode.value]) return false;
    
    currNode = nodes[0];
    while (currNode.children.length > 0) {
      const childrenLen = currNode.children.length;
      const currChild = currNode.nextChildIdx;
      const nextChild = (currNode.nextChildIdx + 1) % childrenLen;
      
      currNode.nextChildIdx = nextChild;
      currNode = currNode.children[currChild];
    }
    currNode.sum += num;

    return true;
  }

  // pop
  function pop(num) {
    let currNode = nodes[0];
    while (currNode.children.length > 0) {
      const len = currNode.children.length;
      const prevChild = (currNode.nextChildIdx - 1 + len) % len;

      currNode.nextChildIdx = prevChild;
      currNode = currNode.children[prevChild];
    }

    currNode.sum -= num;
  }

  // check
  function check() {
    const sums = [];
    for (let i=0; i<=N; i++) {
      if (nodes[i].sum !== target[i]) return false;
      sums.push(nodes[i].sum);
    }
    return true;
  }
}

console.log(solution([
  [2, 4], [1, 2], [6, 8], [1, 3], [5, 7], [2, 5], [3, 6], [6, 10], [6, 9]
],	[0, 0, 0, 3, 0, 0, 5, 1, 2, 3]));
console.log(solution([[1, 2], [1, 3]],	[0, 7, 3]));
console.log(solution([[1, 3], [1, 2]],	[0, 7, 1]));