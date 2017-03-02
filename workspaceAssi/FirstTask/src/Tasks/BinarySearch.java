package Tasks;

import java.util.Scanner;

public class BinarySearch {

	private static Scanner in;
	private static Scanner in2;
	


	public static void main(String[] args) {
		
		int c, first, last, middle, n, search, array[];
		 
	    in2 = new Scanner(System.in);
	    System.out.println("Enter number of elements");
	    n = in2.nextInt(); 
	    array = new int[n];
	 
	    System.out.println("Enter " + n + " integers");
	 
	 
	    for (c = 0; c < n; c++)
	      array[c] = in2.nextInt();
	 
	    System.out.println("Enter value to find");
	    search = in2.nextInt();
	 
	    first  = 0;
	    last   = n - 1;
	    middle = (first + last)/2;
	 
	    while( first <= last )
	    {
	      if ( array[middle] < search )
	        first = middle + 1;    
	      else if ( array[middle] == search ) 
	      {
	        System.out.println(search + " found at location " + (middle + 1) + ".");
	        break;
	      }
	      else
	         last = middle - 1;
	 
	      middle = (first + last)/2;
	   }
	   if ( first > last )
	      System.out.println(search + " is not present in the list.\n");
	   in.close();
	   in2.close();
	}
	

}
