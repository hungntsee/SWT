<%-- 
    Document   : search
    Created on : Jun 7, 2021, 12:47:05 PM
    Author     : Admin
--%>

<%@page import="hungnt.registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <%
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            %> 
            <font color ="red"> Welcome, <%=cookies[cookies.length - 1].getName() %></font>
        <%
        }//find cookies are existed
        
        
        %>
        <h1>Search Page</h1>
        <form action="DispatchController">
            Search Value <input type="text" name="txtSearchLastName" 
                                value="<%= request.getParameter("txtSearchLastName")%>"/><br/>
            <input type ="submit" value ="Search" name="btnAction" />
        </form><br/>

        <%
            String searchValue = request.getParameter("txtSearchLastName");
            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border ="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String urlRewriting = "DispatchController"
                                + "?btnAction=delete"
                                + "&username=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
            <form action="DispatchController">
                <tr>
                    <th>
                        <%= ++count%>
                        .</th>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name ="txtUsername"
                               value ="<%=dto.getUsername()%>"/>
                    </td>
                    <td>
                        <input type=""text name ="txtPassword"
                               value ="<%=dto.getPassword()%>" />
                    </td>
                    <th>
                        <%= dto.getLastname()%>
                    </th>
                    <td>
                        <input type="checkbox" name="chkAdmin" value="ON"
                               <% if (dto.isRole()) {
                               %>
                               checked ="checked"
                               <%
                                   }
                               %>
                               />
                    </td>
                    <td>
                        <a href = "<%=urlRewriting%>">Delete </a>
                    </td>
                    <td>
                    <input type="submit" value="Update" name="btnAction" />
                    
                    <input type="hidden" name="lastSearchValue"
                                    value="<%=searchValue%>"
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
    } else {
    %> 
    <h2>
        No record is matched!!!
    </h2>
    <%
            }
        }//end if search Value has valid
    %>    
</body>
</html>
