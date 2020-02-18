/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.io.Serializable;

/**
 *
 * @author Abdalla
 */
public class Item implements Serializable {


    
    private String itemCode;
    private String itemName;
    private String catalogCategory;
    private String description;
    private String rating;
    private String imageUrl;    
    
    
    public Item (){
        itemCode = "";
        itemName = "";
        catalogCategory = "";
        description = "";
        rating = "";
        imageUrl = "";
    }
    
    public Item(String itemCode, String itemName, String catalogCategory, String description, String rating, String imageUrl){
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.catalogCategory = catalogCategory;
        this.description = description;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    
        /**
     * @return the itemCode
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * @param itemCode the itemCode to set
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the catalogCategory
     */
    public String getCatalogCategory() {
        return catalogCategory;
    }

    /**
     * @param catalogCategory the catalogCategory to set
     */
    public void setCatalogCategory(String catalogCategory) {
        this.catalogCategory = catalogCategory;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
    
}

