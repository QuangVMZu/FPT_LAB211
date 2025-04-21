/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package sample.controllers;

import java.util.ArrayList;
import java.util.List;
import sample.models.I_List;
import sample.models.Mountain;
import sample.models.Student;
import sample.models.StudentMountain;
import sample.utils.Utils;
import sample.models.I_Menu;
import sample.models.MountainStatistic;

/**
 *
 * @author Wang
 */
public class StudentMountainList extends ArrayList<StudentMountain> implements I_List {

    @Override
    public boolean create() {
        boolean check = false;
        try {
            boolean checkDupliate = true;
            boolean continous = true;
            Student student = new Student();
            do {
                do {
                    String id = Utils.getString("Input student ID: ");
                    if (Utils.isValidID(id)) {
                        student.setId(id.toUpperCase());
                        continous = false;
                    }
                } while (continous);
                if (this.indexOf(new StudentMountain(student, null, 0)) == -1) {
                    checkDupliate = false;
                }
            } while (checkDupliate);
            //input name
            continous = true;
            do {
                String name = Utils.getString("Input student name: ");
                if (name.length() > 1 && name.length() < 21) {
                    student.setName(name);
                    continous = false;
                }
            } while (continous);
            //phone 
            continous = true;
            do {
                String phone = Utils.getString("Input student phone: ");
                if (Utils.isValidPhone(phone)) {
                    student.setPhone(phone);
                    continous = false;
                }
            } while (continous);
            //            email
            continous = true;
            do {
                String email = Utils.getString("Input student email: ");
                if (Utils.isValidEmail(email)) {
                    student.setEmail(email);
                    continous = false;
                }
            } while (continous);

            List<Object> listMountainCode = Utils.readObjectFromFile("mountainList.bin");
            continous = true;
            String mountainCode = "";
            do {
                mountainCode = Utils.getString("Input mountain code: ");
                if (listMountainCode.indexOf(new Mountain(mountainCode)) != -1) {
                    continous = false;
                }
            } while (continous);

            float fee = Utils.BASE_FEE;
            String first3Character = student.getPhone().substring(0, 3);
            if (first3Character.equals("097") || first3Character.equals("098")
                    || first3Character.equals("090") || first3Character.equals("091")) {
                fee = Utils.BASE_FEE - (Utils.BASE_FEE * Utils.DISCOUNT_RATE / 100);
            }
            this.add(new StudentMountain(student, mountainCode, fee));
            check = true;
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
            for (StudentMountain sm : this) {
                System.out.println(sm.toString());
//                System.out.println("\nLast Student:");
//                System.out.println(this.get(this.size() - 1).toString());
//                System.out.println("\nFirst Student:");
//                System.out.println(this.get(0).toString());
//                @Override
//                public Object getFirstStudent() {
//                    if (this.isEmpty()) {
//                        System.out.println("No student registered yet!");
//                        return null;
//                    }
//                    return this.get(0);
//                }
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
            Student fakeStudent = new Student();
            fakeStudent.setId(id.toUpperCase());

            StudentMountain fakeSM = new StudentMountain(fakeStudent, null, 0);
            int index = this.indexOf(fakeSM);
            if (index != -1) {
                StudentMountain target = this.get(index);

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
            Student fakeStudent = new Student();
            fakeStudent.setId(id.toUpperCase());
            StudentMountain stm = new StudentMountain(fakeStudent, null, 0);
            // Tìm kiếm StudentMountain với ID tương ứng
            int index = this.indexOf(stm);
            if (index != -1) { // Nếu tìm thấy đối tượng
                StudentMountain stc = this.get(index); // Lấy đối tượng stc đem đi check bằng index
                System.out.println("Found StudentMountain: ");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("Student ID | Name                 | Phone        | Email                          | Peak Code       | Fee         ");
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println(stc.toString());
                System.out.println("------------------------------------------------------------------------------------------------------------------");

                // Bắt đầu cập nhật thông tin
                Student student = stc.getStudent();

                String newName = Utils.updateString("Enter new name: ", student.getName());
                student.setName(newName);

                String newPhone;
                do {
                    newPhone = Utils.updateString("Enter new phone: ", student.getPhone());
                    if (newPhone.isEmpty() || Utils.isValidPhone(newPhone)) {
                        break;
                    }
                    System.out.println("Invalid phone number. Please try again.");
                } while (true);
                if (!newPhone.isEmpty()) {
                    student.setPhone(newPhone);
                }

                String newEmail;
                do {
                    newEmail = Utils.updateString("Enter new email: ", student.getEmail());
                    if (newEmail.isEmpty() || Utils.isValidEmail(newEmail)) {
                        break;
                    }
                    System.out.println("Invalid email format. Please try again.");
                } while (true);
                if (!newEmail.isEmpty()) {
                    student.setEmail(newEmail);
                }

                String newMountainCode;
                List<Object> listMountainCode = Utils.readObjectFromFile("mountainList.bin");
                do {
                    newMountainCode = Utils.updateString("Enter new mountain code: ", stc.getMountainCode());
                    if (newMountainCode.isEmpty() || listMountainCode.contains(new Mountain(newMountainCode))) {
                        break;
                    }
                    System.out.println("Invalid mountain code. Please try again.");
                } while (true);
                if (!newMountainCode.isEmpty()) {
                    stc.setMountainCode(newMountainCode); // gán newMountainCode vào stc
                }

                String first3Characters = student.getPhone().substring(0, 3);
                float newFee = Utils.BASE_FEE;
                if (first3Characters.equals("097") || first3Characters.equals("098")
                        || first3Characters.equals("090") || first3Characters.equals("091")) {
                    newFee = Utils.BASE_FEE * Utils.DISCOUNT_RATE / 100;
                }
                stc.setFee(newFee);

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
            for (StudentMountain stm : this) { // Duyệt tất cả các phần từ có trong danh sách this "StudentMountain"
                if (stm.getStudent() != null && stm.getStudent().getName().toUpperCase().contains(name.toUpperCase())) {
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
            for (StudentMountain stm : this) {
                if (stm.getStudent() != null
                        && name.substring(0, 2).equalsIgnoreCase(stm.getStudent().getId().substring(0, 2))) {
                    newList.add(stm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }

    @Override
    public List<Object> statistic() {
        List<Object> newList = new ArrayList<>();
        List<String> checked = new ArrayList<>();

        try {
            for (StudentMountain stm : this) {
                String mountainCode = stm.getMountainCode();

                if (checked.contains(mountainCode)) {
                    continue;
                }

                int counts = 0;
                float totalCost = 0;
                for (StudentMountain other : this) {
                    if (mountainCode.equals(other.getMountainCode())) {
                        counts++;
                        totalCost += other.getFee();
                    }
                }

                // Tạo đối tượng MountainStatistic và thêm vào danh sách kết quả
                MountainStatistic statistic = new MountainStatistic(mountainCode, counts, totalCost);
                newList.add(statistic);

                checked.add(mountainCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newList.isEmpty()) {
            System.out.println("No student have registered!");
        } else {
            System.out.println("Statistics of Registration by Mountain Peak:");
            System.out.println("---------------------------------------------------------------------");
            System.out.printf("%-12s | %-20s | %-15s%n", "Peak Name", "Number of Participants", "Total Cost");
            System.out.println("---------------------------------------------------------------------");

            for (Object obj : newList) {
                MountainStatistic statistic = (MountainStatistic) obj; // Ép kiểu về MountainStatistic
                System.out.println(statistic.toStringByStatic());
            }
            System.out.println("---------------------------------------------------------------------");
        }
        return newList;
    }

    @Override
    public Student getLastStudent() {
       return this.get(this.size() - 1).getStudent();
    }
    
    @Override
    public Student getFirstStudent() {
       return this.get(0).getStudent();
    }
}