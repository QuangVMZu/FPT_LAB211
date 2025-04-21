/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test_1;

import java.util.LinkedList;



/**
 *
 * @author ADMIN
 */
public class Test_1 {

    /**
     * @param args the command line arguments
     */
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