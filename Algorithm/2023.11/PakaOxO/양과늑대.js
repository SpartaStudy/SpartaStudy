/**
 * Programmers_양과 특대
 *  - 문제 분류: 트리
 */
const solution = (info, edges) => {
  // 이진트리 노드 클래스
  class Node {
    constructor(idx, val) {
      this.idx = idx;
      this.val = val;
      this.left = null;
      this.right = null;
      this.parent = null;
    }

    setChild(child) {
      if (this.left === null) {
        this.left = child;
        return;
      }

      this.right = child;
    }

    setParent(parent) {
      this.parent = parent;
    }
  }

  /* 변수 관리 */
  const N = info.length;
  const nodes = Array.from({ length: N }, (_, idx) => new Node(idx, info[idx]));
  const visited = Array.from({ length: N }, () => false);
  const dp = Array.from({ length: N }, () => 0);
  let answer = 0;

  /* 메인 로직 */
  // 간선 정보로 이진트리 생성
  for (let i=0; i<N-1; i++) {
    const [parentIdx, childIdx] = edges[i];
    const [parent, child] = [nodes[parentIdx], nodes[childIdx]];

    parent.setChild(child);
    child.setParent(parent);
  }

  // 루트노드부터 dfs
  visited[0] = true;
  dfs(nodes[0], 1, 0);

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(currNode, sCount, wCount) {
    if (dp[currNode.idx] >= sCount || sCount <= wCount) return;
    dp[currNode.idx] = sCount;
    answer = Math.max(answer, sCount);
    if (currNode.idx === 0) {
      console.log(visited);
    }

    const [left, right, parent] = [currNode.left, currNode.right, currNode.parent];
    let [sDiff, wDiff] = [0, 0];

    if (left !== null) {
      if (!visited[left.idx]) {
        [sDiff, wDiff] = [left.val === 0 ? 1 : 0, left.val === 1 ? 1 : 0];
      }
      visited[left.idx] = true;
      dfs(left, sCount + sDiff, wCount + wDiff);
      visited[left.idx] = false;
    }

    if (right !== null) {
      if (!visited[right.idx]) {
        [sDiff, wDiff] = [right.val === 0 ? 1 : 0, right.val === 1 ? 1 : 0];
      }
      visited[right.idx] = true;
      dfs(right, sCount + sDiff, wCount + wDiff);
      visited[right.idx] = false;
    }

    if (parent !== null) {
      dfs(parent, sCount, wCount);
    }
  }
}

console.log(solution([0,0,1,1,1,0,1,0,1,0,1,1],	[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]));
// console.log(solution([0,1,0,1,1,0,1,0,0,1,0],	[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]));
// console.log(solution([0, 1], [[0, 1]]));