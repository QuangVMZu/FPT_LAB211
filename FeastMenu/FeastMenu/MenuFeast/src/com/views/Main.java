package com.views;

import com.controllers.OrdersControllers;
import com.controllers.Menu;
import com.models.Action;
import com.models.I_Menu;
import com.utils.Utils.Utils;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
public class Main {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Register new customer.");
        menu.addItem("2. Update customer's information.");
        menu.addItem("3. Search customer by name.");
        menu.addItem("4. Display feast menu.");
        menu.addItem("5. Place a feast order.");
        menu.addItem("6. Update order information.");
        menu.addItem("7. Save data.");
        menu.addItem("8. Display customer and order list.");
        menu.addItem("9. Exit.");
        int choice;
        boolean cont = true;
        String id;
        OrderViews.init();
        CustomerViews.init();

        do {

            menu.showMenu();
            choice = Utils.getInt("Input your choice : ", 1, 9);
            switch (choice) {
                case 1:
                    Utils.executeWithRetry(new Action() {
                        @Override
                        public boolean execute() {
                            return CustomerViews.register();
                        }
                    }, "Register new customer successfully.", "Register new customer failed.");
                    break;
                case 2:
                    Utils.executeWithRetry(new Action() {
                        @Override
                        public boolean execute() {
                            return CustomerViews.update();
                        }
                    }, "Update customer's information successfully.", "Update customer's information failed.");
                    break;
                case 3:
                    Utils.executeWithRetry(new Action() {
                        @Override
                        public boolean execute() {
                            return CustomerViews.searchByName();
                        }
                    }, "", "No one matches the search criteria.");
                    break;
                case 4:
                    OrderViews.displayMenu();
                    break;
                case 5:
                    Utils.executeWithRetry(new Action() {
                        @Override
                        public boolean execute() {
                            return OrderViews.placeOrder();
                        }
                    }, "Place a order successfully.", "");
                    break;
                case 6:
                    Utils.executeWithRetry(new Action() {
                        @Override
                        public boolean execute() {
                            return OrderViews.updateOrder();
                        }
                    }, "Update a order's information successfully.", "");
                    break;
                case 7:
                    Utils.displayStatus(OrderViews.saveData(), "Save data successfully.", "Save data failed.");
                    break;
                case 8:
                    Utils.displayStatus(OrderViews.displayCustomerOrder(), "", "No data.");
                    break;
                case 9:
                    boolean exit = Utils.confirmYesNo("Do you still wanna exit [y/n] :");
                    if(exit) {
                        cont = false;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        } while (true && cont);
    }
}
