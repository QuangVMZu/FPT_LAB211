/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.CustomersManager;
import controllers.FeastMenuManager;
import controllers.Menu;
import controllers.TableReservationList;
import java.util.List;
import models.Customer;
import models.FeastMenu;
import models.I_List;
import models.I_ListOfOrder;
import models.I_Menu;
import models.OrderFeast;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class TraditionalFeastOrderManagement {
//

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CustomersManager cusList = new CustomersManager();
        TableReservationList orderList = new TableReservationList();

        I_Menu menu = new Menu();
        
        menu.addItem("| 1. Add new registration.            |");
        menu.addItem("| 2. Update Registration Information. |");
        menu.addItem("| 3. Display Registered List.         |");
        menu.addItem("| 4. Delete Registration Information. |");
        menu.addItem("| 5. Search Participants by Name.     |");
        menu.addItem("| 6. Filter Data by Campus.           |");
        menu.addItem("| 7: Statistics of Registration Numbers by Location. |");
        menu.addItem("| 8: Save Data to File.               |");
        menu.addItem("| 9: Exit the Program.                |");
        menu.addItem("| 10: In.                             |");
        int choice;
        boolean cont = true;
        Utils input = new Utils();
        do {
            System.out.println("\n-------------- Main MENU --------------");
            menu.showMenu();
            System.out.println("---------------------------------------");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    boolean continueCreate = true;
                    do {
                        Customer newC = input.inputCustomerInfo(false);
                        cusList.addNew(newC);
                        cusList.saveToFile();
                        continueCreate = menu.confirmYesNo("Do you want to continue create Customer? (Y/N): ");
                    } while (continueCreate);

                    break;
                case 2:
                    boolean continueUpdateCustomer = false;
                    do {
                        //nhập code customer muốn tìm trước vào.
                        String codeUpdateC = input.getString("Enter customer code you want to update: ");
                        // tạo ra 1 customer trống để ktra trong ctmList
                        Customer existC = cusList.searchByCode(codeUpdateC);
                        if (existC == null) {
                            System.out.println("This customer does not exist.");
                            break;
                        }
                        cusList.updateInfoCutomer(codeUpdateC);
                        System.out.println("Update successful!");
                        continueUpdateCustomer = menu.confirmYesNo("Do you want to continue update Customer? (Y/N): ");
                    } while (continueUpdateCustomer);

                    break;
                case 10:
                    cusList.displayAll();
                    break;

                case 3:
                    boolean continueSearch = false;
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
                    boolean continueUpdateOrder = false;
                    do {
                        // Nhập mã đơn hàng cần cập nhật
                        String orderCode = input.getString("Enter order code you want to update: ");

                        // Tìm kiếm đơn hàng theo mã
                        OrderFeast existingOrder = orderList.searchByID(orderCode);

                        // Kiểm tra đơn hàng có tồn tại không
                        if (existingOrder == null) {
                            System.out.println("This order does not exist.");
                            break;
                        }

                        // Gọi hàm cập nhật đơn hàng
                        orderList.updateInfoOrder(orderCode);
                        System.out.println("Order update successful!");
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
                                cusList.displayAll();
                                System.out.println("Registration data has been successfully saved to `customer.dat`");
                                break;
                            case 2:
                                orderList.displayOrderList();
                                System.out.println("Registration data has been successfully saved to `feast_order_service.dat`");
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
                            continueToDisplay = menu.confirmYesNo("Do you want to continue saving (Y/N): ");
                        }

                    } while (continueToDisplay); // Tiếp tục vòng lặp nếu continueToDisplay là true

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
        } while (choice >= 0 && choice <= 10 && cont);
    }

}
