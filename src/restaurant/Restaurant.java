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
public class Restaurant {

    static final int totalTableCount = 8;    
    static SeatingManager manager;
    static List<Table> restaurantTables;
    
    /**
     * @param args the command line arguments
     */
    /*
    public  void main(String[] args) {
        // TODO code application logic here
    }
    */
    
    public static void main(String[] args) {
        if (restaurantTables == null) {
            restaurantTables = new LinkedList();
        }
        for (int i = 0; i < totalTableCount; i++) {
            Table t =  new Table(2 + (int)(Math.random() * 5));
        //     Table t =  new Table(6);
            restaurantTables.add(t);
        }
        
        manager =  new SeatingManager(restaurantTables);
        
        CustomerGroup g1 = new CustomerGroup(6);
        CustomerGroup g2 = new CustomerGroup(6);
        CustomerGroup g3 = new CustomerGroup(6);
        CustomerGroup g4 = new CustomerGroup(6);
        CustomerGroup g5 = new CustomerGroup(6);
        CustomerGroup g6 = new CustomerGroup(6);
        
        manager.arrives(g1);
        manager.arrives(g2);
        manager.arrives(g3);
        manager.arrives(g4);
        manager.arrives(g5);
        manager.arrives(g6);        
    }
}


// I'm not sure if it's allowed, but I've added a tab to keep track of chairs left.  
// If this is not allowed, I would simply calculate the the chairs left dynamically as needed.
// I'm assuming we are talking about regular restaurants with table counts in the tens, 
// and not some convention sized restaurtants.  If the later, I would replace the table array
// with a table hashmap, for better searching and seating.  
// I also did not optimize the seating algorithm, ex. if two tables are available, 
// one with two chairs left and the other with four chairs left we can optimize it 
// a bit so that most number of people can sit.  However, I think it would be 
// less awkward to seat two groups of two people and have some chairs empty, 
// than to seat three groups of two people, hence I leave it open at this point .