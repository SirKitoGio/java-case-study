package com.visualizer;

/* =================
   This imports the Scanner class from Java's utility library.
   We need this tool to read input from the user (like when you type numbers on your keyboard).
   ================= */
import java.util.Scanner;

/* =================
   This is the main class declaration. Everything in our program lives inside here.
   ================= */
public class BasicCaseStudy {

    /* =================
       Here we create a single 'Scanner' object named 'scanner'.
       It is 'static', meaning it belongs to the class itself and can be shared 
       by all the methods (main Menu, stackMenu, queueMenu) without having to recreate it.
       'System.in' tells the scanner to read from the standard input (the console).
       ================= */
    private static Scanner scanner = new Scanner(System.in);

    /* =================
       The 'main' method is the starting point of the program. 
       When you click "Run", the computer starts reading the code sequentially from here.
       ================= */
    public static void main(String[] args) {
        
        /* =================
           Sequence 1: The program prints a welcome message and asks for an array size.
           The user types a number, and 'scanner.nextInt()' grabs that number.
           We store that number in a new integer variable called 'size'.
           ================= */
        System.out.println("DATA STRUCTURES AND ALGORITHMS: MIDTERM CASE STUDY");
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        /* =================
           Sequence 2: Now that we have the 'size', we create two new objects:
           1. A 'Stack' object named 'stack'
           2. A 'Queue' object named 'queue'
           We pass the 'size' variable into their constructors to set their maximum capacity.
           ================= */
        Stack stack = new Stack(size);
        Queue queue = new Queue(size);

        /* =================
           Sequence 3: We create a boolean variable 'running' and set it to true.
           This starts our main loop. As long as 'running' is true, the program will 
           keep showing the Main Menu over and over again.
           ================= */
        boolean running = true;
        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. STACK");
            System.out.println("2. QUEUE");
            System.out.println("3. EXIT");
            System.out.print("Choose option: ");
            
            /* =================
               The user types their menu choice (1, 2, or 3).
               We store this in the integer variable 'choice'.
               ================= */
            int choice = scanner.nextInt();

            /* =================
               Sequence 4: A 'switch' statement looks at the 'choice' variable.
               If it's 1: We jump to the stackMenu method and pass our 'stack' object to it.
               If it's 2: We jump to the queueMenu method and pass our 'queue' object to it.
               If it's 3: We change 'running' to false, which breaks the loop and ends the program.
               ================= */
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

    /* =================
       This is the Sub-Menu for the Stack. 
       It requires a 'Stack' object to be passed in (which we created in the main method).
       ================= */
    private static void stackMenu(Stack stack) {
        
        /* =================
           Similar to the main menu, we create a boolean 'back' set to false.
           The while loop keeps us trapped in the Stack menu until the user wants to go back.
           ================= */
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
            
            int choice = scanner.nextInt(); // Gets the user's action choice

            /* =================
               This switch handles the Stack operations based on what the user picked.
               ================= */
            switch (choice) {
                case 1:
                    /* =================
                       PUSH: First, check if the stack is already full using stack.isFull().
                       If yes, warn the user. If no, ask for a number, store it in 'val', 
                       and use stack.push(val) to add it. Then display the updated stack.
                       ================= */
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
                    /* =================
                       POP: Check if the stack is empty first. You can't remove what isn't there!
                       If not empty, call stack.pop(), which removes and returns the top number.
                       ================= */
                    if (stack.isEmpty()) {
                        System.out.println("Stack Underflow! Nothing to pop.");
                    } else {
                        System.out.println("Popped: " + stack.pop());
                        stack.display();
                    }
                    break;
                case 3:
                    /* =================
                       PEEK: Just looks at the top element without removing it.
                       Checks if it's empty first to avoid errors.
                       ================= */
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
                    /* =================
                       BACK: Sets 'back' to true. The while loop condition (!back) becomes false,
                       the loop breaks, and the code returns to the Main Menu.
                       ================= */
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /* =================
       This is the Sub-Menu for the Queue.
       It works exactly like the Stack menu, but uses a 'Queue' object and queue terminology.
       ================= */
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
                    /* =================
                       ENQUEUE: Check if queue is full. If not, get a value ('val') 
                       from the user and add it to the back of the queue.
                       ================= */
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
                    /* =================
                       DEQUEUE: Check if queue is empty. If not, remove the item 
                       from the front of the queue and print it.
                       ================= */
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

    /* =================
       This is the blueprint for our Stack data structure.
       A stack works like a stack of plates: Last In, First Out (LIFO).
       ================= */
    static class Stack {
        // 'arr' is the actual array that holds the numbers
        private int[] arr;
        // 'top' keeps track of the index of the highest item in the stack
        private int top;
        // 'capacity' is the maximum number of items the stack can hold
        private int capacity;

        /* =================
           The Constructor: This is called when we use "new Stack(size)".
           The 'size' passed from the main menu becomes the 'capacity'.
           We create an empty array of that size.
           'top' starts at -1 because an index of 0 would mean there is 1 item. -1 means empty.
           ================= */
        public Stack(int size) {
            this.capacity = size;
            this.arr = new int[size];
            this.top = -1;
        }

        /* =================
           PUSH: '++top' increases the top index by 1 FIRST, 
           then we place the new number 'x' into that new array slot.
           ================= */
        public void push(int x) {
            arr[++top] = x;
        }

        /* =================
           POP: We grab the number currently at 'top' to return it.
           'top--' then decreases the top index by 1, effectively "forgetting" the top item.
           ================= */
        public int pop() {
            return arr[top--];
        }

        /* =================
           PEEK: Just looks at the array at the 'top' index and returns that number.
           ================= */
        public int peek() {
            return arr[top];
        }

        /* =================
           ISEMPTY: Returns 'true' if top is -1 (meaning no items have been pushed).
           ================= */
        public boolean isEmpty() {
            return top == -1;
        }

        /* =================
           ISFULL: Because arrays start at index 0, the last slot is (capacity - 1).
           If top has reached that number, the stack is full.
           ================= */
        public boolean isFull() {
            return top == capacity - 1;
        }

        /* =================
           DISPLAY: This prints the stack visually.
           It loops through every possible slot from 0 up to 'capacity'.
           If the current slot 'i' is less than or equal to 'top', it prints the number.
           Otherwise, it prints a "_" to show an empty slot.
           ================= */
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


    /* =================
       This is the blueprint for our Queue data structure.
       A queue works like a line at a store: First In, First Out (FIFO).
       This specific implementation is a "Circular Queue" to reuse empty spaces.
       ================= */
    static class Queue {
        private int[] arr;
        // 'front' tracks where the line starts (where to dequeue)
        private int front;
        // 'rear' tracks the end of the line (where to enqueue)
        private int rear;
        // 'size' tracks how many actual items are in the queue right now
        private int size;
        // 'capacity' is the max size
        private int capacity;

        /* =================
           Constructor: Sets up the empty queue.
           'front' starts at 0. 'rear' starts at -1. 'size' is 0.
           ================= */
        public Queue(int size) {
            this.capacity = size;
            this.arr = new int[size];
            this.front = 0;
            this.size = 0;
            this.rear = -1;
        }

        /* =================
           ENQUEUE: Adds a number 'x' to the back of the line.
           Because it's circular, we use '% capacity' (modulo).
           If capacity is 5 and rear is 4, (4+1)%5 = 0. It wraps around to the beginning!
           Then we save 'x' to that index and increase 'size' by 1.
           ================= */
        public void enqueue(int x) {
            rear = (rear + 1) % capacity;
            arr[rear] = x;
            size++;
        }

        /* =================
           DEQUEUE: Grabs the number at the 'front' index.
           Then it moves 'front' up by 1 (again using modulo to wrap around if needed).
           It reduces 'size' by 1 and returns the number we grabbed.
           ================= */
        public int dequeue() {
            int x = arr[front];
            front = (front + 1) % capacity;
            size--;
            return x;
        }

        /* =================
           PEEK: Returns the number at the front of the line without removing it.
           ================= */
        public int peek() {
            return arr[front];
        }

        /* =================
           ISEMPTY / ISFULL: Simple checks against the 'size' tracker variable.
           ================= */
        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }

        /* =================
           DISPLAY: Prints the queue visually. This is slightly complex because 
           the queue wraps around in a circle.
           It loops through all array slots. We use a boolean 'isActive' to see if 
           the current slot 'i' holds a real number in our line.
           - If front <= rear: It's a normal line. Any slot between them is active.
           - If front > rear: The line wrapped around! Slots are active if they are 
             at the end of the array OR the beginning of the array.
           If active, print the number. Otherwise, print "_".
           ================= */
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