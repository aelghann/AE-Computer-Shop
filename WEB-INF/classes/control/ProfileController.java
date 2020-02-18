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
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           response.setContentType("text/html;charset=UTF-8");
            //UserDB userDB = new UserDB();
    UserProfile UP = new UserProfile();
    //returning an arraylist of users
    ArrayList<User> uDB = UserDBprovided.getAllUsers();
            HttpSession session = request.getSession();
        String theUser = null;
        try {
            theUser = ESAPI.validator().getValidInput("sessionID", session.getId(), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String itemCodeUpdate = null;
        try {
            itemCodeUpdate = ESAPI.validator().getValidInput("ItemCodeUPdate", request.getParameter("itemCodeUpdate"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Item selectedItem =   (Item) session.getAttribute("selectedItem");
        String action = null;
        try {
            action = ESAPI.validator().getValidInput("action", request.getParameter("action"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String userID = null ;
        try {
            userID = ESAPI.validator().getValidInput("userID", (String) session.getAttribute("userID"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
       // ArrayList<Item> itemList = (ArrayList) session.getAttribute("itemList"); // needs to be moved into validation part of save.
        String url = "";
        //the parameter returned by getparamterValues are stored in a list.
        //ArrayList itemList = new ArrayList<>(Arrays.asList(request.getParameterValues("itemList")));

        if (!validateInput(theUser)) {
            User user = uDB.get(0); // place holder. must be changed with dynamic data.
            session.setAttribute("theUser", user);
            // placeholder for the userSessionID
            //  session.setAttribute("theUser",theUser);
        } else if (validateInput(theUser)) {
            User user = new User();
            user = uDB.get(0);
            session.setAttribute("theUser", user);
            if (validateInput(action) && (action.equals("save") || action.equals("updateProfile")
                    || action.equals("updateRating") || action.equals("updateFlag") || action.equals("deleteItem") || action.equals("signout"))) {

                //maybe use a newly created method implemeting the code from catalog controller to select the appropriate item from the item database
                if (action.equals("save")) {
                    if(((UserProfile) session.getAttribute("currentProfile"))==null){
                    UP.addItem(userID, selectedItem, "0", "false");
                    session.setAttribute("currentProfile", UP);
                    session.setAttribute("profileItemsToView",UP.getItems());
                    url = "/profile.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                    }
                    else{
                    //User Profile Creates an item rating bean. In the ItemRating bean the userID is used to connect the item ratings with its propper user
                    UP = (UserProfile) session.getAttribute("currentProfile");
                    UP.addItem(userID, selectedItem, "0", "false");
                    session.setAttribute("currentProfile", UP);
                    session.setAttribute("profileItemsToView",UP.getItems());
                    url = "/profile.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                    }
                    //updateprofilelogic
                    //adding items to the userprofile code: UP.addItem(userID, itemList.get(i), "0", "false")
                } else if (action.equals("updateProfile")) {
                    UP =  (UserProfile) session.getAttribute("currentProfile");
                    for (int i = 0; i < UP.getItems().size(); i++) {
                        if (validateItemCode(UP.getItems().get(i).getItem().getItemCode())) {
                            if (UP.getItems().get(i).getItem().getItemCode().equals(itemCodeUpdate)) {
                                
                                url = "/feedback.jsp";
                                request.setAttribute("theItem", UP.getItems().get(i).getItem()); // the item object to be added to userProfile
                                request.setAttribute("itemCodeUpdate", UP.getItems().get(i).getItem().getItemCode()); // the item code to display the propper feedback page
                                getServletContext().getRequestDispatcher(url).forward(request, response);
                            }

                        } else {
                            url = "/profile.jsp";
                            getServletContext().getRequestDispatcher(url).forward(request, response);
                        }
                    }

                } //update profile logic
                else if (action.equals("updateRating")) {
                   UP =  (UserProfile) session.getAttribute("currentProfile");
                    for (int i = 0; i < UP.getItems().size(); i++) {
                        if (validateItemCode(UP.getItems().get(i).getItem().getItemCode())) {
                            if (UP.getItems().get(i).getItem().getItemCode().equals(itemCodeUpdate)) {
                                
                                
                                url = "/feedback.jsp";
                                request.setAttribute(itemCodeUpdate, UP.getItems().get(i).getRating());
                                request.setAttribute("itemCodeUpdate", UP.getItems().get(i).getItem().getItemCode());

                                int itemRating = Integer.parseInt(request.getParameter("rating"));

                                if (!validateItemRating(itemRating) && validateInput(Integer.toString(itemRating))) {
                                    url = "/profile.jsp";
                                    getServletContext().getRequestDispatcher(url).forward(request, response);

                                } else if (itemRating == 0) {

                                    UP.getItems().get(i).setRating("No Rating");

                                } else if (request.getParameter("rating").equals(UP.getItems().get(i).getRating())) //problem here
                                {
                                    String trueRating = properRating(request.getParameter("rating"));
                                    UP.getItems().get(i).setRating(trueRating);

                                } else if (!request.getParameter("rating").equals(UP.getItems().get(i).getRating())) {

                                    String trueRating = properRating(request.getParameter("rating"));
                                    UP.getItems().get(i).setRating(trueRating);

                                }
                                session.setAttribute("profileItemsToView",UP.getItems());

                                url = "/profile.jsp";

                                //request.setAttribute("theItem", itemList.get(i)); // the item object to be added to userProfile
                                // the item code to display the propper feedback page
                                getServletContext().getRequestDispatcher(url).forward(request, response);
                            }

                        } else {
                            url = "/profile.jsp";
                            getServletContext().getRequestDispatcher(url).forward(request, response);
                        }
                    }

                } else if (action.equals("updateFlag")) {
                    UP =  (UserProfile) session.getAttribute("currentProfile");
                    for (int i = 0; i < UP.getItems().size(); i++) {

                        if (validateItemCode(UP.getItems().get(i).getItem().getItemCode())) {
                            if (UP.getItems().get(i).getItem().getItemCode().equals(itemCodeUpdate)) {

                                String flag = request.getParameter(itemCodeUpdate);

                                if (validateInput(flag) && !(flag.equals("yes") || flag.equals("no"))) {
                                    url = "/profile.jsp";
                                    getServletContext().getRequestDispatcher(url).forward(request, response);
                                } else if (validateInput(flag) && (flag.equals("yes") || flag.equals("no"))) {
                                    if (flag.equals(UP.getItems().get(i).getBoughtIt())) {
                                        url = "/profile.jsp"; //unsure of this
                                        getServletContext().getRequestDispatcher(url).forward(request, response);
                                    } else if (!flag.equals(UP.getItems().get(i).getBoughtIt())) {
                                        //test = UP.getItems().get(i).getBoughtIt();
                                        UP.getItems().get(i).setBoughtIt(properFlag(flag));
                                        // test = UP.getItems().get(i).getBoughtIt();
                                    }

                                }

                                session.setAttribute("profileItemsToView",UP.getItems());

                                url = "/profile.jsp";
                                //request.setAttribute("theItem", itemList.get(i)); // the item object to be added to userProfile
                                //request.setAttribute("itemCodeUpdate", itemList.get(i).getItemCode()); // the item code to display the propper feedback page
                                getServletContext().getRequestDispatcher(url).forward(request, response);
                            }

                        }
                    }

                } else if (action.equals("deleteItem")) {
                    UP = (UserProfile) session.getAttribute("currentProfile");
                    for (int i = 0; i < UP.getItems().size(); i++) {

                        if (validateItemCode(UP.getItems().get(i).getItem().getItemCode())) {
                            String test = UP.getItems().get(i).getItem().getItemCode();
                            if (UP.getItems().get(i).getItem().getItemCode().equals(itemCodeUpdate)) {
                                UP.getItems().remove(i); //using ArrayList built in functionto remove the object in the specified index
                                session.setAttribute("profileItemsToView",UP.getItems());
                                url = "/profile.jsp";
                                getServletContext().getRequestDispatcher(url).forward(request, response);

                            } else if (!validateItemCode(UP.getItems().get(i).getItem().getItemCode())) {
                                url = "/profile.jsp";
                                getServletContext().getRequestDispatcher(url).forward(request, response);
                            }
                        }

                    }
                    
                }
            } else {
                url = "/profile.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            }

        try {
            action = ESAPI.validator().getValidInput("action", request.getParameter("action"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		if(action.equals("signout")){
			session.setAttribute("LoggedIn", false);
			session.removeAttribute("account");
			session.removeAttribute("accountName");
			session.removeAttribute("currentProfile");
			session.removeAttribute("profileItemsToView");
			url="/signin.jsp";
			getServletContext().getRequestDispatcher(url).forward(request, response);
		}
        
        if(action.equals("applyChanges")){
            
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
            String userIDToBeEdited = "";

            String question = "";

            try {
                userID = ESAPI.validator().getValidInput("uID", request.getParameter("uID"), "SafeString", 200, false);
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
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
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

            UserDBprovided.editProfile(userIDToBeEdited, firstName, lastName, emailAddress, address1, address2, city, state, postCode, country, question);
            UserDBprovided.addSalt(userIDToBeEdited, Salt);
            
            //session.setAttribute("usersToView", sDB);
            User account = new User();
            account = UserDBprovided.getUser(emailAddress, 0);
            session.setAttribute("account", account);
            url = "/viewprofile.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        }
        }
    
        
     
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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

    public Boolean validateItemCode(String itemCode) {

        boolean valid = false;
        log("This is from profile controller" + itemCode);
        if (!valid && itemCode.length() <=6) {
            valid = true;

        } else {
            valid = false;
        }
        return valid;
    }

    public boolean validateItemRating(int itemRating) {

        if ((itemRating <= 5 || itemRating < 0)) {
            return true;
        } else {
            return false;
        }

    }

    public String properRating(String numericalRating) {
        String starRating = "";

        switch (numericalRating) {
            case "0":
                starRating = "No Rating";
                break;
            case "1":
                starRating = "&#9733;";
                break;
            case "2":
                starRating = "&#9733;&#9733;";
                break;
            case "3":
                starRating = "&#9733;&#9733;&#9733;";
                break;
            case "4":
                starRating = "&#9733;&#9733;&#9733;&#9733;";
                break;
            case "5":
                starRating = "&#9733;&#9733;&#9733;&#9733;&#9733;";
                break;
        }

        return starRating;
    }

    public String properFlag(String wordFlag) {

        String boxFlag = "";

        switch (wordFlag) {
            case "yes":
                boxFlag = "&#9745";
                break;
            case "no":
                boxFlag = "&#9744;";
                break;
        }

        return boxFlag;
    }
}






