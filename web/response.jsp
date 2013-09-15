<%-- 
    Document   : response
    Created on : 11 Σεπ 2013, 5:56:14 μμ
    Author     : k
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel='stylesheet' type='text/css' href='newCascadeStyleSheet2.css' >
<html>
    <head> 
        <title>Response page </title>
    </head>
    <body>
        <h1> </h1>
               <h2>Reformulate your query</h2> 
        <p></p>
        <FORM method='post' ACTION='upload1' >
            <input type='text' name='name' value="<%= request.getAttribute("name2")%>" >
            <input type='submit' value='submit'> 
            <p></p>

        </FORM>
        <p></p>
        <c:if test="${not empty Syn}">     
            <h2> system suggestions</h2>
            <form method="post" action='upload1'>          
                <select name='name'>

                    <c:forEach var="ar" items="${Syn}">
                        <option><c:out value="${ar}"/></option>
                    </c:forEach>           
                </select>
                <input type="submit" value="Submit">
            </form>
        </c:if>
        <c:if test="${empty Syn}"> 
            <h2>No system suggestions </h3>
            </c:if>
        <p></p>
        <p> </p>
        <p> </p>
        <h2>
            Your Previous Queries:
        </h2>

        <form method='post' ACTION='upload1' > 
            <select name='name'>
                <c:forEach var="prev" items="${previousQueries}">
                    <option><c:out value="${prev}"/></option>
                </c:forEach>  
            </select>
        </form>
        <h2>Search Results </h2>
        <div id="fr">
            <p> <iframe src="<%=request.getAttribute("url")%>" height='1000' width='1000'   >
                </iframe> </p>
        </div>
</html>
</body>

