'''
표 병합 - 프로그래머스 Lv. 3(2023 KAKAO BLIND)
분류 : 구현, 유니온 파인드
'''
class Table:
    def __init__(self):
        self.table = [None] * 2500
        self.par = {i:[i] for i in range(2500)}

    def cal_idx(self, r, c):
        return 50 * r + c

    def find(self, x):
        while x != self.par[x][0]:
            x = self.par[self.par[x][0]][0]
        return x
    
    def union(self, x, y):
        x = self.find(x)
        y = self.find(y)
        self.par[y][0] = x
        self.par[x].append(y)

    def update1(self, r, c, value):
        idx = self.cal_idx(r, c)
        par_idx = self.find(idx)
        self.par[idx][0] = par_idx
        if idx not in self.par[par_idx]:
            self.par[par_idx].append(idx)
        self.table[par_idx] = value

    def update2(self, value1, value2):
        for i in range(2500):
            if self.table[i] != value1: continue
            self.table[i] = value2

    def merge(self, r1, c1, r2, c2):
        if r1 == r2 and c1 == c2: return
        idx1 = self.cal_idx(r1, c1)
        idx2 = self.cal_idx(r2, c2)
        par1 = self.find(idx1)
        par2 = self.find(idx2)
        if not self.table[par1] and self.table[par2]:
            par1, par2 = par2, par1
        if par1 != par2:
            self.union(par1, par2)

    def unmerge(self, r, c):
        idx = self.cal_idx(r, c)
        par_idx = self.find(idx)
        value = self.table[par_idx]
        self.table[par_idx] = None
        STACK = [par_idx]
        while STACK:
            v = STACK.pop()
            if len(self.par[v]) > 1:
                for w in self.par[v][1:]:
                    self.table[w] = None
                    STACK.append(w)
            self.par[v] = [v]
        self.table[idx] = value

    def add(self, r, c):
        idx = self.cal_idx(r, c)
        par_idx = self.find(idx)
        return self.table[par_idx] if self.table[par_idx] else "EMPTY"

def solution(commands):
    table = Table()
    answer = []
    for command in commands:
        lst = list(command.split())
        if lst[0] == "UPDATE":
            if len(lst) > 3:
                table.update1(int(lst[1])-1, int(lst[2])-1, str(lst[3]))
            else:
                table.update2(str(lst[1]), str(lst[2]))
        elif lst[0] == "MERGE":
            table.merge(*map(lambda x: int(x) - 1, lst[1:]))
        elif lst[0] == "UNMERGE":
            table.unmerge(*map(lambda x: int(x) - 1, lst[1:]))
        else:
            answer.append(table.add(*map(lambda x: int(x) - 1, lst[1:])))
    return answer

inputs = [
    ["MERGE 1 1 2 2", "MERGE 1 1 3 3", "UPDATE 3 3 A", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3"],
    ["UPDATE 1 1 A", "UPDATE 2 2 B", "UPDATE 3 3 C", "UPDATE 4 4 D", "MERGE 1 1 2 2", "MERGE 3 3 4 4", "MERGE 1 1 3 3", "UNMERGE 1 1", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3", "PRINT 4 4"],
]

for input in inputs:
    print('-------------CASE-----------------')
    print(solution(input))