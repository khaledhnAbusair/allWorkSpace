package Tasks;

import java.util.ArrayList;

public class ReversArray {

	public static void main(String[] args) {
		
		ArrayList<String > arrayList=new ArrayList<String>();
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("c");
		StringBuilder sb=new StringBuilder(arrayList.toString());
		System.out.println(arrayList.toString());
		System.out.println(sb.reverse());
	}

}
