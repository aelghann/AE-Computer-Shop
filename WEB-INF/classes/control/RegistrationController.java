/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ae.cs.inv.HashSalt;
import ae.cs.inv.User;
import ae.cs.inv.UserDBprovided;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;

/**
 *
 * @author Owner
 */
@WebServlet(name = "RegistrationController", urlPatterns = {"/RegistrationController"})
public class RegistrationController extends HttpServlet {

    ArrayList<User> uDB = UserDBprovided.getAllUsers();

    ;
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
        //uDB = UserDBprovided.getAllUsers();
        String url = "";
        //session and request variables
        HttpSession session = request.getSession();

        String theUser = null;
        try {
            theUser = ESAPI.validator().getValidInput("sessionID", session.getId(), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = null;
        try {
            action = ESAPI.validator().getValidInput("action", request.getParameter("action"), "SafeString", 200, false);
        } catch (ValidationException | IntrusionException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (validateInput(action) && (action.equals("register") || action.equals("forgetPassword"))) {

            if (action.equals("register")) {
                String userID = "U01h";
                String Salt = null;
                String password = null;
                String npassword= null;
                
                try {
                    password = ESAPI.validator().getValidInput("password", request.getParameter("password"), "SafeString", 200, false);
                    npassword=password;
  //                  UserDBprovided.addPw(userID, password);
                    Salt = HashSalt.getSalt(); //saving the generated salt in a variable.
                    password = password.concat(Salt); // adding the salt to the password
                    try {
                        password = HashSalt.hashPassword(password); //hashing  the saltedPassword before adding it to the user table
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String firstName = null;
                try {
                    firstName = ESAPI.validator().getValidInput("firstName", request.getParameter("firstName"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String lastName = null;
                try {
                    lastName = ESAPI.validator().getValidInput("lastName", request.getParameter("lastName"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String emailAddress = null;
                try {
                    emailAddress = ESAPI.validator().getValidInput("emailAddress", request.getParameter("emailAddress"), "Email", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String address1 = null;
                try {
                    address1 = ESAPI.validator().getValidInput("address1", request.getParameter("address1"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String address2 = null;
                try {
                    address2 = ESAPI.validator().getValidInput("address2", request.getParameter("address2"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String city = null;
                try {
                    city = ESAPI.validator().getValidInput("city", request.getParameter("city"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String state = null;
                try {
                    state = ESAPI.validator().getValidInput("state", request.getParameter("state"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String postCode = null;
                try {
                    postCode = ESAPI.validator().getValidInput("postCode", request.getParameter("postCode"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String country = null;
                try {
                    country = ESAPI.validator().getValidInput("country", request.getParameter("country"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String question = null;
                try {
                    question = ESAPI.validator().getValidInput("question", request.getParameter("question"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

                
                UserDBprovided.addUser(userID, password, firstName, lastName, emailAddress,
                        address1, address2, city, state,
                        postCode, country, question);
                //ading proper userID based off the ID in the table -->  security concerns ? solution: can premute the userID setter in UserDBprovided
                UserDBprovided.setRUserID(emailAddress);
               //saving the salt in the Salt table 
               UserDBprovided.addSalt(UserDBprovided.getUser(emailAddress, 0).getUserID(), Salt);

                //maybe add populate methods
                url = "/index.jsp";

            } else if (action.equals("forgetPassword")) {

                String emailAddress = null;
                String Salt ="";
                try {
                    emailAddress = ESAPI.validator().getValidInput("email", request.getParameter("email"), "Email", 500, true);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String question = null;
                try {
                    question = ESAPI.validator().getValidInput("question", request.getParameter("question"), "SafeString", 200, false);
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                String newPassword = null;
                try {
                    newPassword = ESAPI.validator().getValidInput("newPassword",  request.getParameter("newPassword"), "SafeString", 200, false);
                    Salt = HashSalt.getSalt(); //saving the generated salt in a variable.
                    newPassword = newPassword.concat(Salt); // adding the salt to the password
                    try {
                     newPassword = HashSalt.hashPassword(newPassword); //hashing  the saltedPassword before adding it to the user table
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ValidationException | IntrusionException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }

                User user = new User();
                for (int i = 0; i < uDB.size(); i++) {
                    if (uDB.get(i).getQuestion().equalsIgnoreCase(question) && uDB.get(i).getEmailAddress().equalsIgnoreCase(emailAddress)) {
                        user = uDB.get(i);
                        //user.setPassword(newPassword);
                        
                        UserDBprovided.updateSalt(UserDBprovided.getUser(emailAddress, 0).getUserID(), Salt);
                        
                        UserDBprovided.changePassword(newPassword, emailAddress, user);

                    }
                }
                
                url = "/index.jsp";
            }
            
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationController at " + request.getContextPath() + "</h1>");
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

    public boolean validateInput(String input) {
        boolean validInput = true;
        if (input == null) {
            validInput = false;
        }
        return validInput;
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

}
