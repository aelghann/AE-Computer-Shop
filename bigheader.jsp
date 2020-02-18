<%-- 
    Document   : bigheader
    Created on : Apr 27, 2018, 9:28:59 PM
    Author     : Jacob Marlowe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <header class = "H">
		<c:choose>
			<c:when test="${account != null}">
				<a href="profile?action=signout" class="logInPlaceHolder">Sign Out <b><c:out value="${sessionScope.accountName}"/></b></a>
			</c:when>
            <c:otherwise>	
				<a href="signin.jsp" class="logInPlaceHolder"><b>Click Here to Log In</b></a>
			</c:otherwise> 
		</c:choose>
		<img src = "images/AElogo.png" alt = "graphical logo of ae" >
		<h1> AE Computer Shop </h1>
	</header>
</html>
</html>
