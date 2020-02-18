/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ae.cs.inv.HashSalt;
import ae.cs.inv.Item;
import ae.cs.inv.ItemDBprovided;
import ae.cs.inv.User;
import ae.cs.inv.UserDBprovided;
import ae.cs.inv.UserProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author Abdalla
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    UserProfile UP = new UserProfile();

    //returning an arraylist of users
    ArrayList<User> uDB = UserDBprovided.getAllUsers();
    ArrayList<Item> iDB = ItemDBprovided.getAllItems();

    ArrayList<User> sDB = new ArrayList();
    ArrayList<Item> siDB = new ArrayList();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String userID = null;
        String action = "";
        String userIDToBeDeleted = null;
        String userIDToBeEdited = null;
        String url = "";
        //session and request variables
        HttpSession session = request.getSession();
        String theUser = null;
        try {
            theUser = ESAPI.validator().getValidInput("sessionID", session.getId(), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            userID = ESAPI.validator().getValidInput("userID", (String) session.getAttribute("userID"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            action = ESAPI.validator().getValidInput("action", request.getParameter("action"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //add a validation method to furthur ensure that the admin is logged in
        //maybe a session attribute from the sign in page.
// if action = viewUsers;
        if (action.equals("viewUsers")) {
            for (int i = 0; i < uDB.size(); i++) {
                if (!sDB.contains(uDB.get(i))) {
                    sDB.add(uDB.get(i));
                }

            }
            session.setAttribute("usersToView", sDB);
            url = "/user.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }

        if (action.equals("deleteUser")) {

            try {
                userIDToBeDeleted = ESAPI.validator().getValidInput("userIDToBeDeleted", request.getParameter("userIDToBeDeleted"), "SafeString", 200, false);
            } catch (ValidationException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            UserDBprovided.removeUser(userIDToBeDeleted);
            //repopulated the db in the session
            uDB = UserDBprovided.getAllUsers();
            sDB = new ArrayList();
            populate(uDB, sDB);
            session.setAttribute("usersToView", sDB);
            url = "/user.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }

        if (action.equals("addUser")) { // this logic adds a user to the DB. May use for registration.jsp
            String uID = "";
            String password = "";
            String firstName = "";
            String lastName = "";
            String emailAddress = "";
            String address1 = "";
            String address2 = "";
            String city = "";
            String state = "";
            String postCode = "";
            String country = "";
            String question = "";
            String Salt = HashSalt.getSalt();

            try {
                uID = ESAPI.validator().getValidInput("uID", request.getParameter("userID"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                password = ESAPI.validator().getValidInput("password", request.getParameter("password"), "SafeString", 200, false);
//                UserDBprovided.addPw(uID, password);
                password = password.concat(Salt); // adding the salt to the password
                    try {
                        password = HashSalt.hashPassword(password); //hashing  the saltedPassword before adding it to the user table
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                firstName = ESAPI.validator().getValidInput("firstName", request.getParameter("firstName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lastName = ESAPI.validator().getValidInput("lastName", request.getParameter("lastName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                emailAddress = ESAPI.validator().getValidInput("emailAddress", request.getParameter("emailAddress"), "Email", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                address1 = ESAPI.validator().getValidInput("address1", request.getParameter("address1"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                address2 = ESAPI.validator().getValidInput("address2", request.getParameter("address2"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                city = ESAPI.validator().getValidInput("city", request.getParameter("city"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                state = ESAPI.validator().getValidInput("state", request.getParameter("state"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                postCode = ESAPI.validator().getValidInput("postCode", request.getParameter("postCode"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                country = ESAPI.validator().getValidInput("country", request.getParameter("country"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                question = ESAPI.validator().getValidInput("question", request.getParameter("question"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            UserDBprovided.addUser(uID, password, firstName, lastName, emailAddress, address1, address2, city, state, postCode, country, question);
            UserDBprovided.setUserID(emailAddress);
            UserDBprovided.addSalt(UserDBprovided.getUser(emailAddress, 0).getUserID(),Salt);
            uDB = UserDBprovided.getAllUsers();
            sDB = new ArrayList();
            populate(uDB, sDB);
            session.setAttribute("usersToView", sDB);

            url = "/user.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }

        if (action.equals("editUser")) {

            String userIDph = "";

            try {
                userIDph = ESAPI.validator().getValidInput("userID ph", request.getParameter("userIDToBeEdited"), "SafeString", 200, false);
                session.setAttribute("userIDToBEdited", request.getParameter("userIDToBeEdited"));
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("userInfoPlaceHolder", UserDBprovided.getUser(userIDph));

            url = "/edituser.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }

        if (action.equals("applyChanges")) {

            String uID = "";
            String password = "";
            String firstName = "";
            String lastName = "";
            String emailAddress = "";
            String address1 = "";
            String address2 = "";
            String city = "";
            String state = "";
            String postCode = "";
            String country = "";
            String Salt="";

            String question = "";

            try {
                userID = ESAPI.validator().getValidInput("uID", request.getParameter("userID"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                password = ESAPI.validator().getValidInput("password", request.getParameter("password"), "SafeString", 200, false);
                Salt = HashSalt.getSalt(); //saving the generated salt in a variable.
                password = password.concat(Salt); // adding the salt to the password
                    try {
                        password = HashSalt.hashPassword(password); //hashing  the saltedPassword before adding it to the user table
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                firstName = ESAPI.validator().getValidInput("firstName", request.getParameter("firstName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lastName = ESAPI.validator().getValidInput("lastName", request.getParameter("lastName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                emailAddress = ESAPI.validator().getValidInput("emailAddress", request.getParameter("emailAddress"), "Email", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                address1 = ESAPI.validator().getValidInput("address1", request.getParameter("address1"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                address2 = ESAPI.validator().getValidInput("address2", request.getParameter("address2"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                city = ESAPI.validator().getValidInput("city", request.getParameter("city"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                state = ESAPI.validator().getValidInput("state", request.getParameter("state"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                postCode = ESAPI.validator().getValidInput("postCode", request.getParameter("postCode"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                country = ESAPI.validator().getValidInput("country", request.getParameter("country"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                userIDToBeEdited = ESAPI.validator().getValidInput("userIDToBeEdited", request.getParameter("uID"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                question = ESAPI.validator().getValidInput("question", request.getParameter("question"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            UserDBprovided.editUser(userIDToBeEdited, userID, password, firstName, lastName, emailAddress, address1, address2, city, state, postCode, country, question);
            UserDBprovided.updateSalt(userIDToBeEdited, Salt);
            uDB = UserDBprovided.getAllUsers();
            sDB = new ArrayList();
            populate(uDB, sDB);
            session.setAttribute("usersToView", sDB);
            url = "/user.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }

        if (action.equals("viewItems")) {
            
            populateItems(iDB, siDB);
            session.setAttribute("itemsToView",siDB);

            url = "/viewitems.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }
        
        if(action.equals("deleteItem")){
            
            String itemCodeToBeDeleted = "";
            
            try {
                itemCodeToBeDeleted = ESAPI.validator().getValidInput("itemCodeUpdate", request.getParameter("itemCodeUpdate"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(ItemDBprovided.exists(itemCodeToBeDeleted))
            {
            ItemDBprovided.removeItem(itemCodeToBeDeleted);
            iDB = ItemDBprovided.getAllItems();
            siDB = new ArrayList();
            populateItems(iDB, siDB);
            session.setAttribute("itemsToView", siDB);
			session.setAttribute("itemsDB", siDB);
            url = "/viewitems.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            else{
                System.out.println("could not delete" + itemCodeToBeDeleted + "beacause it was not found in the DB");
                 url = "/viewitems.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }
            
            
        }
        
        if(action.equals("addItem")){
            
            String itemCode = "";
            String itemName = "";
            String catalogCategory = "";
            String description = "";
            String rating = "";
            String imageUrl = "";
            
            try {
                itemCode = ESAPI.validator().getValidInput("itemCode", request.getParameter("itemCode"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                itemName = ESAPI.validator().getValidInput("itemName", request.getParameter("itemName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                catalogCategory = ESAPI.validator().getValidInput("catalogCategory", request.getParameter("catalogCategory"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                description = ESAPI.validator().getValidInput("description", request.getParameter("description"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rating = ESAPI.validator().getValidInput("rating", request.getParameter("rating"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                imageUrl = ESAPI.validator().getValidInput("imageUrl", request.getParameter("imageUrl"), "URL", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ItemDBprovided.addItem(itemCode, itemName, catalogCategory, description, properRating(rating), imageUrl);
            
            iDB = ItemDBprovided.getAllItems();
            siDB = new ArrayList();
            populateItems(iDB, siDB);
            session.setAttribute("itemsToView", siDB);
            url = "/viewitems.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }
        
         if (action.equals("editItem")) {

            String itemIDph = "";
            

            try {
                itemIDph = ESAPI.validator().getValidInput("userID ph", request.getParameter("itemCodeUpdate"), "SafeString", 200, false);
                session.setAttribute("itemIDToBEdited", request.getParameter("itemCodeUpdate"));
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }

            session.setAttribute("itemInfoPlaceHolder", ItemDBprovided.getItem(itemIDph));

            url = "/edititem.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }
        
        if(action.equals("applyChangesItem"))
        {
            String itemCodeToBeUpdated = "";
                        String itemCode = "";
            String itemName = "";
            String catalogCategory = "";
            String description = "";
            String rating = "";
            String imageUrl = "";
            
            try {
                itemCode = ESAPI.validator().getValidInput("itemCode", request.getParameter("itemCode"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                itemName = ESAPI.validator().getValidInput("itemName", request.getParameter("itemName"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                catalogCategory = ESAPI.validator().getValidInput("catalogCategory", request.getParameter("catalogCategory"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                description = ESAPI.validator().getValidInput("description", request.getParameter("description"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rating = ESAPI.validator().getValidInput("rating", request.getParameter("rating"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                imageUrl = ESAPI.validator().getValidInput("imageUrl", request.getParameter("imageUrl"), "URL", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                itemCodeToBeUpdated = ESAPI.validator().getValidInput("itemCodeToBeUpdated", request.getParameter("itemCodeToBeUpdated"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            ItemDBprovided.editItem(itemCodeToBeUpdated, itemCode, itemName, catalogCategory, description, properRating(rating), imageUrl);
            
            iDB = ItemDBprovided.getAllItems();
            siDB = new ArrayList();
            populateItems(iDB, siDB);
            session.setAttribute("itemsToView", siDB);
			session.setAttribute("itemsDB", siDB);
            url = "/viewitems.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            
            
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public boolean validateInput(String input) {
        boolean validInput = true;
        if (input == null) {
            validInput = false;
        }
        return validInput;
    }

    private void populate(ArrayList<User> uDB, ArrayList<User> sDB) {
        for (int i = 0; i < uDB.size(); i++) {
            if (!sDB.contains(uDB.get(i))) {
                sDB.add(uDB.get(i));
            }

        }

    }

    private void populateItems(ArrayList<Item> iDB, ArrayList<Item> siDB) {
        for (int i = 0; i < iDB.size(); i++) {
            if (!siDB.contains(iDB.get(i))) {
                siDB.add(iDB.get(i));
            }

        }
    }
     public String properRating(String numericalRating) {
        String starRating = "";

        switch (numericalRating) {
            case "0":
                starRating = "No Rating";
                break;
            case "1":
                starRating = "&ensp;&#9733;";
                break;
            case "2":
                starRating = "&ensp;&#9733;&ensp;&#9733;";
                break;
            case "3":
                starRating = "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;";
                break;
            case "4":
                starRating = "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;";
                break;
            case "5":
                starRating = "&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;";
                break;
        }

        return starRating;
    }

}
