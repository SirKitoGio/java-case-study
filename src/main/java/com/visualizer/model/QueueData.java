package com.visualizer.model;

public class QueueData {
    private int[] array;
    private int size;
    private int capacity;

    public QueueData(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
    }

    public void enqueue(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue Full");
        }
        array[size] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Empty");
        }
        int value = array[0];
        // Shift elements to the left
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return array[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int[] getElements() {
        int[] result = new int[size];
        System.arraycopy(array, 0, result, 0, size);
        return result;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }
    
    public int[] getRawArray() {
        return array;
    }
}
