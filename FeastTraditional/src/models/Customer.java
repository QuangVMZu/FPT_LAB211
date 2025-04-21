/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Wang
 */
public class Customer implements Serializable {

    private String customerCode;
    private String name;
    private String phoneNumber;
    private String email;

    public Customer() {
    }

    @Override
    public boolean equals(Object obj) {
        Customer i = (Customer) obj;
        return (this.getCustomerCode().equals(i.getCustomerCode()) && this.getName().equalsIgnoreCase(i.getName()));
    }

    public Customer(String customerCode, String name, String phoneNumber, String email) {
        this.customerCode = customerCode;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        // Chia tên thành các phần và xử lý để có tên cuối, họ tên đệm
        String[] nameParts = this.name.split(" ");

        // Hàm để viết hoa chữ cái đầu và giữ nguyên phần còn lại
        StringBuilder formattedName = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                // Viết hoa chữ cái đầu, phần còn lại là chữ thường
                formattedName.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Nếu tên có nhiều phần (họ, tên đệm, tên cuối)
        if (nameParts.length > 1) {
            // Tên cuối là phần tử cuối cùng trong mảng
            String lastName = nameParts[nameParts.length - 1];

            // Các phần còn lại là họ tên đệm
            StringBuilder middleName = new StringBuilder();
            for (int i = 0; i < nameParts.length - 1; i++) {
                middleName.append(nameParts[i]).append(" ");
            }

            // Trả về định dạng mong muốn: "Tên cuối, Họ tên đệm"
            return String.format(" %-16s | %-25s | %-12s | %-30s",
                    this.customerCode,
                    lastName + ", " + middleName.toString().trim(),
                    this.phoneNumber,
                    this.email);
        } else {
            // Nếu chỉ có một phần, thì coi đó là tên duy nhất
            return String.format(" %-16s | %-25s | %-12s | %-30s",
                    this.customerCode,
                    formattedName.toString().trim(),
                    this.phoneNumber,
                    this.email);
        }
    }
}
