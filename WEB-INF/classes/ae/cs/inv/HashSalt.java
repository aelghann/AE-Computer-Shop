/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.cs.inv;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jacob Marlowe
 */
public class HashSalt {

	public static String hashPassword(String password)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);

		for (byte b : mdArray) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}

	public static String getSalt() {
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}

	public static String hashAndSaltPassword(String password)
			throws NoSuchAlgorithmException {
		String salt = getSalt();
		return hashPassword(password + salt); //following this logic to reproduce the password in validtion is UserDBprovided
	}

	public static void main(String[] args) {
		try {
			System.out.println("Hash for 'sesame'     : " + hashPassword("sesame"));
			System.out.println("Random salt     : " + getSalt());
			System.out.println("Salted hash for 'sesame'     : " + hashAndSaltPassword("sesame"));
		} catch (NoSuchAlgorithmException ex) {
			System.out.println(ex);
		}
                
                
                String salt = getSalt(); // will be saved in salt table....  should try adding a user with the salted password first and then captureing the salt while the user is being added
                String password = "password5";
                System.out.println("salt for " + password + " : " + salt);
                
                
            //concatenating the salt value to "password" then hashing. Expect value for each run should be the same.
            // we can have a new method that accepts the salt as a parameter , but i think it would be risky
            System.out.println("hash for Salted 'password'. the value hashed  :" + password + "  " +  salt);
            try {
                System.out.println(hashPassword(password + salt));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HashSalt.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //hashed and salted vaule for "password" and kjwb1cOkgkInOE7Ge6goM5ONjcGxKJ3PiQAo52s6GkE= is
            //  ---> this is what will be saved in the password field in the user table and since we save the salt value once created then we can recreate the password when comparing
            //the above value was hard coded into the user table for testing purposes.
        }
        

}
