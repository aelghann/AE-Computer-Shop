package ae.cs.inv;


import ae.cs.inv.ConnectionPool;
import ae.cs.inv.DBUtil;
import ae.cs.inv.Item;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nanajjar
 * ITIS 4166 Assignment 4
 * Class for Item DB access
 * @ Abdalla : added connection pooling
 */

public class ItemDBprovided {

    public static void createItemTable() {
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "CREATE TABLE Item\n" +
"(\n" +
"	ItemCode VARCHAR(50),\n" +
"    ItemName VARCHAR(50),\n" +
"	CatalogCategory VARCHAR(50),\n" +
"	Description VARCHAR(500),\n" +
"	Rating VARCHAR(250),\n" +
"	ImageUrl VARCHAR(50), \n" +
"	\n" +
"    PRIMARY KEY (ItemCode)\n" +
"\n" +
"\n" +
");";

        
        try {
            ps = connection.prepareStatement(query);
            /**
            statement.execute("CREATE TABLE item("
                    + "itemCode INT,itemName VARCHAR(50),"
                    + "categoryCode INT,catalogCategory VARCHAR(50),"
                    + "description VARCHAR(100),imageUrl VARCHAR(50),"
                    + "PRIMARY KEY (itemCode))");
                    * **/
            System.out.println("Created a new table: " + "ITEM");
            ps.executeUpdate();
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                System.out.println("ERROR: Could not create ITEM table: " + se);
            }
        }
                finally{
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);
            
        }
    }

    public static Item addItem(String itemCode, String itemName, String catalogCategory,
            String description, String rating, String imageUrl) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?)";
        // insert the new row into the table
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, itemCode);
            ps.setString(2, itemName);
            ps.setString(3, catalogCategory);
            ps.setString(4, description);
            ps.setString(5,rating);
            ps.setString(6, imageUrl);

            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into ITEM; dup primary key: " + itemCode);
            } else {
                System.out.println("ERROR: Could not add row to ITEM table: " + itemCode + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to ITEM table: " + itemCode);
            return null;
        }
                finally{
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);
            
        }
        System.out.println("Added item to ITEM table: " + itemCode);

        return new Item(itemCode, itemName, catalogCategory,
                description, rating, imageUrl);
    }

    public static Item addItem(Item item) {

         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO item VALUES (?, ?, ?, ?, ?, ?)";
        // insert the new row into the table
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, item.getItemCode());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getCatalogCategory());
            ps.setString(4, item.getDescription());
            ps.setString(5, item.getRating());
            ps.setString(6, item.getImageUrl());

            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into ITEM; dup primary key: " + item.getItemCode());
            } else {
                System.out.println("ERROR: Could not add row to ITEM table: " + item.getItemCode() + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to ITEM table: " + item.getItemCode());
            return null;
        }
                finally{
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);
            
        }
        System.out.println("Added item to ITEM table: " + item.getItemCode());

        // return the  item object
        return item;
    }

    public static Item getItem(String pcode) {

        Item item = new Item();
        item.setItemCode(pcode);
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT ItemName, CatalogCategory, Description, Rating, ImageUrl  FROM ITEM WHERE ITEM.ItemCode = \""+pcode+"\"";

        ResultSet resultSet = null ;

        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {

                item.setItemName(resultSet.getString("itemName"));
                item.setCatalogCategory(resultSet.getString("catalogCategory"));
                item.setDescription(resultSet.getString("description"));
                item.setImageUrl(resultSet.getString("imageUrl"));
                item.setRating(resultSet.getString("rating"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
                finally{
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(resultSet);
            pool.freeConnection(connection);
            
        }

        return item;

    }

    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        
        String itemCode = "";
        String itemName = "";
        String catalogCategory = "";
        String description = "";
        String rating = "";
        String imageUrl = "";
        
        String query = "SELECT itemCode, itemName, catalogCategory, description, rating, imageUrl FROM ITEM ";

        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemCode = resultSet.getString("itemCode");
                itemName = resultSet.getString("itemName");
                catalogCategory = resultSet.getString("catalogCategory");
                description = resultSet.getString("description");
                imageUrl = resultSet.getString("imageUrl");
                rating = resultSet.getString("rating");

                Item item = new Item(itemCode, itemName, catalogCategory, description, rating, imageUrl);
                items.add(item);
                System.out.println("Found item in Item table: " + itemCode);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "ItemDB.getAllItems()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }
        finally{
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(resultSet);
            pool.freeConnection(connection);
            
        }

        return items;
    }

    public static ArrayList<Item> getAllItems(String catalogCategory) {
        ArrayList<Item> items = new ArrayList<Item>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String query = "SELECT itemCode, itemName, catalogCategory, description, rating, imageUrl FROM ITEM WHERE catalogCategory = '" + catalogCategory + "' ORDER BY itemCode";
        
        String itemCode = "";
        String itemName = "";
        String description = "";
        String imageUrl = "";
        String rating = "";

        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                itemCode = resultSet.getString("itemCode");
                itemName = resultSet.getString("itemName");
                catalogCategory = resultSet.getString("catalogCategory");
                description = resultSet.getString("description");
                imageUrl = resultSet.getString("imageUrl");
                rating = resultSet.getString("rating");

                Item item = new Item(itemCode, itemName, catalogCategory, description, rating, imageUrl);
                items.add(item);
                System.out.println("Found item in ITEM table: " + itemCode);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "ItemDB.getAllItems()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }        finally{
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(resultSet);
            pool.freeConnection(connection);
            
        }

        return items;
    }

    public static List getCategories() {
        List categoryCodes = new ArrayList();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        String catalogCategory = "" ;
        String query = "SELECT DISTINCT catalogCategory FROM ITEM";
        int i = 0;
        try {
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                catalogCategory = resultSet.getString("catalogCategory");
                categoryCodes.add(catalogCategory);
                System.out.println("Found category in ITEM table: " + catalogCategory);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "ItemDB.getAllItems()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }        finally{
            DBUtil.closePrepredStatement(ps);
            DBUtil.closeResultSet(resultSet);
            pool.freeConnection(connection);
            
        }

        return categoryCodes;
    }
    
    public static void removeItem(String itemCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
        String itemToBeDeleted = itemCode;
        String query = "DELETE FROM item WHERE ItemCode = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, itemCode);
            ps.executeUpdate();

            System.out.println("Deleted Item " + itemCode);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }

    }
    
        public static void editItem(String itemCodeToBeEdited, String itemCode, String itemName , String catalogCategory, String description, String rating, String imageUrl) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps=null;
        String query = "UPDATE aecp.item SET ItemCode = ?, ItemName = ? ,CatalogCategory = ?, Description = ?, Rating = ?, ImageUrl = ?  WHERE ItemCode = ?";
            
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, itemCode);
            ps.setString(2, itemName);
            ps.setString(3, catalogCategory);
            ps.setString(4, description);
            ps.setString(5, rating);
            ps.setString(6, imageUrl);
            ps.setString(7, itemCodeToBeEdited);

            ps.executeUpdate();

            System.out.println("Updated Item " + itemCodeToBeEdited);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBprovided.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtil.closePrepredStatement(ps);
            pool.freeConnection(connection);

        }

    }
        
        	

    public static boolean exists(String itemCode) {
        Item p = getItem(itemCode);
        if (p != null) {
            return true;
        } else {
            return false;
        }
    }
}
