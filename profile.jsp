<%-- 
    Document   : profile
    Created on : Feb 18, 2018, 1:24:59 PM
    Author     : Abdalla


This used to be myParts.jsp and it was changed to profile.jsp to match the wording in ITCS 4166 assignment 3.
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ae.cs.inv.UserProfile"%>
<%@page import="ae.cs.inv.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page= "header.jsp" />

<link rel="stylesheet" href="styles/profile.css" />
<!-- logo from http://automotivexcellence.com.au/product/ae-sticker-small-logo/ -->

<jsp:include page = "bigheader.jsp" />  

<!-- Breadcrumb Nav-->
<jsp:include page = "user-navigation.jsp" />

<main>
    <table>
        <tr>
            <th>Item Name</th>
            <th>Img</th>
            <th>Rating</th>
            <th>Bought It</th>
            <th></th>
        </tr>


       <%-- <c:set var= "userProfileItems" value = "${sessionScope.currentProfile.DB}" />--%>
        <c:forEach items = "${sessionScope.profileItemsToView}"  var= "userProfileItems">
            <tr>
                <td><c:out value = "${userProfileItems.item.itemName}"/></td><!-- returns selected item name-->
                <td><img src = "<c:out value = "${userProfileItems.item.imageUrl}"/>" alt="partImage"></td><!-- i could not add the img source from the items rating, but since is have the selected image object it should work only for one part -->


                <td><c:out value="${userProfileItems.rating}" escapeXml="false"/></td> 
                <td><c:out value = "${userProfileItems.boughtIt}" escapeXml="false"/></td>
                <td>
                    <!-- this was categories --> 
                    <form action="profile" method="post">
                        <input type = "hidden" value = "<c:out value = "${userProfileItems.item.itemCode}"/>" name = "itemCodeUpdate"> <!-- TO be fixed-->
                        <input type = "hidden" value = "updateProfile" name = "action">
                        <input type="submit" value="Update" class = "button"/>
                    </form>
                </td>
                <td>
                    <form action="profile" method="post">
                        <input type="submit" value="Delete" class = "button"/>
                        <input type = "hidden" value = "<c:out value= "${userProfileItems.item.itemCode}" />" name = "itemCodeUpdate"> <!-- TO be fixed-->
                        <input type = "hidden" value = "deleteItem" name = "action">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <%--
        <%
            /**
             * The items from the user profile are displayed. once it is updated
             * from myparts.jsp these items should be stored in the profile.jsp
             * and myparts should be empty.
             */
            int size = 0;
            if (session != null && session.getAttribute("currentProfile") != null) {

            UserProfile up = (UserProfile) session.getAttribute("currentProfile");
            ArrayList<ItemRating> itemRatings = up.getItems();
            size = itemRatings.size();
            ArrayList<Item> itemList = new ArrayList<>();

            for (int i = 0; i < size; i++) {
    %>   
    <tr>
        <td><%=itemRatings.get(i).getItem().getItemName()%></td><!-- returns selected item name-->
        <td><img src = "<%=itemRatings.get(i).getItem().getImageUrl()%>" alt="partImage"></td><!-- i could not add the img source from the items rating, but since is have the selected image object it should work only for one part -->


        <td><%=itemRatings.get(i).getRating()%></td> 
        <td><%=itemRatings.get(i).getBoughtIt()%></td>
        <td>
            <!-- this was categories --> 
            <form action="profile" >
                <input type = "hidden" value = "<%=itemRatings.get(i).getItem().getItemCode()%>" name = "itemCodeUpdate"> <!-- TO be fixed-->
                <input type = "hidden" value = "updateProfile" name = "action">
                <input type="submit" value="Update" class = "button"/>
            </form>
        </td>
        <td>
            <form action="profile" >
                <input type="submit" value="Delete" class = "button"/>
                <input type = "hidden" value = "<%=itemRatings.get(i).getItem().getItemCode()%>" name = "itemCodeUpdate"> <!-- TO be fixed-->
                <input type = "hidden" value = "deleteItem" name = "action">
            </form>
        </td>
    </tr>
    <%
                itemList.add(itemRatings.get(i).getItem());
            }
            session.setAttribute("itemList", itemList); //arayList of the items saved.
        }
    %>

    <!--            <td>NVIDIA K40 GPU</td>
                <td><img src = "images/GPU3.png" alt = "HHGPU" ></td>
                <td>&#9733;&ensp;&#9733;&ensp;&#9733;&ensp;&#9733;</td>-->



</table> --%>
</main>
<!--Site navigation -->
<jsp:include page = "site-navigation.jsp" />
<jsp:include page = "footer.jsp" />


<!--

    to be used to indicate the flag &#9745;
    tick if the sure bought it 
    if the user did not buy the item &#9744;
-->