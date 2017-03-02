package Tasks;

import java.util.Scanner;

public class ToLowerCase {

	public static void main(String[] args) {
		System.out.println("Enter sentance ");
		Scanner in = new Scanner(System.in);
		String lower=in.nextLine();
			
		 for (int i = 0; i< lower.length(); i++)
	        {
	            char str = lower.charAt(i);
	            if (65 <= str && str<=90)
	            {
	                str = (char)( str + 32 ); 
	            }
	            System.out.print(str);
	         }
		 in.close();
	}

}
