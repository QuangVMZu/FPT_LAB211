package sample.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
import java.util.ArrayList;
import java.util.Collections;

import sample.dto.I_List;
import sample.dto.Product;
import sample.utils.Utils;

public class ProductList extends ArrayList<Product> implements I_List {

    @Override
    public int find(String code) {
        int index = -1;
        index = this.indexOf(new Product(code));
        return index;
    }

    @Override
    public void add() {
        boolean check = true;
        String code;
        do {
            code = Utils.getString("Input Code:");
            int index = this.find(code);
            if (index == -1) {
                check = false;
            }
        } while (check);
        String name = Utils.getString("Input Name:");
        int size = Utils.getInt("Input Size[1,100]:", 1, 100);
        int price = Utils.getInt("Input price[1,1000]:", 1, 1000);
        this.add(new Product(code, name, size, price));

    }

    @Override
    public void remove() {
        String code = Utils.getString("Input code that you want to remove:");
        boolean confirmRemove = Utils.confirmYesNo("Ban co muon xoa hay ko ?(Y/N)");
        if (confirmRemove) {
            this.remove(new Product(code));
        }
    }

    @Override
    public void update() {
        String code = Utils.getString("Input code that you want to upade:");
        int index = this.find(code);
        if (index < 0 || index >= this.size()) {
            return;
        }
        String newName = Utils.getString("Input new name:", this.get(index).getName());
        this.get(index).setName(newName);

        int newSize = Utils.getInt("Input new size[1,100]:", 1, 100, this.get(index).getSize());
        this.get(index).setSize(newSize);

        int newPrice = Utils.getInt("Input new price[1,1000]:", 1, 1000, this.get(index).getPrice());
        this.get(index).setPrice(newPrice);
    }

    @Override
    public void sort() {
        Collections.sort(this);
    }
    

    @Override
    public void output() {
        System.out.println(this.get(0).toString());
    }

     

}
