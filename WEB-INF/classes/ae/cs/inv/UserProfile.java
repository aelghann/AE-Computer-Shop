/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.io.Serializable;
import java.util.ArrayList;
import ae.cs.inv.UserDB;

/**
 *
 * @author Abdalla
 * 
 * Needs to be implemented with a DB to prevent duplicate user profiles.
 */
public class UserProfile implements Serializable {

   //flag for existing ratings for items 
    private Boolean f = false;

    private ArrayList<ItemRating> DB = new ArrayList<>();
    private UserDB UDB = new UserDB();
    private ArrayList<User> u = UDB.getUsers(); 
    private String userID = u.get(0).getUserID();

    public void addItem(String UID, Item item, String rating, String boughtIt) {
        
        UID = userID;
        
        ItemRating i = new ItemRating(UID, item, rating, boughtIt);
        int j;
        //checking if the item already exits in the user's profile
        for (j = 0; j < DB.size(); j++) {
            if (i.getItem().getItemName().equals(DB.get(j).getItem().getItemName())) {
                f = true;
                break;
            }
            
           

        }

        //making sure to replace the current rating if the item already exists in the user's profile
        if (f == true) {
            DB.get(j).setRating(i.getRating());
        } //otherwise add the item to the user's profile
        else if (f == false) {

            DB.add(i);

        }
        
       

    }

    public void removeItem(String item) {

        Boolean exist = false;
        int i;
        for (i = 0; i < DB.size(); i++) {
            if (DB.get(i).getItem().getItemName().equals(item)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            DB.get(i).setRating("");
        } else {
            System.out.println("Item doesnt exist");
        }

    }

    public ArrayList<ItemRating> getItems() {
        return DB;
    }

    public void emptyProfile() {

        DB.clear();
    }
}
