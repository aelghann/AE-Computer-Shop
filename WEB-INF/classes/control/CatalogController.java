/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ae.cs.inv.Item;
import ae.cs.inv.ItemDB;
import ae.cs.inv.ItemDBprovided;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class CatalogController extends HttpServlet {

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

        System.out.println("you have accessed the catalog controller servlet");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Item> items = ItemDBprovided.getAllItems();
        
        
        HttpSession session = request.getSession();
        
        boolean done = false;
        String url;

        String ssp = request.getRequestURI(); //   /AE_Computer_Parts/
        
        
        
        if(ssp.equals("/AE_Computer_Parts/") && !done)
        {
           session.setAttribute("itemsDB",items);
            System.out.println("itemsDB has been set");
            done = true;
            url = "/index.jsp";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }
        
        else{
        
        

   

        String itemCode = null;
            try {
                itemCode = ESAPI.validator().getValidInput("itemCode", request.getParameter("itemCode"), "SafeString", 200, false);
            } catch (ValidationException | IntrusionException ex) {
                Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
            }

        boolean valid = false;
        log(itemCode);
        if ( !valid  && itemCode.length() <=6 ) {
            valid = true;
                /**
                 * ItemDB DB = new ItemDB(); List<Item> items = DB.getItems(); *
                 */
              

            url = "/items.jsp";
            
            
            request.setAttribute("item",ItemDBprovided.getItem(itemCode));
            
            /**
            if (itemCode.equals("GPU1")) {
                request.setAttribute("item", items.get(0));
                session.setAttribute("selectedItemIndex", 0);
            } else if (itemCode.equals("GPU2")) {
                request.setAttribute("item", items.get(1));
                session.setAttribute("selectedItemIndex", 1);
            } else if (itemCode.equals("GPU3")) {
                request.setAttribute("item", items.get(2));
                session.setAttribute("selectedItemIndex", 2);
            } else if (itemCode.equals("CPU1")) {
                request.setAttribute("item", items.get(3));
                session.setAttribute("selectedItemIndex", 3);
            } else if (itemCode.equals("CPU2")) {
                request.setAttribute("item", items.get(4));
                session.setAttribute("selectedItemIndex", 4);
            } else if (itemCode.equals("CPU3")) {
                request.setAttribute("item", items.get(5));
                session.setAttribute("selectedItemIndex", 5);
            }
            * **/

            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);

        } else if (!valid) {
            url = "/categories.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
        

        if (!validateInput(itemCode)) {
            url = "/categories.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        }
        
        
        String message = "Item Code is invalid";
        try (PrintWriter out = response.getWriter()) {
            // TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CatalogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CatalogController at " + request.getContextPath() + "</h1>");
            out.println(message);
            out.println("</body>");
            out.println("</html>");
        }

    
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

}
