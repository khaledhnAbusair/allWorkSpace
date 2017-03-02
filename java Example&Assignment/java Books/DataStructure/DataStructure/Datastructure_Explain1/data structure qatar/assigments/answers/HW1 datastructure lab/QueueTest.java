/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QUEUS;

/**
 *
 * @author ProTectEdWall
 */
public class QueueTest {

    public static void main(String[] args) {
        Cqueue<Character> myqueue = new Cqueue<>(6);
        myqueue.insert('H');   //olleh
        myqueue.insert('E');
        myqueue.insert('L');
        myqueue.insert('L');
        myqueue.insert('O');
        
        
        System.out.println("peek is " + myqueue.peek());
        System.out.println("removing " + myqueue.remove());
        System.out.println(myqueue);
        
        reverseQueue(myqueue);
        System.out.println(myqueue);
        
    }
    
    public static <T> void reverseQueue(Cqueue<T> myque) {
        Stack<T> mystack = new Stack<>(myque.size());
        T temp;
        while (!myque.isEmpty()) {
            temp = myque.remove();
          System.out.println("removing"+temp);
            mystack.push(temp);
            
        }
        System.out.println("---------");
        while (!mystack.isEmpty()) {
            temp = mystack.pop();
            System.out.println("pushing "+temp);
            myque.insert(temp);
            
        }
        
    }
    
}
