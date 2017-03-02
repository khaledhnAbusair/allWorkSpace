import java.util.*;

// Class Farey Seq extends the Java LinkedList class
class FareySeq extends LinkedList<Fraction>
{
	// added for serialisation
	private static final long serialVersionUID = 1L;

	public FareySeq(){
		super();
	}

	public void display()
	{
		for (int i = 0; i < size(); i++) System.out.print(get(i));
		System.out.println();
	}
}

// The class Fraction
class Fraction
{
	int Num, Den ;

	public Fraction(int a, int b)
	{
		Num = a;
		Den = b;
	}

	public String toString()
	{
		return Num + "/" + Den + ", ";
	}
}


// The test class containing the main() function
class FareySeqApp
{
	public static void main(String[] args)
	{
		FareySeq fList = new FareySeq(); 
		Fraction f1 = new Fraction(0,1);
		Fraction f2 = new Fraction(1,1);

		try{         
			fList.add(f1);
			fList.add(f2);
		}catch(IndexOutOfBoundsException ex){}

		Scanner ab = new Scanner(System.in);
		int userInput;
		System.out.print("The current sequence is ");
		fList.display();
		System.out.println("Please enter the number of terms to expand the sequence:");
		userInput = ab.nextInt();
		
		// Generate new sequences and add them to the list. The new list is returned
		FareySeq newList = fList;
		for(int i = 1; i <= userInput;i++)
		{
			newList = insertFraction(newList,i);
		}
		System.out.println("The list after expanding is:");
		newList.display();
	}

	public static FareySeq insertFraction(FareySeq fL, int n)
	{       
		Fraction fNode1,fNode2;
		int newSize = fL.size() - 1;   
		for ( int i = 0; i < newSize; i++)
		{
			fNode1 = (Fraction) fL.get(i);
			fNode2 = (Fraction) fL.get(i+1);
	
			if (fNode1.Den + fNode2.Den <= n)
			{
				int newNum = fNode1.Num + fNode2.Num;
				int newDen = fNode1.Den + fNode2.Den;
				Fraction newNode = new Fraction(newNum, newDen);
				fL.add(i+1,newNode);
				newSize++;  // increment newsize for the list to expand after adding terms 
			}
		}
		return fL;
	}
}
