<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="netrox.Process" %>
<%@ page import="netrox.Record" %>
<%@ page import="java.io.IOException" %>
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

  .container {
    display: flex;
    align-items: center;
  }

  .text-box {
    width: 100%;
    margin-bottom: 20px;
    margin-left: 5px;
  }

  input[type="number"] {
    width: 10%;
    padding: 10px;
    margin-bottom: 20px;
    margin-left: 10px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 5px rgba(0,0,0,0.1);
  }
  output[type="text"] {
    width: 10%;
    padding: 10px;
    margin-bottom: 20px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 5px rgba(0,0,0,0.1);
  }

  .text-box input[type="text"] {
    width: 50%;
    height: 30px;
    margin-left: 10px;
    padding: 10px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 5px rgba(0,0,0,0.1);
    resize: none;
  }

  .text-box output[type="text"] {
    width: 70%;
    height: 30px;
    padding: 10px;
    border-radius: 5px;
    border: none;
    box-shadow: 0px 0px 5px rgba(0,0,0,0.1);
    resize: none;
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

  <br>
  Enter a number between 2 and 5:
  <input type="number" id="inputNumber" value="2" name="inputNumber" min="2" max="5" onchange="showTextBoxes()">
  <br>
  <div class="text-box input" style="display: block;">
    First text:  <input type="text" id="textBox" value="metin1" name="textBox">
    <br>
  </div>
  <div class="text-box input" style="display: block;">
    Second text: <input type="text" id="textBoxTwo" value="metin2" name="textBoxTwo">
    <br>
  </div>
  <div class="text-box input" style="display: none;">
    Third text:  <input type="text" id="textBoxThree" value="metin3" name="textBoxThree">
    <br>
  </div>
  <div class="text-box input" style="display: none;">
    Fourth text: <input type="text" id="textBoxFour" value="metin4" name="textBoxFour">
    <br>
  </div>
  <div class="text-box input" style="display: none;">
    Fifth text:  <input type="text" id="textBoxFive" value="metin5" name="textBoxFive">
  </div>
<br>
<Button name="combine" id="combine"> Combine </Button>
<br>
<div class="text-box output" style="display: block;">
    <p>Combined text: <input type="text" id="textBoxResult" style="margin-left: 10px" name="textBoxResult"></p>
    <p>Passing Time: <input type="number" step=".01" id="textBoxTime" width="10%" name="textBoxTime"></p>
</div>
<div>
    <Button name="save" id="save"> Save </Button>
    <Button name="reset" id="reset"> Reset </Button>
    <a href="records.jsp"><Button> List Records </Button></a>
</div>
<br><br>
</body>
</html>
<script>
    document.getElementById("combine").addEventListener("click", Combine, false);
    document.getElementById("save").addEventListener("click", Save, false);
    document.getElementById("reset").addEventListener("click", Reset, false);

    function Combine() {
        <%!
        Process process = new Process();
        Record record = new Record();
        %>
        <%
        String[] liste = {"textBox", "textBoxTwo", "textBoxThree", "textBoxFour", "textBoxFive"};
        String indexS = request.getParameter("inputNumber");
        int index;
        if (indexS != null) {
            index = Integer.parseInt(indexS);
        }
        else {
            index = 2;
        }
        for (int i = 0; i < index; i++) {
            record.metinler.add(request.getParameter(liste[i]));
        }
    %>
        var index = document.getElementById("inputNumber").value;
        var texts = ["textBox", "textBoxTwo", "textBoxThree", "textBoxFour", "textBoxFive"];
        var text;
        for (var i = 0; i < index; i++) {
            text = document.getElementById(texts[i]);
            document.getElementById("textBoxResult").value = text;
            <%record.metinler.add(request.getParameter("textBoxResult"));%>
        }
        document.getElementById("textBoxResult").value = <%=process.Combine(record.GetMetinler())%>
        document.getElementById("textBoxTime").value = <%=process.CalculateTime()%>
    }
    function Save() {
        <%process.SetRecordValues(record);
          process.SaveRecord(record);%>
    }
    function showTextBoxes() {
        var inputVal = document.getElementById("inputNumber").value;
        var textBoxes = document.querySelectorAll(".text-box");

        for (var i = 0; i < textBoxes.length-1; i++) {
            if (i < inputVal) {
                textBoxes[i].style.display = "block";
            } else {
                textBoxes[i].style.display = "none";
            }
        }
    }
    function Reset() {
        <%process.connection.CloseConnection();
        process.connection.OpenConnection();%>
        document.getElementById("textBox").value = "metin1";
        document.getElementById("textBoxTwo").value = "metin2";
        document.getElementById("textBoxThree").value = "metin3";
        document.getElementById("textBoxFour").value = "metin4";
        document.getElementById("textBoxFive").value = "metin5";
        document.getElementById("textBoxResult").value = "";
        document.getElementById("textBoxTime").value = "";
    }
</script>