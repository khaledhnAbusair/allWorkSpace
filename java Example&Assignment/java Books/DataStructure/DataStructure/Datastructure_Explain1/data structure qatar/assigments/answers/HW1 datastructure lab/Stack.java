/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QUEUS;

import data_structure.lab.pkg5.redo.*;
import com.sun.javafx.font.t2k.T2KFactory;

/**
 *
 * @author ProTectEdWall
 */
public class Stack<T> {

    private int counter;
    private T[] arrayReference;

    public Stack(int size) {

        arrayReference = (T[]) new Object[size];
        counter = -1;    //so that the stack is empty;

    }

    public void push(T elementToPush) {

        if (!isFull()) {

                arrayReference[++counter] = elementToPush;

        } else {
            throw new IndexOutOfBoundsException("sorry it is full");
        }

    }

    public T pop() {
        if (!isEmpty()) {

            return arrayReference[counter--]; //returns the top and then decreement it
        }
        return null;

    }

    public T peek() {

        if(!isEmpty())
        return arrayReference[counter];
        else
            return null;
    }

    public boolean isFull() {
        if (arrayReference.length-1 == counter ) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isEmpty() {
        if (counter == -1) {
            return true;
        } else {
            return false;
        }
    }

    public int size() { //return how many elements is in the array xD
        return counter + 1;
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            String concate = "";
            for (int i = 0; i <= counter; i++) {
                concate += arrayReference[i] + "\n";

            }
            return concate;
        }
        return null;
    }

}
