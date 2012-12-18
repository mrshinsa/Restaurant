/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kevin
 */

public class Table {
    public final int size; //number of chairs around this table
    private int availableChairs; //number of empty chairs around this table
    private List<CustomerGroup> seatedCustomerList;
    public Table (int newSize) { 
   //     if ((newSize >= 2) && (newSize <= 6)) {
            availableChairs = size = newSize;       
            seatedCustomerList = new LinkedList();
     //   }
        
    } 
    public int getAvailableChairs()  {
        return availableChairs;
    }
    public void setAvailableChairs(int count)  {
        availableChairs = count;
    }
    public List<CustomerGroup> getSeatedCustomerList()  {
        return seatedCustomerList;
    }
 }