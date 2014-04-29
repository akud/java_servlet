<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.worldfusion.models.*,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Worldfusion Programming Exercise</title>
</head>
<body>
    <table>
    <thead>
        <tr>
            <th>Cancer Type</th>
            <th>All</th>
            <th>Year</th>
            <th>Month</th>
            <th>Recent</th>
        </tr>
    </thead>
    <tbody>
        <%
            for(ReferenceCount cancerReference : ((List<ReferenceCount>)request.getAttribute("cancerReferences"))){
        %>
            <tr>
                <td><%= cancerReference.getType() %></td>
                <td><%= cancerReference.getAllCount() %></td>
                <td><%= cancerReference.getYearCount() %></td>
                <td><%= cancerReference.getMonthCount() %></td>
                <td><%= cancerReference.getRecentCount() %></td>
            </tr>
        <% } %>
    </tbody>
    </table>
</body>
</html>