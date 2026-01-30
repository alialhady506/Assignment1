package المشروع;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


/**
 * الطالب علي احمد يحيى الهادي
 * الطالب حمزه العياني
 * مشروع هياكل البيانات: نظام إدارة الخدمات اللوجستية والتوصيل الذكي
 * يطبق جميع هياكل البيانات الأساسية: المصفوفات، القوائم المترابطة، المكدس، الطابور، طابور الأولويات، شجرة البحث الثنائية، والرسوم البيانية.
 */
public class LogisticsSystem {

    // =================================================================
    // 1. Shipment Class (Data Model)
    // =================================================================
    static class Shipment implements Comparable<Shipment> {
        int trackingId;
        String source;
        String destination;
        String status;
        int priority; // Used for PriorityQueue

        public Shipment(int trackingId, String source, String destination, int priority) {
            this.trackingId = trackingId;
            this.source = source;
            this.destination = destination;
            this.status = "Pending";
            this.priority = priority;
        }

        @Override
        public String toString() {
            return String.format("[ID:%d, From:%s, To:%s, Status:%s, Priority:%d]",
                    trackingId, source, destination, status, priority);
        }

        // Used for PriorityQueue (Smallest priority value is highest priority)
        @Override
        public int compareTo(Shipment other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    // =================================================================
    // 2. Singly Linked List (Active Shipments)
    // =================================================================
    static class ShipmentNode {
        Shipment data;
        ShipmentNode next;

        public ShipmentNode(Shipment data) {
            this.data = data;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        ShipmentNode head;

        public void addShipment(Shipment shipment) {
            ShipmentNode newNode = new ShipmentNode(shipment);
            if (head == null) {
                head = newNode;
            } else {
                ShipmentNode current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public void display() {
            ShipmentNode current = head;
            System.out.print("Active Shipments (Linked List): ");
            while (current != null) {
                System.out.print(current.data.trackingId + " -> ");
                current = current.next;
            }
            System.out.println("NULL");
        }
    }

    // =================================================================
    // 3. Stack (Shipment History/Undo)
    // =================================================================
    static class ShipmentHistoryStack {
        private Stack<String> history = new Stack<>();

        public void recordStatusChange(Shipment shipment, String newStatus) {
            String record = String.format("ID:%d, Old Status:%s, New Status:%s",
                    shipment.trackingId, shipment.status, newStatus);
            history.push(record);
            shipment.status = newStatus;
        }

        public String undoLastChange() {
            if (!history.isEmpty()) {
                return history.pop();
            }
            return "No history to undo.";
        }

        public void display() {
            System.out.println("Shipment History (Stack - LIFO): " + history);
        }
    }

    // =================================================================
    // 4. Queue (Normal Delivery Requests)
    // =================================================================
    static class DeliveryQueue {
        private Queue<Shipment> normalQueue = new LinkedList<>();

        public void enqueue(Shipment shipment) {
            normalQueue.add(shipment);
        }

        public Shipment dequeue() {
            return normalQueue.poll(); // FIFO
        }

        public void display() {
            System.out.println("Normal Delivery Queue (FIFO): " + normalQueue);
        }
    }

    // =================================================================
    // 5. Priority Queue (Express Delivery Requests)
    // =================================================================
    static class PriorityDeliveryQueue {
        // Uses the natural ordering defined in Shipment (smallest priority value first)
        private PriorityQueue<Shipment> expressQueue = new PriorityQueue<>();

        public void enqueue(Shipment shipment) {
            expressQueue.add(shipment);
        }

        public Shipment dequeue() {
            return expressQueue.poll(); // Smallest priority first
        }

        public void display() {
            System.out.println("Express Delivery Queue (Priority): " + expressQueue);
        }
    }

    // =================================================================
    // 6. Binary Search Tree (Search by Tracking ID)
    // =================================================================
    static class BSTNode {
        Shipment data;
        BSTNode left, right;

        public BSTNode(Shipment data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static class ShipmentBST {
        BSTNode root;

        public void insert(Shipment shipment) {
            root = insertRec(root, shipment);
        }

        private BSTNode insertRec(BSTNode root, Shipment shipment) {
            if (root == null) {
                root = new BSTNode(shipment);
                return root;
            }
            if (shipment.trackingId < root.data.trackingId) {
                root.left = insertRec(root.left, shipment);
            } else if (shipment.trackingId > root.data.trackingId) {
                root.right = insertRec(root.right, shipment);
            }
            return root;
        }

        public Shipment search(int trackingId) {
            return searchRec(root, trackingId);
        }

        private Shipment searchRec(BSTNode root, int trackingId) {
            if (root == null || root.data.trackingId == trackingId) {
                return (root == null) ? null : root.data;
            }
            if (trackingId < root.data.trackingId) {
                return searchRec(root.left, trackingId);
            }
            return searchRec(root.right, trackingId);
        }

        public void inOrderTraversal() {
            System.out.print("BST (In-Order Traversal by ID): ");
            inOrderRec(root);
            System.out.println();
        }

        private void inOrderRec(BSTNode root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.print(root.data.trackingId + " ");
                inOrderRec(root.right);
            }
        }
    }

    // =================================================================
    // 7. Graph (Delivery Network - Adjacency List)
    // =================================================================
    static class DeliveryNetworkGraph {
        private Map<String, List<String>> adjList = new HashMap<>();

        public void addCity(String city) {
            adjList.putIfAbsent(city, new ArrayList<>());
        }

        public void addRoute(String city1, String city2) {
            adjList.get(city1).add(city2);
            adjList.get(city2).add(city1); // Undirected graph
        }

        // Example application: Find shortest path using Breadth-First Search (BFS)
        public List<String> findShortestPath(String start, String end) {
            if (!adjList.containsKey(start) || !adjList.containsKey(end)) {
                return Collections.emptyList();
            }

            Map<String, String> parentMap = new HashMap<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            parentMap.put(start, null);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                if (current.equals(end)) break;

                for (String neighbor : adjList.get(current)) {
                    if (!parentMap.containsKey(neighbor)) {
                        parentMap.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }

            // Reconstruct path
            List<String> path = new LinkedList<>();
            String current = end;
            while (current != null) {
                path.add(0, current);
                current = parentMap.get(current);
            }

            return path.size() > 1 ? path : Collections.emptyList();
        }

        public void display() {
            System.out.println("Delivery Network (Graph - Adjacency List):");
            for (String city : adjList.keySet()) {
                System.out.println("  " + city + " -> " + adjList.get(city));
            }
        }
    }

    // =================================================================
    // 8. Main Application (Demonstration)
    // =================================================================
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("نظام إدارة الخدمات اللوجستية والتوصيل الذكي");
        System.out.println("=================================================");

        // 1. Arrays (Fixed Locations)
        String[] cities = {"Riyadh", "Jeddah", "Dammam", "Mecca", "Medina"};
        System.out.println("\n--- 1. Arrays (Fixed Cities) ---");
        System.out.println("Available Cities: " + String.join(", ", cities));

        // Create Shipments
        Shipment s1 = new Shipment(101, "Riyadh", "Jeddah", 5); // Normal
        Shipment s2 = new Shipment(102, "Dammam", "Mecca", 1);  // Express (Highest Priority)
        Shipment s3 = new Shipment(103, "Jeddah", "Medina", 5); // Normal
        Shipment s4 = new Shipment(104, "Riyadh", "Dammam", 2);  // Express

        // 2. Singly Linked List (Active Shipments)
        System.out.println("\n--- 2. Singly Linked List (Active Shipments) ---");
        SinglyLinkedList activeShipments = new SinglyLinkedList();
        activeShipments.addShipment(s1);
        activeShipments.addShipment(s2);
        activeShipments.addShipment(s3);
        activeShipments.display();

        // 3. Stack (Shipment History)
        System.out.println("\n--- 3. Stack (Shipment History) ---");
        ShipmentHistoryStack history = new ShipmentHistoryStack();
        history.recordStatusChange(s1, "In Transit");
        history.recordStatusChange(s1, "Out for Delivery");
        System.out.println("Current Status of S1: " + s1.status);
        history.display();
        System.out.println("Undo: " + history.undoLastChange());
        history.display();

        // 4. Queue (Normal Delivery)
        System.out.println("\n--- 4. Queue (Normal Delivery) ---");
        DeliveryQueue normalDelivery = new DeliveryQueue();
        normalDelivery.enqueue(s1); // Priority 5
        normalDelivery.enqueue(s3); // Priority 5
        normalDelivery.display();
        System.out.println("Processing (Dequeue): " + normalDelivery.dequeue()); // FIFO: s1 first
        normalDelivery.display();

        // 5. Priority Queue (Express Delivery)
        System.out.println("\n--- 5. Priority Queue (Express Delivery) ---");
        PriorityDeliveryQueue expressDelivery = new PriorityDeliveryQueue();
        expressDelivery.enqueue(s2); // Priority 1 (Highest)
        expressDelivery.enqueue(s4); // Priority 2
        expressDelivery.display();
        System.out.println("Processing (Dequeue): " + expressDelivery.dequeue()); // Priority: s2 first
        expressDelivery.display();

        // 6. Binary Search Tree (Search by Tracking ID)
        System.out.println("\n--- 6. Binary Search Tree (Search) ---");
        ShipmentBST bst = new ShipmentBST();
        bst.insert(s1);
        bst.insert(s2);
        bst.insert(s3);
        bst.insert(s4);
        bst.inOrderTraversal();
        Shipment found = bst.search(103);
        System.out.println("Search for ID 103: " + (found != null ? found : "Not Found"));

        // 7. Graph (Delivery Network)
        System.out.println("\n--- 7. Graph (Shortest Path) ---");
        DeliveryNetworkGraph graph = new DeliveryNetworkGraph();
        for (String city : cities) graph.addCity(city);
        graph.addRoute("Riyadh", "Dammam");
        graph.addRoute("Riyadh", "Jeddah");
        graph.addRoute("Jeddah", "Mecca");
        graph.addRoute("Mecca", "Medina");
        graph.display();

        List<String> path = graph.findShortestPath("Riyadh", "Medina");
        System.out.println("Shortest Path (Riyadh to Medina): " + path);
    }
}
