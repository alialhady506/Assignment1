package alialhadi;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;




public class DataStructuresLab
{




        /**
         * Question 1: Write a function to reverse a string using Stack.
         */
        public static String reverseString(String input) {
            Stack<Character> stack = new Stack<>();
            for (char c : input.toCharArray()) {
                stack.push(c);
            }

            StringBuilder reversed = new StringBuilder();
            while (!stack.isEmpty()) {
                reversed.append(stack.pop());
            }
            return reversed.toString();
        }

        /**
         * Question 2: Write a function to sort a stack using only another Stack.
         */
        public static void sortStack(Stack<Integer> stack) {
            Stack<Integer> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                int current = stack.pop();
                while (!tempStack.isEmpty() && tempStack.peek() > current) {
                    stack.push(tempStack.pop());
                }
                tempStack.push(current);
            }

            // Move elements back to original stack to have them in sorted order
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }

        /**
         * Question 3: Write a function to reverse the order of elements in a queue.
         */
        public static void reverseQueue(Queue<Integer> queue) {
            Stack<Integer> stack = new Stack<>();
            while (!queue.isEmpty()) {
                stack.push(queue.poll());
            }
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }
        }

        /**
         * Question 5: Write a function to merge two sorted queues into a single sorted queue.
         */
        public static Queue<Integer> mergeSortedQueues(Queue<Integer> q1, Queue<Integer> q2) {
            Queue<Integer> mergedQueue = new LinkedList<>();

            while (!q1.isEmpty() && !q2.isEmpty()) {
                if (q1.peek() <= q2.peek()) {
                    mergedQueue.add(q1.poll());
                } else {
                    mergedQueue.add(q2.poll());
                }
            }

            // Add remaining elements
            while (!q1.isEmpty()) {
                mergedQueue.add(q1.poll());
            }
            while (!q2.isEmpty()) {
                mergedQueue.add(q2.poll());
            }

            return mergedQueue;
        }

        public static void main(String[] args) {
            // Testing Q1
            System.out.println("Q1: Reverse 'Hello' -> " + reverseString("Hello"));

            // Testing Q2
            Stack<Integer> s = new Stack<>();
            s.push(34); s.push(3); s.push(31); s.push(98); s.push(92); s.push(23);
            sortStack(s);
            System.out.println("Q2: Sorted Stack -> " + s);

            // Testing Q3
            Queue<Integer> q = new LinkedList<>();
            q.add(1); q.add(2); q.add(3);
            reverseQueue(q);
            System.out.println("Q3: Reversed Queue -> " + q);

            // Testing Q5
            Queue<Integer> q1 = new LinkedList<>();
            q1.add(1); q1.add(3); q1.add(5);
            Queue<Integer> q2 = new LinkedList<>();
            q2.add(2); q2.add(4); q2.add(6);
            System.out.println("Q5: Merged Sorted Queues -> " + mergeSortedQueues(q1, q2));
        }
    }


