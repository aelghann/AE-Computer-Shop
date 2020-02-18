/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.util.ArrayList;

/**
 *
 * @author Abdalla
 */
public class UserDB {
    
    private ArrayList<User> userDB = new ArrayList<>();
    
    public UserDB()
    {
            User u1 = new User("U011","password1","Double","Energy","DE@gmail.com","5941 Crystals St","Suit 767","ICity","Solid","35942","MB","charlotte");
            
            userDB.add(u1);
    
    }

    
    public ArrayList<User> getUsers(){
        
        return userDB;
    }
    
    
    
}
