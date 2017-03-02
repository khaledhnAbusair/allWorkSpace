//
import java.util.Random ;

// node class
class node {
	public int val ;
	public node next ;
	
	node(int v) {
		val= v ;
	}
}

// list class
class marinersList {
	public node head;

	public marinersList() {
		head = null;
	}

	public node getHead() {
		return head ;
	}
	public String toString() {
		String s="" ;
		node cur = head ;
		while(cur.next !=head) {
			s+="["+cur.val+"]"+"->" ;
			cur = cur.next ;
		}
		s+="["+cur.val+"]" ;
		return s ;
	}
	
	public boolean isEmpty() {
		return head == null ;
	}
	
	// on the top of the list
	public void addMariner(int v) {
		node myNew = new node(v) ;
		
		// if list empty
		if(isEmpty()) {
			myNew.next = myNew ;
		}
		else {//find the last node
			node n = head ;
			while(n.next!=head) n = n.next ;
			n.next = myNew ;
			myNew.next = head ;
		}
		head = myNew ;
	}
	
	public void eatMariner(int v) {
		node cur= head ;
		node pred=  head ;
		//find v
		while(cur.val!=v) {
			pred= cur ;
			cur= cur.next ;
		}
		
		// if we remove the first node
		if(cur==head) {
			//find the last node
			node n = head ;
			while(n.next!=head) n = n.next ;
			n.next = head.next ;
			head= head.next ;
		}
		else {
			pred.next = cur.next ;
		}
	}
}

class marinersListApp {
	public static void main(String[] args) {
		marinersList ml = new marinersList() ;
		ml.addMariner(9);
		ml.addMariner(8);
		ml.addMariner(7);		
		ml.addMariner(6);		
		ml.addMariner(5);		
		ml.addMariner(4);		
		ml.addMariner(3);
		ml.addMariner(2);		
		ml.addMariner(1);		
		
		Random rn = new Random();
		int firstPos = rn.nextInt(9) + 1;
        System.out.println("Count 5 by 5 starting from mariner "+firstPos);

        // Find eatFirst
        node n= ml.getHead() ;
        for(int i=0; i<firstPos-1; i++) n= n.next;
        
        // Find those to be eaten
        node pred = null ;
        for (int mar=0 ; mar<(9-1) ; mar++){
            // print alive mariners
            System.out.println(ml);
            for (int lg=0; lg<5; lg++){pred = n ; n = n.next ;}
            System.out.println("Mariner "+pred.val+" has been eaten");
            ml.eatMariner(pred.val); 
        }
        // print the last living mariner
        System.out.println(ml+" -> I am the only mariner who remains alive !");
	}
}

