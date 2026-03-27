package com.visualizer.model;

public class StackData {
    private int[] array;
    private int top;
    private int capacity;

    public StackData(int size) {
        this.capacity = size;
        this.array = new int[size];
        this.top = -1;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("Stack Overflow");
        }
        array[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        return array[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");
        }
        return array[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public int[] getElements() {
        int[] result = new int[top + 1];
        System.arraycopy(array, 0, result, 0, top + 1);
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTop() {
        return top;
    }
}
