/**
 * BaekJoon_2637, 장난감 조립
 *  - 문제 분류: 그래프, 위상 정렬
 */
const solution = () => {
  class Node {
    constructor(idx, n) {
      this.idx = idx;
      this.inDegree = 0;
      this.next = [];
      this.parts = new Map();
      this.hasChild = true;
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/2637.txt").toString().trim().split("\n");
  const N = +input[0];
  const M = +input[1];
  const nodes = Array.from({ length: N + 1 }, (_, idx) => new Node(idx, N));
  const answer = [];

  /* 메인 로직 */
  for (let i=2; i<M + 2; i++) {
    const [next, prev, count] = input[i].split(" ").map(Number);
    nodes[prev].next.push([next, count]);
    nodes[next].inDegree++;
  }

  topologySort();

  for (const part of nodes[N].parts) {
    answer.push(part);
  }
  answer.sort((a, b) => a[0] - b[0]);

  /* 부품 개수 반환 */
  return answer.map((item) => item.join(" ")).join("\n");

  // 위상 정렬
  function topologySort() {
    const queue = [];
    for (let i=1; i<=N; i++) {
      if (nodes[i].inDegree > 0) continue;
      nodes[i].hasChild = false; // 최소 단위 부품
      queue.push(nodes[i]);
    }

    while (queue.length > 0) {
      const curr = queue.shift();
      for (const next of curr.next) {
        const [idx, count] = next;

        if (!curr.hasChild) { // 내가 최소 단위면 상위 부품에 필요한 나의 개수 추가
          nodes[idx].parts.set(curr.idx, count);
        } else {
          const keys = nodes[curr.idx].parts.keys()
          for (const key of keys) { // 부품 A로 B를 만들 때, (A가 가진 부품의 개수) * (B를 만들 때 필요한 A의 개수)
            const prevCount = nodes[idx].parts.get(key); // 하위 부품을 이미 가지고 있는지
            const childPartCount = nodes[curr.idx].parts.get(key);

            if (prevCount === undefined) {
              nodes[idx].parts.set(key, childPartCount * count);
            } else {
              nodes[idx].parts.set(key, prevCount + childPartCount * count);
            }
          }
        }

        nodes[idx].inDegree--; // 진입차수 감소

        if (nodes[idx].inDegree === 0) { // 진입차수가 0이되면 큐에 삽입
          queue.push(nodes[idx]);
        }
      }
    }
  }
}

console.log(solution());