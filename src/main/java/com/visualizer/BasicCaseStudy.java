package com.visualizer;

import java.util.Scanner;


public class BasicCaseStudy {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("DATA STRUCTURES AND ALGORITHMS: MIDTERM CASE STUDY");
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        Stack stack = new Stack(size);
        Queue queue = new Queue(size);

        boolean running = true;
        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. STACK");
            System.out.println("2. QUEUE");
            System.out.println("3. EXIT");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    stackMenu(stack);
                    break;
                case 2:
                    queueMenu(queue);
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void stackMenu(Stack stack) {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== STACK SUB-MENU =====");
            System.out.println("1. Push (Add element)");
            System.out.println("2. Pop (Remove element)");
            System.out.println("3. Peek (View top)");
            System.out.println("4. IsEmpty?");
            System.out.println("5. IsFull?");
            System.out.println("6. View Array Elements");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (stack.isFull()) {
                        System.out.println("Stack Overflow! Cannot push.");
                    } else {
                        System.out.print("Enter value to push: ");
                        int val = scanner.nextInt();
                        stack.push(val);
                        stack.display();
                    }
                    break;
                case 2:
                    if (stack.isEmpty()) {
                        System.out.println("Stack Underflow! Nothing to pop.");
                    } else {
                        System.out.println("Popped: " + stack.pop());
                        stack.display();
                    }
                    break;
                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty.");
                    } else {
                        System.out.println("Top element: " + stack.peek());
                    }
                    break;
                case 4:
                    System.out.println("IsEmpty: " + stack.isEmpty());
                    break;
                case 5:
                    System.out.println("IsFull: " + stack.isFull());
                    break;
                case 6:
                    stack.display();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void queueMenu(Queue queue) {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== QUEUE SUB-MENU =====");
            System.out.println("1. Enqueue (Add to end)");
            System.out.println("2. Dequeue (Remove from front)");
            System.out.println("3. Peek (View front)");
            System.out.println("4. IsEmpty?");
            System.out.println("5. IsFull?");
            System.out.println("6. View Array Elements");
            System.out.println("7. Back to Main Menu");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (queue.isFull()) {
                        System.out.println("Queue Full! Cannot enqueue.");
                    } else {
                        System.out.print("Enter value to enqueue: ");
                        int val = scanner.nextInt();
                        queue.enqueue(val);
                        queue.display();
                    }
                    break;
                case 2:
                    if (queue.isEmpty()) {
                        System.out.println("Queue Empty! Nothing to dequeue.");
                    } else {
                        System.out.println("Dequeued: " + queue.dequeue());
                        queue.display();
                    }
                    break;
                case 3:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Front element: " + queue.peek());
                    }
                    break;
                case 4:
                    System.out.println("IsEmpty: " + queue.isEmpty());
                    break;
                case 5:
                    System.out.println("IsFull: " + queue.isFull());
                    break;
                case 6:
                    queue.display();
                    break;
                case 7:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static class Stack {
        private int[] arr;
        private int top;
        private int capacity;

        public Stack(int size) {
            this.capacity = size;
            this.arr = new int[size];
            this.top = -1;
        }

        public void push(int x) {
            arr[++top] = x;
        }

        public int pop() {
            return arr[top--];
        }

        public int peek() {
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public boolean isFull() {
            return top == capacity - 1;
        }

        public void display() {
            System.out.print("Stack (Array view): [");
            for (int i = 0; i < capacity; i++) {
                if (i <= top) {
                    System.out.print(arr[i]);
                } else {
                    System.out.print("_");
                }
                if (i < capacity - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }



    static class Queue {
        private int[] arr;
        private int front, rear, size, capacity;

        public Queue(int size) {
            this.capacity = size;
            this.arr = new int[size];
            this.front = 0;
            this.size = 0;
            this.rear = -1;
        }

        public void enqueue(int x) {
            rear = (rear + 1) % capacity;
            arr[rear] = x;
            size++;
        }

        public int dequeue() {
            int x = arr[front];
            front = (front + 1) % capacity;
            size--;
            return x;
        }

        public int peek() {
            return arr[front];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        public void display() {
            System.out.print("Queue (Array view): [");
            for (int i = 0; i < capacity; i++) {
                boolean isActive = false;
                if (size > 0) {
                    if (front <= rear) {
                        isActive = (i >= front && i <= rear);
                    } else {
                        isActive = (i >= front || i <= rear);
                    }
                }
                
                if (isActive) {
                    System.out.print(arr[i]);
                } else {
                    System.out.print("_");
                }
                if (i < capacity - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
}
