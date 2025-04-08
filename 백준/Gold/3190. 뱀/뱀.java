import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int head;
	static int tail;
	static int[] dx = {-1,0,1,0};//상우하좌
	static int[] dy = {0,1,0,-1};
	static int dIdx = 1;//방향 처음은 오른쪽
	static int N;
	static char[] directionList;
	static int ans;
	static Deque<int[]> dq = new ArrayDeque<int[]>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		directionList = new char[100000];
		int count = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < count;i++) {
			st = new StringTokenizer(br.readLine());
			int tempX = Integer.parseInt(st.nextToken());
			int tempY = Integer.parseInt(st.nextToken());
			map[tempX-1][tempY-1] =1; //1은 사과
		}
		//맵 초기화 완료
		
		count = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < count;i++) {
			st = new StringTokenizer(br.readLine());
			int second = Integer.parseInt(st.nextToken());
			char direc =st.nextToken().charAt(0);

			directionList[second] = direc;
			
		}
		
		//
		
		dq.add(new int[] {0,0});
		sol(0);
		System.out.println(ans);
		
		
	}
	public static void sol(int sec) {
		if(directionList[sec] =='L') {
			dIdx= (dIdx+3)%4;//방향전환
		}
		if(directionList[sec] =='D') {
			dIdx= (dIdx+5)%4;//방향전환
		}
		int ret = canGo();
		if(ret == -1 || ret == 2) { //못갈때
			ans =sec+1;
			return;
		}
		else {
			sol(sec+1);
		}
	}
	public static int canGo(){
		int[] tempHead = dq.getFirst();
		int next=-1;
		int nextX= tempHead[0]+dx[dIdx];
		int nextY=tempHead[1]+dy[dIdx];
		if(nextX>=0 && nextY>=0 &&nextX<N && nextY<N) {
			if(map[nextX][nextY]==1) {//사과일경우 꼬리는그대로 두고머리만 한칸전진
				dq.addFirst(new int[] {nextX,nextY});
				map[nextX][nextY]= 2;//뱀으로변경
				next = 1;
			}
			else if(map[nextX][nextY]==2) {//뱀일경우 못감
				next = 2;
			}
			else { //아무것도아닌경우
				dq.addFirst(new int[] {nextX,nextY});
				int tempTail[] = dq.pollLast();//꼬리제거 꼬리부분 0으로 바꿔줘야함
				map[tempTail[0]][tempTail[1]]=0;
				map[nextX][nextY]= 2;//뱀으로변경
				next = 0;
			}
			
		}
		else {
			next = -1; //맵나감
		}
		
		return next;
		
	}
}
