import java.io.*;

public class Solution {
	static Node[] tree;
	static StringBuilder sb;
	
	static class Node {
		String s;
		int left;
		int right;
		
		public Node (String s, int left, int right){
			this.s = s;
			this.left = left;
			this.right = right;
		}
	}
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 1; t <= 10; t++) {
        	int N = Integer.parseInt(br.readLine());
        	sb = new StringBuilder();
        	
        	tree = new Node[N+1];
        	
        	for (int i = 1; i < N+1; i++) {
        		String[] line = br.readLine().split(" ");
        		tree[i] = new Node(line[1], 0, 0);
        		
        		if (line.length>2) {
        			tree[i].left = Integer.parseInt(line[2]);
				}
        		
        		if (line.length>3) {
        			tree[i].right = Integer.parseInt(line[3]);
				}
			}
        	
    		inorder(1);
    		
    		System.out.println("#" + t + " " + sb.toString());
		}
    }

	static void inorder(int index) {
		if (index == 0) {
			return;
		}
		inorder(tree[index].left);
		sb.append(tree[index].s);
		inorder(tree[index].right);
	}
}