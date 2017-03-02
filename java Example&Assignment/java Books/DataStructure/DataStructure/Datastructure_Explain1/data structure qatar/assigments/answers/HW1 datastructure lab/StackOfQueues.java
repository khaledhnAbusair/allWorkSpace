/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QUEUS;

import java.util.Queue;

/**
 *
 * @author ProTectEdWall
 */
public class StackOfQueues<T> {

    private Stack<T> mystack;
    private Cqueue<Character> firstqueus;
    private int counter;
    private int top;
    private Cqueue<Character> TempPeek;

    public StackOfQueues(int size) {
        mystack = new Stack<>(size);
        firstqueus = new Cqueue<>(5);
        top = -1;
        counter = 0;
        mystack.push((T) firstqueus);
    }

    public void addchar(char x) {

        if (!mystack.isEmpty()) { //checking if the stack is not empty so that the peek can be valid;
            TempPeek = (Cqueue) mystack.peek();
        } else if (mystack.isEmpty()) { //if empty stack then make new queue and add the element to it
            Cqueue<Character> newQueue = new Cqueue<>(5);

            mystack.push((T) newQueue);
            TempPeek = (Cqueue) mystack.peek();

        }

        if (!TempPeek.isFull()) { //if the queue at the top has a room for the char
            TempPeek.insert(x);

        } else if (TempPeek.isFull() && !mystack.isFull()) { //if it doesnt has any room but the stack can be extended
            Cqueue<Character> newQueue = new Cqueue<>(5);

            newQueue.insert(x);
            mystack.push((T) newQueue);

        } else { //if the stack is full and top queue is full then then there no room at all so just print not availble
            System.out.println("sorry can not add the element");
        }

    }

    public char removeChar() {
        Character c = null;

        if (mystack.isEmpty()) { //just for handelling exception
            System.out.println("Sorry there is nothing to be poped");
           throw new IndexOutOfBoundsException("Sorry there is nothing to be poped");
        } else if (!mystack.isEmpty()) {
            TempPeek = (Cqueue) mystack.peek();
            if (TempPeek.isEmpty()) {
                mystack.pop();
            }

            if (!TempPeek.isEmpty()) {
                c = TempPeek.remove2();
                if (TempPeek.isEmpty()) {//if the queue has became empty then pop it 
                    mystack.pop();
                }
//queue . remove
                return c;
            }

        }
        return c;
    }

    public int size() {
        
        if(!mystack.isEmpty()){
        int size_of_all_queus_exceptLastOne = mystack.size() - 1;
        TempPeek = (Cqueue<Character>) mystack.peek();
        return TempPeek.size() + ((size_of_all_queus_exceptLastOne) * 5);
        }
        else
            return 0;

    }

    public void display() {
        if(!mystack.isEmpty())
        System.out.println(mystack);
        else
            System.out.println("nothing in the stack");
           
    }

}
