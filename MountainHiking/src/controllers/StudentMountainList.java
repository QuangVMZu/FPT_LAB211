/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import models.I_List;
import models.Student;
import models.StudentMountain;
import utils.Utils;

/**
 *
 * @author ADMIN
 */
public class StudentMountainList extends ArrayList<StudentMountainList> implements I_List {

    @Override
    public boolean create() {
        // input id
        boolean check = false;
        try {
            boolean checkDupliate = true;
            Student student = new Student();
            boolean continous = true;
            do {
                do {
                    String id = Utils.getString("Input student ID: ");
                    if (Utils.isValiateID(id)) {
                        student.setId(id);
                        continous = false;
                    }
                } while (continous);
            } while (checkDupliate);
            if (this.indexOf(new StudentMountain(student, null, 0)) == -1) {
                checkDupliate = false;
            }

            // input name
            continous = false;
            do {
                String name = Utils.getString("Input student name: ");
                if (name.length() > 1 && name.length() < 21) {
                    student.setName(name);
                    continous = false;
                }
            } while (continous);

            // input phone
            continous = false;
            do {
                String phone = Utils.getString("Input student phone: ");
                if (Utils.isValidPhone(phone)) {
                    student.setPhone(phone);
                    continous = false;
                }
            } while (continous);

            //input email    
            continous = false;
            do {
                String email = Utils.getString("Input student email: ");
                if (Utils.isValidEmail(email)) {
                    student.setEmail(email);
                    continous = false;
                }
            } while (continous);

        } catch (Exception e) {

        }
        return check;
    }

    @Override
    public boolean undate(String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
