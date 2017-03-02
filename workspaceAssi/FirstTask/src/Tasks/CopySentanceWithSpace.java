package Tasks;

import java.util.ArrayList;

public class CopySentanceWithSpace {

	public static void CopyWithSpace(int n) {
		
		
		
		ArrayList<Character> arrOrignal=new ArrayList<Character>();
		
		arrOrignal.add('a');
		arrOrignal.add('b');
		arrOrignal.add('c');
		ArrayList<Character> arr=new ArrayList<Character>();
		
		for (Character ch : arrOrignal) {
			arr.add(ch);
		}
		for (int  i=0;i<n;i++){
			arr.add('*');
		}
		
		System.out.print(arr);
	}
	public static void main(String[] args) {
		CopyWithSpace(5);

	}

}
