/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QUEUS;

import javax.print.PrintException;

/**
 *
 * @author ProTectEdWall
 */
public class Cqueue<T> {

    private int front;
    private int rear;
    private int counter;
    private int maxSize;
    private T[] elements;

    public Cqueue(int size) {
        front = 0;
        rear = -1;
        counter = 0;
        maxSize = size;
        elements = (T[]) new Object[size];

    }

    public boolean isEmpty() {
        if (counter == 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isFull() {

        if (counter == maxSize) {

            return true;
        }
        return false;

    }

    public T peek() {
        return elements[front];

    }

    public void insert(T elementToBeAdd) {
        if (!isFull()) {
            elements[++rear] = elementToBeAdd;
            if (rear == maxSize - 1) {
                rear = 0; //handeling the circular queus
            }
            counter++;
        } else {
            throw new IndexOutOfBoundsException("Sorry you cannot insert more ");
        }

    }

    public T remove() {

        if (!isEmpty()) {
            T temp = elements[front++];
            if (front == maxSize) {
                front = 0;
            }
            counter--;
            return temp;
        }
        throw new IndexOutOfBoundsException("Sorry cannot remove from the queue");
    }

    @Override
    public String toString() {
        if (!isEmpty()) {
            String contentsOfTheQueue = "";

            for (int i = 0; i < counter; i++) {
                contentsOfTheQueue += elements[(front + i) % maxSize];
            }
            return contentsOfTheQueue;
        }
        return ("No elements to display");

    }

    public int size() {
        return counter;
    }

    public void insert2(T e) {

        if (counter == elements.length) {
            System.out.println("queue is full");
        } else {
            rear = (rear + 1) % elements.length;
        }
        elements[rear] = e;
        if (front == -1) {
            front++;
        }
        counter++;
    }

    public T remove2() {
        if (counter == 0) {
            System.out.println("queu is empty");
            return null;
        } else {
            T myel = elements[front];
            front = (front + 1) % elements.length;
            counter--;
            if (counter == 0) {
                front = rear = -1;
            }
            return myel;
        }

    }

    public String display2() {
        String x = "";
        if (front <= rear && !isEmpty()) {
            for (int i = front; i <= rear; i++) {
                x += elements[i];
            }
        } else {
            for (int i = front; i < maxSize - 1; i++) {
                x += elements[i];
            }
            for (int i = 0; i <= rear; i++) {
                x += elements[i];
            }

        }

        return x;

    }

}
