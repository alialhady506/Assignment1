package alialhadi;



import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Assignment1
{



        // ====================================================================
        // Question 1: Write a function to reverse a string using Stack.
        // ====================================================================
        public static String reverseStringUsingStack(String str) {
            Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) {
                stack.push(c);
            }

            StringBuilder reversedString = new StringBuilder();
            while (!stack.isEmpty()) {
                reversedString.append(stack.pop());
            }
            return reversedString.toString();
        }

        // ====================================================================
        // Question 2: Write a function to sort a stack using only another Stack.
        // (Uses an auxiliary stack to perform a sort similar to Insertion Sort)
        // ====================================================================
        public static Stack<Integer> sortStack(Stack<Integer> inputStack) {
            Stack<Integer> tempStack = new Stack<>();
            while (!inputStack.isEmpty()) {
                int temp = inputStack.pop();
                // While tempStack is not empty and its top is greater than temp
                while (!tempStack.isEmpty() && tempStack.peek() > temp) {
                    inputStack.push(tempStack.pop());
                }
                tempStack.push(temp);
            }
            return tempStack;
        }

        // ====================================================================
        // Question 3: Write a function to reverse the order of elements in a queue.
        // (Uses a Stack as an auxiliary data structure)
        // ====================================================================
        public static <T> void reverseQueue(Queue<T> queue) {
            Stack<T> stack = new Stack<>();
            // 1. Dequeue all elements and push them onto the stack
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            // 2. Pop all elements from the stack and enqueue them back into the queue
            while (!stack.isEmpty()) {
                queue.offer(stack.pop());
            }
        }

        // ====================================================================
        // Question 4: Implement a priority queue where the smallest element is dequeue first.
        // (Using Java's built-in PriorityQueue which is a Min-Heap by default)
        // ====================================================================
        public static class MinPriorityQueue<T extends Comparable<T>> {
            private PriorityQueue<T> pq;

            public MinPriorityQueue() {
                // PriorityQueue is a Min-Heap by default
                this.pq = new PriorityQueue<>();
            }

            public void enqueue(T element) {
                pq.offer(element); // Adds the element to the queue
            }

            public T dequeue() {
                return pq.poll(); // Retrieves and removes the smallest element
            }

            public boolean isEmpty() {
                return pq.isEmpty();
            }
        }

        // ====================================================================
        // Question 5: Write a function to merge two sorted queues into a single sorted queue.
        // (Uses a standard merge algorithm)
        // ====================================================================
        public static <T extends Comparable<T>> Queue<T> mergeSortedQueues(Queue<T> q1, Queue<T> q2) {
            Queue<T> mergedQueue = new LinkedList<>();

            while (!q1.isEmpty() && !q2.isEmpty()) {
                if (q1.peek().compareTo(q2.peek()) <= 0) {
                    mergedQueue.offer(q1.poll());
                } else {
                    mergedQueue.offer(q2.poll());
                }
            }

            // Add remaining elements from q1
            while (!q1.isEmpty()) {
                mergedQueue.offer(q1.poll());
            }

            // Add remaining elements from q2
            while (!q2.isEmpty()) {
                mergedQueue.offer(q2.poll());
            }

            return mergedQueue;
        }

        // Main method to demonstrate all functions
        public static void main(String[] args) {
            System.out.println("--- Data Structure Assignment 1 Solutions ---");

            // Q1 Demonstration
            String originalStr = "Hello";
            String reversedStr = reverseStringUsingStack(originalStr);
            System.out.println("\nQ1: Reverse String");
            System.out.println("Original: " + originalStr + ", Reversed: " + reversedStr); // olleH

            // Q2 Demonstration
            Stack<Integer> unsortedStack = new Stack<>();
            unsortedStack.push(34);
            unsortedStack.push(3);
            unsortedStack.push(31);
            unsortedStack.push(98);
            unsortedStack.push(92);
            System.out.println("\nQ2: Sort Stack");
            System.out.println("Unsorted Stack (Top to Bottom): " + unsortedStack);
            Stack<Integer> sortedStack = sortStack(unsortedStack);
            System.out.println("Sorted Stack (Top to Bottom): " + sortedStack); // 3, 31, 34, 92, 98

            // Q3 Demonstration
            Queue<Integer> originalQueue = new LinkedList<>();
            originalQueue.offer(10);
            originalQueue.offer(20);
            originalQueue.offer(30);
            System.out.println("\nQ3: Reverse Queue");
            System.out.println("Original Queue: " + originalQueue); // [10, 20, 30]
            reverseQueue(originalQueue);
            System.out.println("Reversed Queue: " + originalQueue); // [30, 20, 10]

            // Q4 Demonstration
            MinPriorityQueue<Integer> minPQ = new MinPriorityQueue<>();
            minPQ.enqueue(5);
            minPQ.enqueue(1);
            minPQ.enqueue(10);
            minPQ.enqueue(3);
            System.out.println("\nQ4: Min Priority Queue (Smallest first)");
            System.out.println("Dequeue 1: " + minPQ.dequeue()); // 1
            System.out.println("Dequeue 2: " + minPQ.dequeue()); // 3
            System.out.println("Dequeue 3: " + minPQ.dequeue()); // 5

            // Q5 Demonstration
            Queue<Integer> qA = new LinkedList<>();
            qA.offer(1);
            qA.offer(5);
            qA.offer(9);
            Queue<Integer> qB = new LinkedList<>();
            qB.offer(2);
            qB.offer(6);
            qB.offer(10);
            System.out.println("\nQ5: Merge Sorted Queues");
            System.out.println("Queue A: " + qA);
            System.out.println("Queue B: " + qB);
            Queue<Integer> merged = mergeSortedQueues(qA, qB);
            System.out.println("Merged Queue: " + merged); // [1, 2, 5, 6, 9, 10]
        }
    }




