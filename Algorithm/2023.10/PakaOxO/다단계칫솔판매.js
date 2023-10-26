/**
 * Programmers_다단계 칫솔 판매
 *  - 문제 분류: 해시, 트리
 */
const solution = (enroll, referral, seller, amount) => {
  // 노드 클래스
  class Node {
    constructor(parent, idx) {
      this.parent = parent;
      this.idx = idx;
    }
  }

  /* 변수 관리 */
  const N = enroll.length;
  const nodes = Array.from({ length: N }, () => null);
  const hash = new Map(); // 이름과 인덱스를 매칭할 해시
  const root = new Node(null, -1); // 루트 노드(center)
  let answer = Array.from({ length: N }, () => 0);

  /* 메인 로직 */
  for (let i = 0; i < N; i++) {
    const name = enroll[i];
    nodes[i] = new Node(null, i);
    hash.set(name, i);
  }

  for (let i = 0; i < N; i++) {
    const parentName = referral[i];
    // 부모 노드 등록(연결 리스트)
    if (parentName === "-") {
      nodes[i].parent = root;
    } else {
      nodes[i].parent = nodes[hash.get(parentName)];
    }
  }

  for (let i = 0; i < seller.length; i++) {
    let node = nodes[hash.get(seller[i])];
    let amt = amount[i] * 100; // 판매 전체 금액

    while (amt > 0) {
      if (node === root) break; // 루트 노드면 끝
      const percent10 = Math.floor(amt / 10); // 남은 금액의 10할

      answer[node.idx] += amt - percent10; // 9할은 내꺼
      node = node.parent;
      amt = percent10;
    }
  }

  /* 수익 배열 반환 */
  return answer;
};

console.log(
  solution(
    ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"],
    ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"],
    ["young", "john", "tod", "emily", "mary"],
    [12, 4, 2, 5, 10]
  )
);
