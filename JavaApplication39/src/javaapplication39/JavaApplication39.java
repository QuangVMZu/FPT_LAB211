/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication39;

/**
 *
 * @author ADMIN
 */
public class JavaApplication39 {

    /**
     * @param args the command line arguments
     */
    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class LinkedList {

        Node head;

        // Hàm sắp xếp Merge Sort cho Linked List
        public Node mergeSort(Node head) {
            if (head == null || head.next == null) {
                return head;
            }

            // Chia danh sách thành hai phần
            Node middle = getMiddle(head);
            Node nextOfMiddle = middle.next;
            middle.next = null;

            Node left = mergeSort(head);
            Node right = mergeSort(nextOfMiddle);

            // Hợp nhất hai phần đã sắp xếp
            return merge(left, right);
        }

        // Hàm tìm phần tử giữa của Linked List
        public Node getMiddle(Node head) {
            if (head == null) {
                return head;
            }
            Node slow = head;
            Node fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        // Hàm hợp nhất hai danh sách đã sắp xếp
        public Node merge(Node left, Node right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }

            if (left.data <= right.data) {
                left.next = merge(left.next, right);
                return left;
            } else {
                right.next = merge(left, right.next);
                return right;
            }
        }

        // Hàm thêm phần tử vào cuối Linked List
        public void append(int data) {
            Node newNode = new Node(data);
            if (head == null) {
                head = newNode;
                return;
            }

            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

        // Hàm in ra Linked List
        public void printList(Node head) {
            while (head != null) {
                System.out.print(head.data + " ");
                head = head.next;
            }
        }
    }
        public static void main(String[] args) {
            LinkedList list = new LinkedList();
            list.append(5);
            list.append(2);
            list.append(9);
            list.append(1);
            list.append(6);

            System.out.println("Original Linked List:");
            list.printList(list.head);

            list.head = list.mergeSort(list.head);

            System.out.println("\nSorted Linked List:");
            list.printList(list.head);
        }
    }
