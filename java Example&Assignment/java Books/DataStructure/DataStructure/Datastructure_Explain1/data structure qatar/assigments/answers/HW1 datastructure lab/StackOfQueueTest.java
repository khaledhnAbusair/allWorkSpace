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
public class StackOfQueueTest {

    public static void main(String[] args) {
        StackOfQueues<Character> myStack = new StackOfQueues<>(2);

        myStack.addchar('h');  // to be inserted  HELLO THERE
        myStack.addchar('e');
        myStack.addchar('l');
        myStack.addchar('l');
        myStack.addchar('0');
        myStack.addchar('t');
        myStack.addchar('h');
        myStack.addchar('e');
        myStack.addchar('r');
        myStack.addchar('e');
    //    myStack.addchar('d');  //@test
        System.out.println("Displaying content of the strcture  before deleting:{ ");

        myStack.display();
        System.out.println("}");

        System.out.println("SIZE OF THE STRCTURE IS : " + myStack.size());
        System.out.println("======================");
        myStack.removeChar();
        myStack.removeChar();
        

        System.out.println("Displaying content of the strcture after deleting :{ ");

        myStack.display();
        System.out.println("}");

        System.out.println("SIZE OF THE STRCTURE IS : " + myStack.size()); //Displaying size 
        System.out.println("======================");
    }

}
