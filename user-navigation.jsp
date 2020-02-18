<%-- 
    Document   : user-navigation
    Created on : Feb 18, 2018, 12:43:08 PM
    Author     : Abdalla
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<nav class = "N1">

	<form action="admin" id="form1">
		<input type="hidden" value="viewItems" name="action"/></form>

	<form action="admin" id="form2">
		<input type="hidden" value="viewUsers" name="action"/></form>

	<ul>
		<li><a href = "signin.jsp">Sign In</a>&ensp;&ensp;|&ensp;&ensp; </li>
		<li><a href = "profile.jsp" id = "P">Profile</a>&ensp;&ensp;|&ensp;&ensp; </li>

		<c:if test="${(sessionScope.isAdmin == true) && (sessionScope.LoggedIn == !null)}">
			<li><a href = "viewprofile.jsp">View Profile
					<input type="hidden" value="viewProfile" name="action"/>
				</a>
				&ensp;&ensp;|&ensp;&ensp;</li>
			<li>
				<button type="submit" form="form1">View Items</button>
				&ensp;&ensp;|&ensp;&ensp;</li>

			<li><button type="submit" form="form2">View Users</button>
				&ensp;&ensp;|&ensp;&ensp;</li>

		</c:if>
		<c:if test="${(sessionScope.LoggedIn == true) && (sessionScope.isAdmin == false)}">
			<li><a href = "viewprofile.jsp">View Profile
					<input type="hidden" value="viewProfile" name="action"/>
				</a>
				&ensp;&ensp;|&ensp;&ensp;</li>
        </c:if>
		<li><a href = "#">Cart</a></li>
	</ul>
</nav>
