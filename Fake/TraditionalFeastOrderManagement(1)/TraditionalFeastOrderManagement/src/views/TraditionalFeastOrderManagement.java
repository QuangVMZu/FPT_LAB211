/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomerFeastOrderList;
import controllers.Menu;
import java.util.List;
import models.CustomerFeastOrder;
import models.I_List;
import models.I_Menu;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class TraditionalFeastOrderManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        I_Menu menu = new Menu();
        menu.addItem("1. Add new registration.");
        menu.addItem("2. Update Registration Information.");
        menu.addItem("3. Display Registered List.");
        menu.addItem("4. Delete Registration Information.");
        menu.addItem("5. Search Participants by Name.");
        menu.addItem("6. Filter Data by Campus.");
        menu.addItem("7: Statistics of Registration Numbers by Location.");
        menu.addItem("8: Save Data to File");
        menu.addItem("9: Exit the Program.");
        int choice;
        boolean cont = true;
        I_List list = new CustomerFeastOrderList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.create();
                    break;
                case 2:
                    boolean contiueUpdate;
                    do {
                        String newCode = Utils.getString("Enter customer Code to update: ");
                        if (list.update(newCode)) {
                            System.out.println("Successfully update the registration.");
                        } else {
                            System.out.println("This customer does not exist .");
                        }

                        contiueUpdate = menu.confirmYesNo("Do you want to place another order? (Y/N): ");
                    } while (contiueUpdate);
                    break;
                case 3:
                    String name = Utils.getString("Enter customer NAME to find: ");
                    List<Object> results = list.search(name);
                    if (results != null && !results.isEmpty()) {
                        System.out.println("Found the following registrations: ");
                        System.out.println("-----------------------------------------------------------------------------------");
                        System.out.println("Customer Code | Name                 | Phone        | Email                        ");
                        System.out.println("-----------------------------------------------------------------------------------");
                        for (Object obj : results) {
                            CustomerFeastOrder sm = (CustomerFeastOrder) obj; // Ép kiểu từ Object về StudentMountain
                            System.out.println(sm.toString());
                        }
                        System.out.println("------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("No one matches the search criteria!");
                    }
                    break;
                case 4:
                    list.display();
                    break;
                case 5:
                    boolean continueOrdering;
                    do {
                        // Nhập mã khách hàng
                        String codeOfCustomer = Utils.getString("Enter customer Code: ").toUpperCase();

                        // Kiểm tra và đặt đơn hàng
                        boolean success = list.placeFeastOrder(codeOfCustomer);
                        if (success) {
                            System.out.println("Order placed successfully!");
                        } else {
                            System.out.println("Order failed! Possible reasons: Customer not found or duplicate order.");
                        }

                        // Hỏi người dùng có muốn tiếp tục đặt đơn không
                        continueOrdering = menu.confirmYesNo("Do you want to place another order? (Y/N): ");
                    } while (continueOrdering);

                    break;
                case 9:
                    boolean confirm = menu.confirmYesNo("Do you want to save the changes before exiting? (Y/N): ");
                    if (confirm) {
//                        boolean saved = list.saveToFile();
//                        if (saved) {
//                            System.out.println("Changes have been saved successfully. Exiting the program.");
//                        } else {
//                            System.out.println("Failed to save changes. Exiting the program.");
//                        }
                        cont = false; // Thoát khỏi vòng lặp
                    } else {
                        System.out.println("Exiting the program without saving changes.");
                        cont = false;
                    }
                    break;
            }

        } while (choice >= 0 && choice <= 9 && cont);
    }
}
