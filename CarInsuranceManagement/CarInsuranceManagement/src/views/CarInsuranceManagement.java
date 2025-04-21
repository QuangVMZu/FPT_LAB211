/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import Controllers.CarInformationManager;
import Controllers.InsuranceManager;
import Controllers.Menu;
import Utils.Utils;
import models.CarInformation;
import models.I_Menu;
import models.InsuranceStatement;

/**
 *
 * @author Admin
 */
public class CarInsuranceManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CarInformationManager c = new CarInformationManager();
        InsuranceManager im = new InsuranceManager();

        I_Menu menu = new Menu();

        menu.addItem("| 1. Add new car.                             |");
        menu.addItem("| 2. Find a car by license plate.             |");
        menu.addItem("| 3. Update car information.                  |");
        menu.addItem("| 4. Delete car information.                  |");
        menu.addItem("| 5. Add an insurance statement.              |");
        menu.addItem("| 6. List of insurance statements.            |");
        menu.addItem("| 7. Report uninsured cars.                   |");
        menu.addItem("| 8. Save data.                               |");
        menu.addItem("| 9: Exit the Program.                        |");
        menu.addItem("| 9: Display.                                 |");

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
                        CarInformation cm = input.inputOwnerInfo(false);
                        c.addNew(cm);
                        continueCreate = menu.confirmYesNo("Do you want to continue create infor? (Y/N): ");
                    } while (continueCreate);
                    break;
                case 2:
                    boolean continueFind;
                    do {
                        String plateToFind = input.getString("Enter plate want to find: ");
                        c.find(plateToFind);
                        continueFind = menu.confirmYesNo("Do you want to continue find plate? (Y/N): ");
                    } while (continueFind);
                    break;
                case 3:
                    boolean continueUpdateCustomer;
                    do {
                        //nhập code customer muốn tìm trước vào.
                        String codeUpdateC = input.getString("Enter customer code you want to update: ");
                        // Gọi hàm cập nhật customer                        
                        c.updateInfoCutomer(codeUpdateC);
                        continueUpdateCustomer = menu.confirmYesNo("Do you want to continue update Information of cars? (Y/N): ");
                    } while (continueUpdateCustomer);
                    break;
                case 4:
                    boolean continueDelete;
                    do {
                        String plate = input.getString("Enter a plate you want to delete: ");
                        c.delete(plate);
                        continueDelete = menu.confirmYesNo("Do you want to continue delete Information of car? (Y/N): ");
                    } while (continueDelete);
                    break;
                case 5:
                    boolean continueAddInsurance;
                    do {
                        InsuranceStatement insurance = input.inputInsurance(false);
                        im.AddInsurance(insurance);
                        continueAddInsurance = menu.confirmYesNo("Do you want to continue create Insurance? (Y/N): ");
                    } while (continueAddInsurance);
                    break;
                case 6:
                    boolean continuedisplayByYear;
                    do {
                        im.displayInsuranceByYear();
                        continuedisplayByYear = menu.confirmYesNo("Do you want to continue displayByYear? (Y/N): ");
                    } while (continuedisplayByYear);

                    break;
                case 7:
                    boolean continueShow;
                    do {
                        im.displayCarsWithoutInsurance();
                        continueShow = menu.confirmYesNo("Do you want to continue displayCarsWithoutInsurance? (Y/N): ");
                    } while (continueShow);
                    break;
                case 8:
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
                                c.saveToFile();
                                System.out.println("Registration data has been successfully saved.");
                                break;
                            case 2:
                                im.saveInsuranToFile();
                                System.out.println("Registration data has been successfully saved.");
                                break;
                            case 3:
                                c.saveToFile();
                                im.saveInsuranToFile();
                                System.out.println("Saved both files.");
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
                case 10:
                    im.display(im);
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
