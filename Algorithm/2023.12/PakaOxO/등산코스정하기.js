/**
 * Programmers_등산코스 정하기
 *  - 문제 분류: 그래프 탐색
 */
const solution = (n, paths, gates, summits) => {
  /* 변수 관리 */
  const INF = 10000000;
  const adjList = Array.from({ length: n + 1 }, () => []);
  const types = Array.from({ length: n + 1 }, () => 0); // 0: 쉼터, 1: 출발지, 2: 정상
  let answer = [-1, INF];

  class Node {
    constructor(idx, weight) {
      this.idx = idx;
      this.weight = weight;
    }
  }

  class PriorityQueue {
    constructor() {
      this.arr = [null];
      this.size = 0;
    }

    offer(node) {
      this.arr.push(node);
      this.size++;

      let pointer = this.size;
      let parent =  Math.floor(pointer / 2);

      while (parent > 0) {
        if (this.arr[parent].weight <= this.arr[pointer].weight) break;
        this.swap(pointer, parent);
        pointer = parent;
        parent = Math.floor(pointer / 2);
      }
    }

    poll() {
      if (this.size === 0) return null;
      this.swap(1, this.size);
      const result = this.arr.pop();
      this.size--;

      let pointer = 1;
      while (this.size > 0) {
        const [left, right] = [pointer * 2, pointer * 2 + 1];
        if (left > this.size) break;
        let next;
        if (right <= this.size) {
          next = this.arr[left].weight < this.arr[right].weight ? left : right;
        } else {
          next = left;
        }

        if (this.arr[pointer].weight <= this.arr[next].weight) break;
        this.swap(pointer, next);
        pointer = next;
      }
      
      return result;
    }

    getSize() {
      return this.size;
    }

    swap(i, j) {
      const temp = this.arr[i];
      this.arr[i] = this.arr[j];
      this.arr[j] = temp;
    }
  }

  /* 메인 로직 */
  for (const gate of gates) {
    types[gate] = 1;
  }

  for (const summit of summits) {
    types[summit] = 2
  }

  for (const [from, next, w] of paths) {
    if (types[from] < 2 && types[next] !== 1) {
      adjList[from].push([next, w]);
    }
    if (types[next] < 2 && types[from] !== 1) {
      adjList[next].push([from, w]);
    }
  }

  bfs();

  /* 정답 반환 */
  return answer;

  // bfs
  function bfs() {
    const pq = new PriorityQueue();
    const visited = Array.from({ length: n + 1 }, () => INF + 1);

    for (const start of gates) {
      const node = new Node(start, 0);
      pq.offer(node);
    }

    while (pq.getSize() > 0) {
      const curr = pq.poll();
      if (curr.weight > answer[1]) break;
      
      if (types[curr.idx] === 2) {
        if (curr.weight === answer[1]) {
          if (curr.idx < answer[0]) [answer[0], answer[1]] = [curr.idx, curr.weight];
        } else if (curr.weight < answer[1]) {
          [answer[0], answer[1]] = [curr.idx, curr.weight];
        }
        continue;
      }
 
      for (const [next, w] of adjList[curr.idx]) {
        const weight = Math.max(curr.weight, w);
        if (types[next] === 1 || visited[next] <= weight || answer[1] < weight) continue;
        visited[next] = weight;
        pq.offer(new Node(next, weight));
      }
    }
  }
}

console.log(solution(6,	[[1, 2, 3], [2, 3, 5], [2, 4, 2], [2, 5, 4], [3, 4, 4], [4, 5, 3], [4, 6, 1], [5, 6, 1]],	[1, 3],	[5]));
console.log(solution(7,	[[1, 4, 4], [1, 6, 1], [1, 7, 3], [2, 5, 2], [3, 7, 4], [5, 6, 6]],	[1],	[2, 3, 4]	));