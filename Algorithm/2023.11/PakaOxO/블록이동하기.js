/**
 * Programmers_블록이동하기
 *  - 문제 분류: 그래프 탐색, 구현
 */
const solution = (board) => {
  /* 변수 관리 */
  const N = board.length;
  let [r, c, dir] = [0, 0, 1];
  const drc = [[-1, 0], [0, 1], [1, 0], [0, -1]];
  const INF = Number.MAX_SAFE_INTEGER;
  const visited = Array.from({ length: N }, () => Array.from({ length: N }, () => Array.from({ length: 4 }, () => false)));
  let answer = INF;

  /* 메인 로직 */
  bfs();
  // console.log(visited);
  
  /* 최소 시간 반환 */
  return answer;
  
  // dfs
  function bfs() {
    visited[r][c][dir] = true;
    const queue = [[r, c, dir, 0]];

    while (queue.length > 0) {
      const [cr, cc, cdir, dist] = queue.shift();
      const [r2, c2] = [cr + drc[cdir][0], cc + drc[cdir][1]];

      if ((cr === N - 1 && cc === N - 1) || (r2 === N - 1 && c2 === N - 1)) {
        answer = dist;
        return;
      }
      
      let [nr, nc, ndir] = [cr, cc, cdir];
      for (let i=0; i<4; i++) {
        [nr, nc] = move(cr, cc, cdir, i, dist + 1);
        if (nr < 0 || nc < 0) continue;
        visited[nr][nc][cdir] = true;
        queue.push([nr, nc, cdir, dist + 1]);
      }
      
      [nr, nc, ndir] = rotate(cr, cc, cdir, 0, cdir - 1, dist + 1);
      if (nr >= 0 && nc >= 0) {
        visited[nr][nc][ndir] = true;
        queue.push([nr, nc, ndir, dist + 1]);
      }
      
      [nr, nc, ndir] = rotate(cr, cc, cdir, 0, cdir + 1, dist + 1);
      if (nr >= 0 && nc >= 0) {
        visited[nr][nc][ndir] = true;
        queue.push([nr, nc, ndir, dist + 1]);
      }

      [nr, nc, ndir] = rotate(cr, cc, cdir, 1, cdir - 1, dist + 1);
      if (nr >= 0 && nc >= 0) {
        visited[nr][nc][ndir] = true;
        queue.push([nr, nc, ndir, dist + 1]);
      }

      [nr, nc, ndir] = rotate(cr, cc, cdir, 1, cdir + 1, dist + 1);
      if (nr >= 0 && nc >= 0) {
        visited[nr][nc][ndir] = true;
        queue.push([nr, nc, ndir, dist + 1]);
      }
    }
  }

  // move(상우하좌)
  function move(r, c, dir, ndir, depth) {
    const [r2, c2] = [r + drc[dir][0], c + drc[dir][1]];

    const [nr, nc] = [r + drc[ndir][0], c + drc[ndir][1]];
    const [nr2, nc2] = [r2 + drc[ndir][0], c2 + drc[ndir][1]];
    let next = [-1, -1];

    if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] === 1 || visited[nr][nc][dir]) return next;
    if (nr2 < 0 || nc2 < 0 || nr2 >= N || nc2 >= N || board[nr2][nc2] === 1) return next;
    if (board[nr][nc] === 1 || board[nr2][nc2] === 1) return next;

    [next[0], next[1]] = [nr, nc];

    return next;
  }

  // rotate
  function rotate(r, c, dir, axis, ndir, depth) {
    if (ndir < 0) ndir = (ndir + 4) % 4;
    else if (ndir >= 4) ndir = ndir % 4;

    const [r2, c2] = [r + drc[dir][0], c + drc[dir][1]];
    const next = [-1, -1, dir];

    
    if (axis === 0) {
      const [nr, nc] = [r + drc[ndir][0], c + drc[ndir][1]];
      const [nr2, nc2] = [r2 + drc[ndir][0], c2 + drc[ndir][1]];

      if (nr < 0 || nc < 0 || nr >= N || nc >= N) return next;
      if (nr2 < 0 || nc2 < 0 || nr2 >= N || nc2 >= N) return next;
      if (board[nr][nc] === 1 || board[nr2][nc2] === 1) return next;
      if (visited[r][c][ndir]) return next;

      [next[0], next[1]] = [r, c];
    } else {
      const ndir2 = (ndir + 2) % 4;
      const [nr, nc] = [r + drc[ndir2][0], c + drc[ndir2][1]];
      const [nr2, nc2] = [r2 + drc[ndir2][0], c2 + drc[ndir2][1]];

      
      if (nr < 0 || nc < 0 || nr >= N || nc >= N) return next;
      if (nr2 < 0 || nc2 < 0 || nr2 >= N || nc2 >= N) return next;
      if (board[nr][nc] === 1 || board[nr2][nc2] === 1) return next;
      if (visited[nr2][nc2][ndir]) return next;

      [next[0], next[1]] = [nr2, nc2];
    }
    
    next[2] = ndir;
    return next;
  }
}

console.log(solution([[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]]));