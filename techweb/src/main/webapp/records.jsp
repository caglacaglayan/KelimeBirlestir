<%--
  Created by IntelliJ IDEA.
  User: CAGLA
  Date: 26.03.2023
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="netrox.Process" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Yazılım Laboratuvarı 2.1</title>
    <div style="text-align: center;">
        <h1>Metin Birleştirme Programı</h1>
    </div>
</head>
<body>
<style>
    body {
        background: linear-gradient(to right, #93e4c1, #3baea0, #118a7e, #1f6f78);
    }

    h1 {
        font-size: 3rem;
        text-align: center;
        color: white;
        padding-top: 2rem;
    }

    button {
        padding: 8px 16px;
        background-color: #007bff;
        color: #fff;
        margin-left: 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
</style>

<%!
    Process process = new Process();
    List<String> records = process.ListRecords();
%>
<%for (int i = 0; i < records.size(); i++) { %>
        <h4><%=records.get(i)%></h4>
<% } %>

<br>
<a href="index.jsp"><Button> Go Back </Button></a>
<br><br>
</body>
</html>