/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.models;

import java.io.Serializable;

/**
 *
 * @author Wang
 */
public class StudentMountain implements Serializable {

    private Student student;
    private String mountainCode;
    private float fee;

    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
        StudentMountain that = (StudentMountain) obj;
        return this.student.getId().equalsIgnoreCase(that.student.getId());
    }

//    public boolean equalsByName(StudentMountain other) {
////        if (this == other) {
////            return true;
////        }
////        if (other == null || getClass() != other.getClass()) {
////            return false;
////        }
//        return this.student.getName().equalsIgnoreCase(other.student.getName());
//    }

    public StudentMountain() {
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public StudentMountain(Student student, String mountainCode, float fee) {
        this.student = student;
        this.mountainCode = mountainCode;
        this.fee = fee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-12s | %-30s | MT%02d            | %,-10.0f",
                this.student.getId(),
                this.student.getName(),
                this.student.getPhone(),
                this.student.getEmail(),
                Integer.parseInt(this.mountainCode),
                this.fee);
    }

    public String toStringByDelete() {
        return String.format("Student ID: %s\nName: %s\nPhone: %s\nEmail: %s\nMountain: MT%02d\nFee: %,.0f",
                this.student.getId(),
                this.student.getName(),
                this.student.getPhone(),
                this.student.getEmail(),
                Integer.parseInt(this.mountainCode), // Chuyển đổi mountainCode thành số nguyên
                this.fee);
    }

}
