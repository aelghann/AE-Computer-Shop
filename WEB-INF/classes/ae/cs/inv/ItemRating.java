/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Abdalla
 */
public class ItemRating implements Serializable {

    
        
    private Item item; // itemCode
    private String rating;
    private String boughtIt;
    private String userID;

    
    public ItemRating()
    {   
        userID = "";
        item = new Item();
        rating = "";
        boughtIt = "";
    }

    ItemRating(String UID, Item item, String rating, String boughtIt) {
        this.userID = userID;
        this.item = item;
        this.rating = rating;
        this.boughtIt = boughtIt;
    }
    
    
    /**
     * @return the boughtIt
     */
    public String getBoughtIt() {
        return boughtIt;
    }

    /**
     * @param boughtIt the boughtIt to set
     */
    public void setBoughtIt(String boughtIt) {
        this.boughtIt = boughtIt;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }



    
}
