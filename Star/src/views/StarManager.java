/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomersManager;
import controllers.FeesManager;
import controllers.Menu;
import models.I_Menu;
import utils.Utils;

/**
 *
 * @author Wang
 */
public class StarManager {

    public static void main(String[] args) {
        // TODO code application logic here
        CustomersManager cusList = new CustomersManager();
        FeesManager fm = new FeesManager();
//        OrderManager orderList = new OrderManager();

        I_Menu menu = new Menu();

        menu.addItem("| 1. Register customers.                      |");
        menu.addItem("| 2. Update customer information.             |");
        menu.addItem("| 3. Search for customer information by name. |");
        menu.addItem("| 4. Display feast menus.                     |");
        menu.addItem("| 5. Place a feast order.                     |");
        menu.addItem("| 6. Update order information.                |");
        menu.addItem("| 7. Save data to file.                       |");
        menu.addItem("| 8. Display Customer or Order lists.         |");
        menu.addItem("| 9: Exit the Program.                        |");

        int choice;
        boolean cont = true;
        Utils input = new Utils();
        do {
            System.out.println("\n----------------- Main MENU -------------------");
            menu.showMenu();
            System.out.println("-----------------------------------------------");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    boolean continueCreate;
//                    do {
//                        Customer newC = input.inputCustomerInfo(false);
//                        cusList.addNew(newC);
//                        continueCreate = menu.confirmYesNo("Do you want to continue create Customer? (Y/N): ");
//                    } while (continueCreate);
                    fm.displayMenu();
                    break;

                case 9:
                    boolean confirm = menu.confirmYesNo("Do you want to save the changes before exiting? (Y/N): ");
                    if (confirm) {
                        cont = false;
                    } else {
                        cont = true;  // Thoát khỏi vòng lặp
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice >= 0 && choice <= 10 && cont);
    }
}
