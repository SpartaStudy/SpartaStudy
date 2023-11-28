/**
 * Programmers_양과 늑대
 *  - 문제 분류: 트리, 그래프 탐색
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
      this.path = [];
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

    hasPath(path) {
      for (let p of this.path) {
        if (p === path) return true;
      }
      return false;
    }

    addPath(path) {
      this.path.push(path);
    }
  }

  /* 변수 관리 */
  const N = info.length;
  const nodes = Array.from({ length: N }, (_, idx) => new Node(idx, info[idx]));
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
  dfs(nodes[0], 0, 0, 0);

  /* 정답 반환 */
  return answer;

  // dfs
  function dfs(currNode, cnt0, cnt1, path) {
    // 현재 인덱스
    const idx = currNode.idx;

    // cnt 증가
    if (currNode.val === 0) {
      if ((path & (1 << idx)) === 0) cnt0++;
    } else {
      if ((path & (1 << idx)) === 0) cnt1++;
    }
    if (cnt0 <= cnt1) return;

    answer = Math.max(answer, cnt0);

    // 현재 위치까지의 경로 갱신
    const newPath = path | (1 << idx);
    if (currNode.hasPath(newPath)) return;
    currNode.addPath(newPath);

    if (currNode.left !== null) {
      dfs(currNode.left, cnt0, cnt1, newPath);
    }

    if (currNode.right !== null) {
      dfs(currNode.right, cnt0, cnt1, newPath);
    }

    if (currNode.parent !== null) {
      dfs(currNode.parent, cnt0, cnt1, newPath);
    }
  }
}

console.log(solution([0,0,1,1,1,0,1,0,1,0,1,1],	[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]));
console.log(solution([0,1,0,1,1,0,1,0,0,1,0],	[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]));
console.log(solution([0, 1], [[0, 1]]));