package sample.views;

import java.util.List;
import sample.controllers.Menu;
import sample.controllers.StudentMountainList;
import sample.models.I_List;
import sample.models.I_Menu;
import sample.models.Student;
import sample.models.StudentMountain;
import sample.utils.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Wang
 */
public class MoutainHikingManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("1. Add new registration.");
        menu.addItem("2. Update Registration Information.");
        menu.addItem("3. Display Registered List.");
        menu.addItem("4. Delete Registration Information.");
        menu.addItem("5. Search Participants by Name.");
        menu.addItem("6. Filter Data by Campus.");
        menu.addItem("7: Statistics of Registration Numbers by Location.");
        menu.addItem("8: Save Data to File");
        menu.addItem("9: LastStuden");
        menu.addItem("10: FirstStudent");
        menu.addItem("11: Exit the Program.");
        int choice;
        boolean cont = true;
        I_List list = new StudentMountainList();
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    list.create();
                    break;
                case 2:
                    String newid = Utils.getString("Enter student ID to update: ");
                    if (list.update(newid)) {
                        System.out.println("Successfully update the registration.");
                    } else {
                        System.out.println("Failed to delete. ID not found.");
                    }
                    break;

                case 3:
                    list.display();
                    break;
                case 9:
                    Student st = list.getLastStudent();
                    System.out.println(st.toString());
                    break;
                case 10:
                    Student stu = list.getFirstStudent();
                    System.out.println(stu.toString());
                    break;    
                case 4:
                    String id = Utils.getString("Enter student ID to delete: ");
                    if (list.delete(id)) {
                        System.out.println("Successfully deleted the registration.");
                    }
                    break;

                case 5:
                    String name = Utils.getString("Enter student NAME to find: ");
                    List<Object> results = list.search(name);

                    if (results != null && !results.isEmpty()) {
                        System.out.println("Found the following registrations: ");
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Student ID | Name                 | Phone        | Email                          | Peak Code       | Fee         ");
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                        for (Object obj : results) {
                            StudentMountain sm = (StudentMountain) obj; // Ép kiểu từ Object về StudentMountain
                            System.out.println(sm.toString());
                        }
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("No student have this name registered! ");
                    }
                    break;

                case 6:
                    String n = Utils.getString("Enter a Campus to filter: ");
                    List<Object> result = list.filter(n);

                    if (result != null && !result.isEmpty()) {
                        System.out.println("Found the following registrations: ");
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                        System.out.println("Student ID | Name                 | Phone        | Email                          | Peak Code       | Fee         ");
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                        for (Object o : result) {
                            System.out.println(o);
                        }
                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("No student have registered under this campus! ");
                    }
                    break;

                case 7:
                    list.statistic();
                    break;

                case 8:
                    list.saveToFile();
                    break;
                case 11:
                    boolean confirm = menu.confirmYesNo("Do you want to save the changes before exiting? (Y/N): ");
                    if (confirm) {
                        boolean saved = list.saveToFile();
                        if (saved) {
                            System.out.println("Changes have been saved successfully. Exiting the program.");
                        } else {
                            System.out.println("Failed to save changes. Exiting the program.");
                        }
                        cont = false; // Thoát khỏi vòng lặp
                    } else {
                        System.out.println("Exiting the program without saving changes.");
                        cont = false;
                    }
            }

        } while (choice >= 0 && choice <= 11 && cont);
    }
}
