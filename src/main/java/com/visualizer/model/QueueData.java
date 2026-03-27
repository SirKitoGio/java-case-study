package com.visualizer.model;

public class QueueData {
    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public QueueData(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue Full");
        }
        rear = (rear + 1) % capacity;
        array[rear] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }
        int value = array[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return array[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int[] getElements() {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = array[(front + i) % capacity];
        }
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getSize() {
        return size;
    }
    
    public int[] getRawArray() {
        return array;
    }
}
