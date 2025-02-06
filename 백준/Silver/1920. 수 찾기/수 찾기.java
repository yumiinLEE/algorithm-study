
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		
		arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		
		for (int i=0; i<M; i++) {
			int tmp=Integer.parseInt(st.nextToken());
			sb.append(binarysearch(arr, tmp)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int binarysearch(int[] arr, int tmp) {
		int first=0;
		int last=arr.length-1;
		
		while(first<=last) {
			int mid=(first+last)/2;
			if (arr[mid]==tmp) {
				return 1;
			}
			if (arr[mid]<tmp) {
				first=mid+1;
			}else last=mid-1;
		}
		return 0;
	}
}