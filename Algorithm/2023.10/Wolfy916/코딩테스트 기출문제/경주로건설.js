
/** 
 * 경주로 건설 - 프로그래머스
 * 분류: 그래프탐색
 * */
function solution(board) {
    const N = board.length;
    const INF = 10**7;
    const visited = Array.from({length: N}, () => {
        return Array.from({length: N}, () => {
            return Array.from({length: 4}, () => INF);
        });
    });
    const delta = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    const comb = new Map([['00', 0], ['01', 1], ['10', 2], ['11', 3]]);
    const delta_map = new Map([
        [-1, [1, 3]],[0, [2, 3, 0]],[1, [2, 3, 1]],
        [2, [0, 1, 2]],[3, [0, 1, 3]],
    ]);
    visited[0][0] = [0, 0, 0, 0];
    dfs(0, 0, -1, 0, 0);
    return Math.min(...visited[N-1][N-1]);

    function dfs(vi, vj, vr, vk, vl) {
        if (vi === N - 1 && vj === N - 1) {
            return;
        } else {
            let idx = -1;
            for (let nr of delta_map.get(vr)) {
                idx++;
                let [di, dj] = delta[nr];
                let [ni, nj] = [vi + di, vj + dj];
                if (ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
                if (board[ni][nj]) continue;
                let c = idx < 2 && vr > -1 ? 600 : 100;
                let nk = ~~(c/600);
                let nl = comb.get(vk + '' + nk);
                if (visited[vi][vj][vl] + c <= visited[ni][nj][nl]) {
                    visited[ni][nj][nl] = visited[vi][vj][vl] + c;
                    dfs(ni, nj, nr, nk, nl);
                };
            }
        }
        return;
    };
}