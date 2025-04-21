/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomersManager;
import controllers.FeastMenuManager;
import controllers.Menu;
import controllers.OrderManager;
import models.Customer;
import models.I_Menu;
import models.OrderFeast;
import utils.Utils;

/**
 *
 * @author Wang
 */
public class TraditionalFeastManagement {

    public static void main(String[] args) {
        // TODO code application logic here
        CustomersManager cusList = new CustomersManager();
        OrderManager orderList = new OrderManager();

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
                    do {
                        Customer newC = input.inputCustomerInfo(false);
                        cusList.addNew(newC);
                        continueCreate = menu.confirmYesNo("Do you want to continue create Customer? (Y/N): ");
                    } while (continueCreate);
                    break;

                case 2:
                    boolean continueUpdateCustomer;
                    do {
                        //nhập code customer muốn tìm trước vào.
                        String codeUpdateC = input.getString("Enter customer code you want to update: ");
                        // Gọi hàm cập nhật customer                        
                        cusList.updateInfoCutomer(codeUpdateC);
                        continueUpdateCustomer = menu.confirmYesNo("Do you want to continue update Customer? (Y/N): ");
                    } while (continueUpdateCustomer);
                    break;
                case 3:
                    boolean continueSearch;
                    do {
                        //nhập tên muốn tìm 
                        String nameCusToSearch = input.getString("Enter customer name you want to search: ");
                        // tìm tên đó trong ctmList
                        cusList.searchByName(nameCusToSearch);
                        continueSearch = menu.confirmYesNo("Do you want to continue search Customer? (Y/N): ");
                    } while (continueSearch);
                    break;
                case 4:
                    FeastMenuManager menuFeast = new FeastMenuManager();
                    menuFeast.displayMenu();
                    break;
                case 5:
                    boolean continueOrdering;
                    do {
                        OrderFeast newO = input.inputOrderFeast(false);
                        orderList.placeOrder(newO);
                        continueOrdering = menu.confirmYesNo("Do you want to continue place another order? (Y/N): ");
                    } while (continueOrdering);
                    break;
                case 6:
                    boolean continueUpdateOrder;
                    do {
                        // Nhập mã đơn hàng cần cập nhật
                        String orderCode = input.getString("Enter order code you want to update: ");
                        // Gọi hàm cập nhật đơn hàng
                        orderList.updateInfoOrder(orderCode);
                        continueUpdateOrder = menu.confirmYesNo("Do you want to conutinue place another order? (Y/N): ");
                    } while (continueUpdateOrder);
                    break;

                case 7:
                    boolean continueToSave = true;
                    do {
                        System.out.println("\n------- SAVE DATA MENU -------");
                        System.out.println("| 1. Save customer data      |");
                        System.out.println("| 2. Save order data         |");
                        System.out.println("| 3. Save the both           |");
                        System.out.println("| 4. Return to the main menu |");
                        System.out.println("------------------------------");
                        int choiceSave = input.getInt("Input choice: ", 1, 4);

                        switch (choiceSave) {
                            case 1:
                                cusList.saveToFile();
                                System.out.println("Registration data has been successfully saved to `customer.dat`");
                                break;
                            case 2:
                                orderList.saveToFileOrder();
                                System.out.println("Registration data has been successfully saved to `feast_order_service.dat`");
                                break;
                            case 3:
                                cusList.saveToFile();
                                orderList.saveToFileOrder();
                                System.out.println("Saved both files");
                                break;
                            case 4:
                                System.out.println("Returning to the main menu...");
                                continueToSave = false; // Kết thúc vòng lặp khi chọn quay lại menu chính
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                        if (choiceSave == 1 || choiceSave == 2 || choiceSave == 3) {
                            continueToSave = menu.confirmYesNo("Do you want to countinue save (Y/N): ");
                        }
                    } while (continueToSave);
                    break;

                case 8:
                    boolean continueToDisplay = true; // Đặt mặc định là true để vòng lặp chạy ít nhất một lần
                    do {
                        System.out.println("------ DISPLAY DATA MENU ------");
                        System.out.println("| 1. Display customer data    |");
                        System.out.println("| 2. Display order data       |");
                        System.out.println("| 3. Return to the main menu  |");
                        System.out.println("-------------------------------");

                        int choiceToDisplay = input.getInt("Input choice: ", 1, 3);
                        // Xử lý lựa chọn của người dùng
                        switch (choiceToDisplay) {
                            case 1:
                                System.out.print("                           The list Information Customer");
                                cusList.displayAll(cusList);
                                break;
                            case 2:
                                System.out.print("                                   The list FeastOrder");
                                orderList.displayOrderList();
                                break;
                            case 3:
                                System.out.println("Returning to the main menu...");
                                continueToDisplay = false; // Kết thúc vòng lặp khi chọn quay lại menu chính
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }
                        // Nếu người dùng chọn 1 hoặc 2, yêu cầu hỏi lại có muốn tiếp tục không
                        if (choiceToDisplay == 1 || choiceToDisplay == 2) {
                            continueToDisplay = menu.confirmYesNo("Do you want to continue display (Y/N): ");
                        }

                    } while (continueToDisplay); // Tiếp tục vòng lặp nếu continueToDisplay là true
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
