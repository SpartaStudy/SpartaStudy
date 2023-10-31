/**
 * BaekJoon_1991, 트리 순회
 *  - 문제 분류: 트리, 그래프 탐색
 */
const solution = () => {
  class Node {
    constructor(char) {
      this.char = char;
      this.left = null;
      this.right = null;
    }
  }

  class Tree {
    constructor(n) {
      this.root = null;
      this.nodes = Array.from({ length: n }, () => null);
      this.path = "";
    }

    preorder(node) {
      if (node === this.root) this.path = "";
      this.path += node.char;
      if (node.left !== null) this.preorder(node.left);
      if (node.right !== null) this.preorder(node.right);

      return this.path;
    }

    inorder(node) {
      if (node === this.root) this.path = "";
      if (node.left !== null) this.inorder(node.left);
      this.path += node.char;
      if (node.right !== null) this.inorder(node.right);

      return this.path;
    }

    postorder(node) {
      if (node === this.root) this.path = "";
      if (node.left !== null) this.postorder(node.left);
      if (node.right !== null) this.postorder(node.right);
      this.path += node.char;

      return this.path;
    }

    setNode(idx, node) {
      this.nodes[idx] = node;
    }

    getNode(idx) {
      return this.nodes[idx];
    }
  }

  /* 변수 관리 */
  const input = require("fs").readFileSync("./dev/stdin/1991.txt").toString().split("\n");
  const N = +input[0];
  const tree = new Tree(N);
  const answer = [];

  /* 메인 로직 */
  for (let i=1; i<=N; i++) {
    const [p, l, r] = input[i].split(" ").map((item) => item.trim());
    const n1 = p.charCodeAt(0) - 65;
    const n2 = l !== '.' ? l.charCodeAt(0) - 65 : -1;
    const n3 = r !== '.' ? r.charCodeAt(0) - 65: -1;

    if (tree.getNode(n1) === null) tree.setNode(n1, new Node(p));
    if (n2 >= 0 && tree.getNode(n2) === null) tree.setNode(n2, new Node(l));
    if (n3 >= 0 && tree.getNode(n3) === null) tree.setNode(n3, new Node(r));

    const parent = tree.getNode(n1);
    if (p === 'A') tree.root = parent;
    if (n2 >= 0 && tree.getNode(n2) !== null) parent.left = tree.getNode(n2);
    if (n3 >= 0 && tree.getNode(n3) !== null) parent.right = tree.getNode(n3);
  }

  answer.push(tree.preorder(tree.root));
  answer.push(tree.inorder(tree.root));
  answer.push(tree.postorder(tree.root));

  /* 순회 결과 반환 */
  return answer.join("\n")
}

console.log(solution());