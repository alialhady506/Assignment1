// ====================================================================
// Data Structure Assignment 1 - Odd Lab Questions Solutions
// Student Name: [اسم الطالب]
// Group: [المجموعة]
// *يرجى استبدال [اسم الطالب] و [المجموعة] بالبيانات الصحيحة*
// ====================================================================

package assignment_1;

import java.util.Arrays;

// --------------------------------------------------------------------
// Node class for Singly Linked List and Circular Linked List
// --------------------------------------------------------------------
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// --------------------------------------------------------------------
// Doubly Node class for Doubly Linked List
// --------------------------------------------------------------------
class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode prev;

    public DoublyNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

// --------------------------------------------------------------------
// Singly Linked List Implementation for Q5, Q7, Q9
// --------------------------------------------------------------------
class SinglyLinkedList {
    Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Helper method to add a node to the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Helper method to print the list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // ====================================================================
    // Question 5: Write a function to concatenate two linked lists
    // ====================================================================
    public static void concatenate(SinglyLinkedList list1, SinglyLinkedList list2) {
        if (list1.head == null) {
            list1.head = list2.head;
            return;
        }
        if (list2.head == null) {
            return;
        }

        Node current = list1.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = list2.head;
        // بعد الدمج، يجب أن تكون قائمة list2 فارغة لتجنب الارتباك
        list2.head = null;
    }

    // ====================================================================
    // Question 7: Write a function to search for element in singly linked list and return its position.
    // (Position is 1-based index)
    // ====================================================================
    public int searchElement(int data) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        }
        return -1; // العنصر غير موجود
    }

    // ====================================================================
    // Question 9: Write a function to remove at specific position from singly linked list.
    // (Position is 1-based index)
    // ====================================================================
    public void removeAtPosition(int position) {
        if (head == null) {
            System.out.println("القائمة فارغة. لا يمكن الحذف.");
            return;
        }

        // الحالة 1: حذف الرأس (الموقع 1)
        if (position == 1) {
            head = head.next;
            return;
        }

        // الحالة 2: حذف عقدة في موقع > 1
        Node current = head;
        Node previous = null;
        int count = 1;

        while (current != null && count < position) {
            previous = current;
            current = current.next;
            count++;
        }

        // إذا كان الموقع خارج الحدود
        if (current == null) {
            System.out.println("الموقع " + position + " خارج الحدود. لا يمكن الحذف.");
            return;
        }

        // حذف العقدة
        previous.next = current.next;
    }
}

// --------------------------------------------------------------------
// Doubly Linked List Implementation for Q11
// --------------------------------------------------------------------
class DoublyLinkedList {
    DoublyNode head;
    DoublyNode tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Helper method to add a node to the end
    public void add(int data) {
        DoublyNode newNode = new DoublyNode(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    // Helper method to print the list
    public void printList() {
        DoublyNode current = head;
        System.out.print("Doubly List: null <-> ");
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // ====================================================================
    // Question 11: Write a function to traverse a doubly linked list in reverse and print all the elements.
    // ====================================================================
    public void traverseReverse() {
        if (tail == null) {
            System.out.println("القائمة فارغة.");
            return;
        }
        DoublyNode current = tail;
        System.out.print("Reverse Traversal: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }
}

// --------------------------------------------------------------------
// Circular Linked List Implementation for Q13, Q15
// --------------------------------------------------------------------
class CircularLinkedList {
    Node head;

    public CircularLinkedList() {
        this.head = null;
    }

    // Helper method to add a node to the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head; // يشير إلى نفسه
            return;
        }
        Node current = head;
        while (current.next != head) {
            current = current.next;
        }
        current.next = newNode;
        newNode.next = head;
    }

    // Helper method to print the list (يطبع حتى 10 عناصر لتجنب الحلقة اللانهائية في حالة الخطأ)
    public void printList() {
        if (head == null) {
            System.out.println("القائمة فارغة.");
            return;
        }
        Node current = head;
        int count = 0;
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
            count++;
        } while (current != head && count < 10);
        System.out.println("(Head: " + head.data + ")");
    }

    // ====================================================================
    // Question 13: Write a function to insert a node at a specific position in a circular linked list.
    // (Position is 1-based index)
    // ====================================================================
    public void insertAtPosition(int data, int position) {
        Node newNode = new Node(data);

        if (head == null) {
            if (position == 1) {
                head = newNode;
                newNode.next = head;
            } else {
                System.out.println("القائمة فارغة. الإدراج ممكن فقط في الموقع 1.");
            }
            return;
        }

        // الحالة 1: الإدراج في الرأس (الموقع 1)
        if (position == 1) {
            Node last = head;
            while (last.next != head) {
                last = last.next;
            }
            newNode.next = head;
            last.next = newNode;
            head = newNode;
            return;
        }

        // الحالة 2: الإدراج في موقع > 1
        Node current = head;
        int count = 1;

        // نتوقف عند العقدة التي تسبق موقع الإدراج
        while (count < position - 1) {
            current = current.next;
            count++;
            // تحقق مما إذا كنا قد عدنا إلى الرأس قبل الوصول إلى الموقع
            if (current == head) {
                System.out.println("الموقع " + position + " خارج الحدود. لا يمكن الإدراج.");
                return;
            }
        }

        // إدراج العقدة الجديدة
        newNode.next = current.next;
        current.next = newNode;
    }

    // ====================================================================
    // Question 15: Write a function to search for an element in a circular linked list.
    // (Returns the 1-based position)
    // ====================================================================
    public int searchElement(int data) {
        if (head == null) {
            return -1;
        }

        Node current = head;
        int position = 1;

        do {
            if (current.data == data) {
                return position;
            }
            current = current.next;
            position++;
        } while (current != head);

        return -1; // العنصر غير موجود
    }
}
