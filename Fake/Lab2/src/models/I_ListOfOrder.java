/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public interface I_ListOfOrder {
    boolean createOfOrder(String customerCode, String menuCode, int numberOfTables, Date eventDate);
//
    boolean updateOrder(String id, String menuCode, Integer numberOfTables, Date eventDate);
}
