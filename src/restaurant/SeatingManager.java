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

public class SeatingManager {
//    2, 3, 4, 5 or 6 people.
//    private final int maxChairPerTable = 6;
//    Table[] restaurantTables;  
    private List<CustomerGroup> customerWaitingList;    
    private List<Table> tableList;
    
    /* Constructor */
    public SeatingManager(List tables) {   
        customerWaitingList = new LinkedList();
        tableList = tables;
        for (int i=0; i< tableList.size(); i++)  {            
            Table t = tableList.get(i);
            System.out.println("tableList size: " + t.size);
        }
    }

    /* Group arrives and wants to be seated. */
    public void arrives(CustomerGroup group) {
        if (group.size > 6 || group.size < 2) {
            return;
        }                    
        Table t = getEmptyTable(group.size); // get empty table, 
        if (t != null) {                          
            System.out.println("Your table is ready.  Please follow me.");
            List seatedCustomerList = t.getSeatedCustomerList();
            seatedCustomerList.add(group);
            t.setAvailableChairs(t.getAvailableChairs() - group.size);            
        }
        else if ((t = getAvailableTable(group.size)) != null) {   // get next available table,              
            System.out.println("Can I sit you with another party?");
            List seatedCustomerList = t.getSeatedCustomerList();
            seatedCustomerList.add(group);    
            t.setAvailableChairs(t.getAvailableChairs() - group.size);   
        }
        else { 
            System.out.println("All tables are full.  Please take a number.");
            customerWaitingList.add(group);    // add to waiting list.
        }
    }

    /* Whether seated or not, the group leaves the restaurant. */
    public void leaves(CustomerGroup group) {
        // remove from table if they are seating            
        Table t = locate(group);            
        if (t != null)  {  
            System.out.println("Thank you.  Please come again.");
            List seatedCustomerList = t.getSeatedCustomerList();
            seatedCustomerList.remove(group);   
            t.setAvailableChairs(t.getAvailableChairs() + group.size);   
            // see if we can seat another group
            for (int i=0; i < customerWaitingList.size(); i++) {    
                CustomerGroup nextGroup = customerWaitingList.get(i);
                if (nextGroup.size <= t.getAvailableChairs()) {                 
                    System.out.println("Right this way please.");                   
                    seatedCustomerList.add(nextGroup);    
                    t.setAvailableChairs(t.getAvailableChairs() - nextGroup.size); 
                    customerWaitingList.remove(nextGroup);
                }
            }
        }
        else if (customerWaitingList.contains(group)){  // otherwise, remove from waiting list
            System.out.println("We're so sorry. Please come visit us again.");
            customerWaitingList.remove(group);
        }
        else {  // should never reach here
            System.out.println("Huh? ");
        }
    }

    /* Return the table at which the group is seated, or null if
       they are not seated (whether they're waiting or already left). */
    public Table locate(CustomerGroup group) {
        for (int i = 0; i < tableList.size(); i++)  {
            List seatedCustomerList = tableList.get(i).getSeatedCustomerList() ;
            if (seatedCustomerList.contains(group)) {
                return tableList.get(i);
            }
        }
        return null;
    }

    /* Returns an empty table, or null if none left). */
    private Table getEmptyTable(int request) {
        for (int i = 0; i < tableList.size(); i++)  {
            List tableCustomerList = tableList.get(i).getSeatedCustomerList() ;
            if (tableCustomerList.isEmpty() && 
                    (tableList.get(i).getAvailableChairs() >= request)) {
                return tableList.get(i);
            }
        }
        return null;
    }

    /* Returns the best available table. */
    private Table getAvailableTable(int request) {
        for (int i = 0; i < tableList.size(); i++)  {
            if (tableList.get(i).getAvailableChairs() >= request) {
                return tableList.get(i);
            }
        }
        return null;
    }
 }

