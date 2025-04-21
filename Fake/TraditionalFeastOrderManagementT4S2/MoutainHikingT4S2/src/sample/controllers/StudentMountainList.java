/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package sample.controllers;

import java.util.ArrayList;
import java.util.List;
import sample.models.I_List;
import sample.models.FeastOrder;
import sample.models.Customer;
import sample.models.CustomerFeastOrder;
import sample.utils.Utils;
import sample.models.I_Menu;
import sample.models.MountainStatistic;

/**
 *
 * @author Wang
 */
public class StudentMountainList extends ArrayList<CustomerFeastOrder> implements I_List {

    @Override
    public boolean create() {
        boolean check = false;
        try {
            boolean checkDupliate = true;
            boolean continous = true;
            Customer customer = new Customer();
            do {
                do {
                    String code = Utils.getString("Input customer Code: ");
                    if (Utils.isValidCode(code)) {
                        customer.setId(code.toUpperCase());
                        continous = false;
                    }
                } while (continous);
                if (this.indexOf(new CustomerFeastOrder(customer, null, 0)) == -1) {
                    checkDupliate = false;
                }
            } while (checkDupliate);
            //input name
            continous = true;
            do {
                String name = Utils.getString("Input student name: ");
                if (name.length() > 1 && name.length() < 24) {
                    customer.setName(name);
                    continous = false;
                }
            } while (continous);
            //phone 
            continous = true;
            do {
                String phone = Utils.getString("Input student phone: ");
                if (Utils.isValidPhone(phone)) {
                    customer.setPhone(phone);
                    continous = false;
                }
            } while (continous);
            //            email
            continous = true;
            do {
                String email = Utils.getString("Input student email: ");
                if (Utils.isValidEmail(email)) {
                    customer.setEmail(email);
                    continous = false;
                }
            } while (continous);

//            List<Object> listMountainCode = Utils.readObjectFromFile("mountainList.bin");
//            continous = true;
//            String mountainCode = "";
//            do {
//                mountainCode = Utils.getString("Input mountain code: ");
//                if (listMountainCode.indexOf(new Mountain(mountainCode)) != -1) {
//                    continous = false;
//                }
//            } while (continous);
//
//            float fee = Utils.BASE_FEE;
//            String first3Character = student.getPhone().substring(0, 3);
//            if (first3Character.equals("097") || first3Character.equals("098")
//                    || first3Character.equals("090") || first3Character.equals("091")) {
//                fee = Utils.BASE_FEE - (Utils.BASE_FEE * Utils.DISCOUNT_RATE / 100);
//            }
//            this.add(new StudentMountain(student, mountainCode, fee));
//            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public void display() {
        if (this.isEmpty()) {
            System.out.println("No student have registered yet! ");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println("Student ID | Name                 | Phone        | Email                          | Peak Code       | Fee         ");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            for (CustomerFeastOrder sm : this) {
                System.out.println(sm.toString());
//                System.out.println("\nLast Student:");
//                System.out.println(this.get(this.size() - 1).toString());
//                System.out.println("\nFirst Student:");
//                System.out.println(this.get(0).toString());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }
    }

    @Override
    public boolean saveToFile() {
        boolean check = false;
        try {
            Utils.writeListObjectToFile("studentMountainList.bin", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean delete(String id) {
        boolean check = false;
        try {
            // Tạo một đối tượng Student giả với ID cần kiểm tra
            Customer fakeStudent = new Customer();
            fakeStudent.setId(id.toUpperCase());

            CustomerFeastOrder fakeSM = new CustomerFeastOrder(fakeStudent, null, 0);
            int index = this.indexOf(fakeSM);
            if (index != -1) {
                CustomerFeastOrder target = this.get(index);

                System.out.println("Details of the registration to be deleted:");
                System.out.println("----------------------------------------------");
                System.out.println(target.toStringByDelete());
                System.out.println("----------------------------------------------");

                I_Menu menu = new Menu();
                boolean confirm = menu.confirmYesNo("Do you want to delete this registration? (Y/N)");

                if (confirm) {
                    this.remove(index);
                    check = true;
                } else {
                    System.out.println("Deletion canceled.");
                }
            } else {
                System.out.println("No registration found with the given ID.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean update(String id) {
        boolean check = false;
        try {
            Customer fakeStudent = new Customer();
            fakeStudent.setId(id.toUpperCase());
            CustomerFeastOrder stm = new CustomerFeastOrder(fakeStudent, null, 0);
            // Tìm kiếm StudentMountain với ID tương ứng
            int index = this.indexOf(stm);
            if (index != -1) { // Nếu tìm thấy đối tượng
                CustomerFeastOrder stc = this.get(index); // Lấy đối tượng stc đem đi check bằng index
                System.out.println("Found StudentMountain: ");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("Student ID | Name                 | Phone        | Email                          | Peak Code       | Fee         ");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println(stc.toString());
                System.out.println("------------------------------------------------------------------------------------------------------------------");

                // Bắt đầu cập nhật thông tin
                Customer customer = stc.getCustomer();

                String newName = Utils.updateString("Enter new name: ", customer.getName());
                customer.setName(newName);

                String newPhone;
                do {
                    newPhone = Utils.updateString("Enter new phone: ", customer.getPhone());
                    if (newPhone.isEmpty() || Utils.isValidPhone(newPhone)) {
                        break;
                    }
                    System.out.println("Invalid phone number. Please try again.");
                } while (true);
                if (!newPhone.isEmpty()) {
                    customer.setPhone(newPhone);
                }

                String newEmail;
                do {
                    newEmail = Utils.updateString("Enter new email: ", customer.getEmail());
                    if (newEmail.isEmpty() || Utils.isValidEmail(newEmail)) {
                        break;
                    }
                    System.out.println("Invalid email format. Please try again.");
                } while (true);
                if (!newEmail.isEmpty()) {
                    customer.setEmail(newEmail);
                }

//                String newMountainCode;
//                List<Object> listMountainCode = Utils.readObjectFromFile("mountainList.bin");
//                do {
//                    newMountainCode = Utils.updateString("Enter new mountain code: ", stc.getMountainCode());
//                    if (newMountainCode.isEmpty() || listMountainCode.contains(new FeastMenu(newMountainCode))) {
//                        break;
//                    }
//                    System.out.println("Invalid mountain code. Please try again.");
//                } while (true);
//                if (!newMountainCode.isEmpty()) {
//                    stc.setMountainCode(newMountainCode); // gán newMountainCode vào stc
//                }
//
//                String first3Characters = customer.getPhone().substring(0, 3);
//                float newFee = Utils.BASE_FEE;
//                if (first3Characters.equals("097") || first3Characters.equals("098")
//                        || first3Characters.equals("090") || first3Characters.equals("091")) {
//                    newFee = Utils.BASE_FEE * Utils.DISCOUNT_RATE / 100;
//                }
//                stc.setFee(newFee);
                // Thay đổi đã lưu vào danh sách
                this.set(index, stc);
                check = true;
                System.out.println("Update successful!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Object> search(String name) {
        List<Object> newList = new ArrayList<>();
        try {
            for (CustomerFeastOrder stm : this) { // Duyệt tất cả các phần từ có trong danh sách this "StudentMountain"
                if (stm.getCustomer() != null && stm.getCustomer().getName().toUpperCase().contains(name.toUpperCase())) {
                    newList.add(stm);
                }
            }
        } catch (Exception e) {
        }
        return newList;
    }

    @Override
    public List<Object> filter(String name) {
        List<Object> newList = new ArrayList<>();
        try {
            for (CustomerFeastOrder stm : this) {
                if (stm.getCustomer() != null
                        && name.substring(0, 2).equalsIgnoreCase(stm.getCustomer().getId().substring(0, 2))) {
                    newList.add(stm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }

//    @Override
//    public List<Object> statistic() {
//        List<Object> newList = new ArrayList<>();
//        List<String> checked = new ArrayList<>();
//
//        try {
//            for (CustomerFeast stm : this) {
//                String mountainCode = stm.getMountainCode();
//
//                if (checked.contains(mountainCode)) {
//                    continue;
//                }
//
//                int counts = 0;
//                float totalCost = 0;
//                for (CustomerFeast other : this) {
//                    if (mountainCode.equals(other.getMountainCode())) {
//                        counts++;
//                        totalCost += other.getFee();
//                    }
//                }
//
//                // Tạo đối tượng MountainStatistic và thêm vào danh sách kết quả
//                MountainStatistic statistic = new MountainStatistic(mountainCode, counts, totalCost);
//                newList.add(statistic);
//
//                checked.add(mountainCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (newList.isEmpty()) {
//            System.out.println("No student have registered!");
//        } else {
//            System.out.println("Statistics of Registration by Mountain Peak:");
//            System.out.println("---------------------------------------------------------------------");
//            System.out.printf("%-12s | %-20s | %-15s%n", "Peak Name", "Number of Participants", "Total Cost");
//            System.out.println("---------------------------------------------------------------------");
//
//            for (Object obj : newList) {
//                MountainStatistic statistic = (MountainStatistic) obj; // Ép kiểu về MountainStatistic
//                System.out.println(statistic.toStringByStatic());
//            }
//            System.out.println("---------------------------------------------------------------------");
//        }
//        return newList;
//    }
    @Override
    public List<Object> statistic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public boolean feastOrder(String code) {
//        boolean check = false;
//        try {
//            boolean continous = true;
//            Customer fakeCustomer = new Customer();
//            fakeCustomer.setId(code.toUpperCase());
//
//            if (this.indexOf(new CustomerFeastOrder(fakeCustomer, null, 0)) != -1) {
//                do {
//                    // Đọc dữ liệu từ file
//                    List<Object> listCodeMenu = Utils.readObjectFromFile("mountainList.bin");
//                    String codeOfMenu;
//                    FeastOrder selectedMenu = null;
//
//                    // Kiểm tra menu có tồn tại không
//                    do {
//                        codeOfMenu = Utils.getString("Input mountain code: ");
//                        for (Object obj : listCodeMenu) {
//                            if (obj instanceof FeastOrder) {
//                                FeastOrder menu = (FeastOrder) obj;
//                                if (menu.getCodeOfSetMenu().equalsIgnoreCase(codeOfMenu)) {
//                                    selectedMenu = menu;
//                                    break;
//                                }
//                            }
//                        }
//                        if (selectedMenu == null) {
//                            System.out.println("Menu code not found! Please try again.");
//                        }
//                    } while (selectedMenu == null);
//
//                    // Nhập số bàn
//                    int numOfTable;
//                    do {
//                        numOfTable = Utils.getInt("Input number of tables: ", 1, 10);
//                    } while (numOfTable < 1 || numOfTable > 10);
//
//                    // Tính cost = price * numOfTable
//                    try {
//                        int price = Integer.parseInt(selectedMenu.getPrice());
//                        int cost = price * numOfTable;
//                        System.out.println("Total Cost: " + cost);
//                        check = true;
//                    } catch (NumberFormatException e) {
//                        System.out.println("Invalid price format in menu data!");
//                    }
//
//                    continous = Utils.confirmYesNo("Do you want to continue ordering? (Y/N): ");
//                } while (continous);
//            } else {
//                System.out.println("Customer not found!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return check;
//    }
    @Override
    public boolean feastOrder(String code) {
        boolean check = false;
        try {
            // Chuyển code thành chữ hoa
            String customerCode = code.toUpperCase();
            Customer fakeCustomer = new Customer();
            fakeCustomer.setId(customerCode);

            // Kiểm tra xem khách hàng có tồn tại không
            boolean customerExists = false;
            for (CustomerFeastOrder order : this) {
                if (order.getCustomer().equals(fakeCustomer)) {
                    customerExists = true;
                    break;
                }
            }

            if (!customerExists) {
                System.out.println("Customer not found!");
                return false;
            }

            boolean continuous;
            do {
                // Đọc danh sách menu từ file
                List<Object> listCodeMenu = Utils.readObjectFromFile("mountainList.bin");
                if (listCodeMenu == null || listCodeMenu.isEmpty()) {
                    System.out.println("Menu list is empty or not found!");
                    return false;
                }

                // Chọn menu từ user
                FeastOrder selectedMenu = null;
                do {
                    String codeOfMenu = Utils.getString("Input mountain code: ");
                    for (Object obj : listCodeMenu) {
                        if (obj instanceof FeastOrder) {
                            FeastOrder menu = (FeastOrder) obj;
                            if (menu.getCodeOfSetMenu().equalsIgnoreCase(codeOfMenu)) {
                                selectedMenu = menu;
                                break;
                            }
                        }
                    }
                    if (selectedMenu == null) {
                        System.out.println("Menu code not found! Please try again.");
                    }
                } while (selectedMenu == null);

                // Nhập số bàn từ user
                int numOfTable = Utils.getInt("Input number of tables: ", 1, 10);

                // Tính tổng chi phí
                try {
                    int price = Integer.parseInt(selectedMenu.getPrice());
                    int cost = price * numOfTable;
                    System.out.println("Total Cost: " + cost);
                    check = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in menu data!");
                    return false;
                }

                continuous = Utils.confirmYesNo("Do you want to continue ordering? (Y/N): ");
            } while (continuous);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred!");
            e.printStackTrace();
        }
        return check;
    }
}
