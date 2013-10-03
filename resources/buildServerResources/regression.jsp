<%@ include file="/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <style type="text/css">
             body {
                   font-family: Verdana, arial, sans-serif;
                 	background-color: white;
             }

             h1{
                 text-align: center;
                 color: lightblue;
                 text-shadow: 0.1em 0.1em 0.1em green;
             }

             legend{
                 text-align: center;
             }

             p {
                 margin-bottom: 1em;
                 clear: both;
                font-size: 1em;
                text-align: center;
                color: black;
             }

             form{
                margin: 1.5em;
                text-align: center;
             }


             input[type='text']  {
                margin-left:0.5em;
             	margin-top:0.5em;
               	margin-bottom:0.5em;
             	text-align: center;
               	width: 3.5em;
             }


             #ok{
            	width: 5em;
             }

             table{

                margin-left: auto;
                margin-right: auto;
                width: 23em;
                margin-bottom: 1em;
                margin-top: 1em;
             }

             td, th {
                padding: 1.2em 2.5em;
                font-size: .80em;
             }

             th {
                color: darkgreen;
                font-size: .95em;
             }

    </style>
    <div style="margin-top: 1em">

   <h1>Slow detection!!</h1>
   <p>Welcome "${user}"</p>
   <p>You are in build Type: "${buildType}"</p>
   <p>BuildSize: "${buildSize}"</p>


   <form method="post" action="/changePage.html">
        <input type="hidden" name="urlRedirect" value="${Url}">
        <fieldset>
        <legend>Options</legend>
        Size: <input id="sizeChoose" name="size" type="text" value="${usedSize}"/> <br />
        Numerical threshold: <input id="numericalThresholdChoose" name="numericalThreshold" type="text" value="${numericalThreshold}"/> <br />
        Percentual threshold: <input id="percentualThresholdChoose" name="percentualThreshold" type="text" value="${percentualThreshold}"/> <br />
        <input id="ok" type="submit" name="confirmSize" value="ok"/> <br />
        </fieldset>
   </form>



   <p>Size used: "${usedSize}"</p>
   <p>There are "${sizeRegression}" regression</p>


    <table border="1">
        <tr>
            <th>Test Name</th>
            <th>Motivation</th>
        </tr>
        <c:forEach items="${testsRegression}" var="regression" varStatus="pos">
           <tr>
            <td><a href="/project.html?projectId=${projectId}&testNameId=${regression.id}&tab=testDetails"><span class="mono mono-12px"><c:out value="${regression.nameTest}"/></span></a></td>
            <td><a href="/motivation.html?testNameId=${regression.id}"><span class="mono mono-12px">motivation</span></td>
           </tr>
        </c:forEach>
    </table>
</div>
