package sample.dto;

/* Interface for a group of objects
 */

/**
 *
 * @author Hoa Doan
 */
public interface I_List {
  // Find the position of element which has code equal parameter coe
  int find(String code);
  // add new element( input from scanner) to I_List
  void add(); 
  // Input the code wanna remove
  void remove();
  // input the code want to update, after that update other information--> use set method
  void update();
  // sort list use Collections.sort(this, new Comparator<Clock>()..., sort based price or name
  void sort();
  // show detail of each element of List
  void output();
  
}
