/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Students {
    private String code;
    private String name;
    private String phone;
    private Date birthDay;
    private int grand;
    private String note;

    public Students() {
    }

    public Students(String code, String name, String phone, Date birthDay, int grand, String note) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.birthDay = birthDay;
        this.grand = grand;
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getGrand() {
        return grand;
    }

    public void setGrand(int grand) {
        this.grand = grand;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Students{" + "code=" + code + ", name=" + name + ", phone=" + phone + ", birthDay=" + birthDay + ", grand=" + grand + ", note=" + note + '}';
    }

}