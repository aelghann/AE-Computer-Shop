package ae.cs.inv;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nanajjar ITIS 4166 Assignment 4 Class to handle the USER table.
 * @co-author Abdalla ElGhannam ITIS 4166 Final Project
 */
public class UserDBprovided {

    public static void createUserTable() {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "CREATE TABLE users("
                + "userId VARCHAR(50),firstName VARCHAR(50),"
                + "lastName VARCHAR(50), emailAddr VARCHAR(50),"
                + "address1 VARCHAR(50), address2 VARCHAR(50),"
                + "city VARCHAR(50),state VARCHAR(50),zipcode VARCHAR(50),"
                + "country VARCHAR(50),"
                + "PRIMARY KEY (userId))";

        try {
            ps = connection.prepareStatement(query);
            ps.executeQuery(query);

            System.out.println("Created a new table: " + "USER");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                System.out.println("ERROR: Could not create USER table: " + se);
            }
        } finally {
            pool.freeConnection(connection);

        }
    }

    public static boolean addUser(String userID, String password, String firstName, String lastName, String email,
            String address1, String address2, String city, String state,
            String zipcode, String country, String question) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO aecp.user (UserID, Password, FirstName, LastName, EmailAddress, Address1, Address2, City,State,PostCode,Country,Question) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
        // insert the new row into the table
        try {
            ps = connection.prepareStatement(query);

            ps.setString(1, userID);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, email);
            ps.setString(6, address1);
            ps.setString(7, address2);
            ps.setString(8, city);
            ps.setString(9, state);
            ps.setString(10, zipcode);
            ps.setString(11, country);
            ps.setString(12, question);
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key");
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table");
            return false;
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        System.out.println("Added user to USER table");

        // user added successfully 
        return true;
    }

    public static boolean addUser(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, user.getUserID());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getEmailAddress());
            ps.setString(6, user.getAddress1());
            ps.setString(7, user.getAddress2());
            ps.setString(8, user.getCity());
            ps.setString(9, user.getState());
            ps.setString(10, user.getPostCode());
            ps.setString(11, user.getCountry());
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + user.getUserID());
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + user.getUserID() + " " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + user.getUserID());
            return false;
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        System.out.println("Added user to USER table: " + user.getUserID());

        // user added successfully 
        return true;
    }

    public static User getUser(String userId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String userID = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String question = "";

        String query = "SELECT UserID ,Password, FirstName, LastName, EmailAddress, Address1, Address2, City, State, PostCode, Country, Question FROM user WHERE UserID = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            // Find the speciic row in the table

            if (!rs.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + userId);
                return null;
            } else {
                userID = rs.getString("UserID");
                password = rs.getString("Password");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                email = rs.getString("EmailAddress");
                address1 = rs.getString("Address1");
                address2 = rs.getString("Address2");
                city = rs.getString("City");
                state = rs.getString("State");
                zipcode = rs.getString("PostCode");
                country = rs.getString("Country");
                question = rs.getString("Question");

                System.out.println("Found user in user table: " + userId);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        } finally {
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);

        }

        return new User(userId, password, firstName, lastName, email, address1, address2, city, state, zipcode, country, question);
    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String userId = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String question = "";

        String query = "SELECT UserID, Password, FirstName, LastName, EmailAddress, Address1, Address2, City, State, PostCode, Country, Question FROM user ORDER BY UserID";
        try {
            ps = connection.prepareStatement(query);
            // Find the speciic row in the table
            rs = ps.executeQuery(query);
            while (rs.next()) {
                userId = rs.getString("UserID");
                password = rs.getString("Password");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                email = rs.getString("EmailAddress");
                address1 = rs.getString("Address1");
                address2 = rs.getString("Address2");
                city = rs.getString("City");
                state = rs.getString("State");
                zipcode = rs.getString("PostCode");
                country = rs.getString("Country");
                question = rs.getString("Question");


                User user = new User(userId, password, firstName, lastName, email, address1, address2, city, state, zipcode, country, question);
                users.add(user);
                System.out.println("Found user in USER table: " + userId);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        } finally {
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);

        }

        return users;
    }

    //method to delete a specific user
    public static void removeUser(String userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String userToBeDeleted = userID;
        //it seems like i cant executemore than one statement.
        String query = "DELETE FROM aecp.user WHERE UserID = ?; ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userToBeDeleted);
            ps.executeUpdate();

            System.out.println("Deleted User " + userToBeDeleted);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        UserDBprovided.removeSalt(userID);
    }

    private static void removeSalt(String userID) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String userToBeDeleted = userID;
        String query = "DELETE FROM aecp.salt WHERE UserID = ?; ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userToBeDeleted);
            ps.executeUpdate();

            System.out.println("Deleted User " + userToBeDeleted);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
    }

    public static void editUser(String userIDToBeEdited, String userID, String password, String firstName, String lastName, String emailAddress, String address1, String address2, String city, String state, String postCode, String country, String question) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE aecp.user SET UserID = ?, Password = ? ,FirstName = ?, LastName = ?, EmailAddress = ?, Address1 = ? , Address2 = ?, City = ?, State = ? , PostCode = ? , Country= ?, Question = ? WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.setString(5, emailAddress);
            ps.setString(6, address1);
            ps.setString(7, address2);
            ps.setString(8, city);
            ps.setString(9, state);
            ps.setString(10, postCode);
            ps.setString(11, country);
            ps.setString(12, question);
            ps.setString(13, userIDToBeEdited);
            ps.executeUpdate();

            System.out.println("Updated User " + userIDToBeEdited);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }

    }
    
    
    
    
    
    public static void editProfile(String userIDToBeEdited,String firstName, String lastName, String emailAddress, String address1, String address2, String city, String state, String postCode, String country, String question) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE aecp.user SET FirstName = ?, LastName = ?, EmailAddress = ?, Address1 = ? , Address2 = ?, City = ?, State = ? , PostCode = ? , Country= ?, Question = ? WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, emailAddress);
            ps.setString(4, address1);
            ps.setString(5, address2);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, postCode);
            ps.setString(9, country);
            ps.setString(10, question);
            ps.setString(11, userIDToBeEdited);
            ps.executeUpdate();

            System.out.println("Updated User " + userIDToBeEdited);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }

    }
    
    

    public static boolean validate(String Email, String Pass) throws NoSuchAlgorithmException {
        boolean status = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SaltedPassword = Pass + UserDBprovided.getSalt(UserDBprovided.getUser(Email, 0).getUserID()); // using getUser(email, int) 

        String hashySalty = HashSalt.hashPassword(SaltedPassword);  // WILL SET STATUS TO FALSE IF DATA ISN'T POPULATED IN DATABASE
        //Pass = hashySalty;
        String query = "select * from aecp.user where EmailAddress=? and Password=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, Email);
            ps.setString(2, hashySalty); //trying to login as email = name@gmail.com , pass=password
            rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return status;
    }

    public static User getUser(String email, int x) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String userID = "";
        String password = "";
        String firstName = "";
        String lastName = "";
        String Email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        String question = "";

        String query = "SELECT UserID ,Password, FirstName, LastName, EmailAddress, Address1, Address2, City, State, PostCode, Country, Question FROM user WHERE EmailAddress = ?";
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            // Find the speciic row in the table

            if (!rs.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + email);
                return null;
            } else {
                userID = rs.getString("UserID");
                password = rs.getString("Password");
                firstName = rs.getString("FirstName");
                lastName = rs.getString("LastName");
                Email = rs.getString("EmailAddress");
                address1 = rs.getString("Address1");
                address2 = rs.getString("Address2");
                city = rs.getString("City");
                state = rs.getString("State");
                zipcode = rs.getString("PostCode");
                country = rs.getString("Country");
                question = rs.getString("Question");

                System.out.println("Found user in user table: " + email);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        } finally {
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);

        }
        return new User(userID, password, firstName, lastName, Email, address1, address2, city, state, zipcode, country, question);
    }

    //used in addUser to add a salt value linked to a UserID
    public static void addSalt(String uID, String salt) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "";

        /**
        if (UserDBprovided.exists(uID)) { // used when called in changePassword Method in UserDBprovided controller(might move it to profilecontroller)
            try {
                query = "UPDATE aecp.salt SET Salt = ? WHERE UserID = ? ";
                ps = connection.prepareStatement(query);
                ps.setString(1, salt);
                ps.setString(2, uID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
**/
            query = "INSERT INTO aecp.salt (UserID,Salt) values (?,?)";

            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, uID);
                ps.setString(2, salt);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtil.closePrepredStatement(ps);
                pool.freeConnection(connection);

            }
        

    }
    
    public static void updateSalt(String uID, String salt) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "";

        
         // used when called in changePassword Method in UserDBprovided controller(might move it to profilecontroller)
            try {
                query = "UPDATE aecp.salt SET Salt = ? WHERE UserID = ? ";
                ps = connection.prepareStatement(query);
                ps.setString(1, salt);
                ps.setString(2, uID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtil.closePrepredStatement(ps);
                pool.freeConnection(connection);

            }
        

    }
    
    /**
    //adds the original password to the user tbale
    public static void addPw(String uID, String password)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "";
        query = "INSERT INTO aecp.user (UserID, nPassword)  VALUES (?,?)";

            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, uID);
                ps.setString(2, password);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtil.closePrepredStatement(ps);
                pool.freeConnection(connection);

            }

    }
    **/
    
    // updates the user table with the original password
    /**
        public static void updatePw(String uID, String password)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "";
        query = "UPDATE aecp.user SET nPassword= ?  WHERE UserID=?";

            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, password);
                ps.setString(2, uID);
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtil.closePrepredStatement(ps);
                pool.freeConnection(connection);

            }

    }
    * **/
    
    /**
        public static String getPw(String uID)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pw="";
        String query = "";
        query = "SELECT Password FROM aecp.salt WHERE UserID = ?";

            try {
                ps = connection.prepareStatement(query);
                ps.setString(1, uID);
                rs = ps.executeQuery();
                while(rs.next())
                {
                    pw=rs.getString("Password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBUtil.closePrepredStatement(ps);
                pool.freeConnection(connection);

            }
            return pw;

    }
    * **/

    // used to return the salt in the table
    public static String getSalt(String uID) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String salt = "";

        String query = "SELECT Salt FROM aecp.salt WHERE UserID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, uID);
            rs = ps.executeQuery();
            while (rs.next()) {
                salt = rs.getString("Salt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        return salt;
    }

    public static void changePassword(String password, String email, User user) {

        String query = "UPDATE aecp.user SET Password = ? WHERE EmailAddress = ?";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, email);
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + user.getUserID());
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + user.getUserID() + " " + se.getCause());
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + user.getUserID());
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
    }

    private static String getUserID(String email) {

        String query = "Select ID From aecp.user WHERE EmailAddress = ?";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userID = "";

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                userID = rs.getString("ID");

            }

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not find ID for email: " + email);
            } else {
                System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email + " " + se.getCause());
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        return userID;

    }

    //makes sure that newly registered user have a proper userID 
    public static String setUserID(String email) {

        String query = "UPDATE aecp.user SET UserID=? WHERE ID=?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String userID = "U01" + UserDBprovided.getUser(email,0).getUserID();

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, UserDBprovided.getUserID(email));
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not find ID for email: " + email);
            } else {
                System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email + " " + se.getCause());
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        return userID;
    }
    
    public static String setRUserID(String email) {

        String query = "UPDATE aecp.user SET UserID=? WHERE ID=?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String userID = "UR01" + UserDBprovided.getUserID(email);

        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, UserDBprovided.getUserID(email));
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not find ID for email: " + email);
            } else {
                System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email + " " + se.getCause());
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not get UserID to USER table w/ email : " + email);
        } finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }
        return userID;
    }
     
 
    public static boolean exists(String userID) {
        User p = getUser(userID);
        if (p != null) {
            return true;
        } else {
            return false;
        }
    }
}
