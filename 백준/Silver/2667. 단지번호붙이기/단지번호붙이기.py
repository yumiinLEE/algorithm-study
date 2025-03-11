from sys import stdin

def bfs(si,sj):
    cnt=1
    
    q=[]
    q.append((si,sj))
    v[si][sj]=1
    
    while q:
        ci,cj=q.pop(0)
        for di,dj in (-1,0),(1,0),(0,1),(0,-1):
            ni,nj=ci+di,cj+dj
            if 0<=ni<N and 0<=nj<N and v[ni][nj]==0 and arr[ni][nj]==1:
                v[ni][nj]=1
                q.append((ni,nj))
                cnt+=1
    
    return cnt


N=int(stdin.readline().strip())

v=[[0]*N for _ in range(N)]

arr=[list(map(int,stdin.readline().strip())) for _ in range(N)]

ans=[]

for i in range(N):
    for j in range(N):
        if arr[i][j]==1 and v[i][j]==0:
            ans.append(bfs(i,j))
            
ans.sort()
print(len(ans),*ans,sep='\n')