/*
 * Jacob Marlowe
 */
package control;
import ae.cs.inv.HashSalt;
import ae.cs.inv.User;
import ae.cs.inv.UserDBprovided;
import ae.cs.inv.UserProfile;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.ValidationException;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.websocket.Session;
import org.owasp.esapi.errors.IntrusionException;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @throws java.security.NoSuchAlgorithmException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NoSuchAlgorithmException{

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String email = "";
		String password = "";
		String action = null;
		String url = "";
		boolean successFlag = false;
		boolean isAdmin = false; // SET TO TRUE IF THE USERNAME/PASSWORD MATCH TO DB ADMIN ACCOUNT

		try {
			//try { // VALIDATIONS
			email = ESAPI.validator().getValidInput("email validation",
					request.getParameter("email"), "Email", 200, false);
		} catch (ValidationException | IntrusionException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			password = ESAPI.validator().getValidInput("password validation",
					request.getParameter("password"), "SafeString", 200, false);
		} catch (ValidationException | IntrusionException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			action = ESAPI.validator().getValidInput("action",
					request.getParameter("action"), "SafeString", 200, false);
		} catch (ValidationException | IntrusionException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
		//UserDBprovided.validate(email, password
		if (UserDBprovided.validate(email, password)) {
			HttpSession session = request.getSession(true);
			if (true) {										// EMAIL/PASSWORD DOES MATCH --> USER CAN CONTINUE
				User account = new User();
				account = UserDBprovided.getUser(email, 0);
				session.setAttribute("account", account);
				session.setAttribute("accountName", account.getFirstName());
				isAdmin = false;
				session.setAttribute("isAdmin", false);
				if(account.getUserID().equals("name")){
					isAdmin = true;
					session.setAttribute("isAdmin", true);
				}
			}
			successFlag = true;
                        session.setAttribute("LoggedIn", true);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			UserProfile userProfile = (UserProfile) session.getAttribute("currentProfile");
		} else {
                        HttpSession session = request.getSession(true);// EMAIL/PASSWORD DON'T MATCH DATABASE --> THROWS ERROR AND TELLS USER TO TRY AGAIN
			email = ESAPI.encoder().encodeForHTML(email);
			password = ESAPI.encoder().encodeForHTML(password);
			successFlag = false;
                        session.setAttribute("LoggedIn", false);
			out.print("<p style=\"color:red; font-weight:bold; text-align: center; font-size: 16px;\">Sorry, email " + email + " or password " + password + " is not valid.</p>");
			RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
			rd.include(request, response);
		}
		out.close();
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @throws java.security.NoSuchAlgorithmException
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
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
			throws ServletException, IOException{
		try {
			processRequest(request, response);
		} catch (NoSuchAlgorithmException ex) {
			Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
