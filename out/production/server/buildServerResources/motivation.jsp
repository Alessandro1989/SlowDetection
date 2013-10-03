<%@ include file="/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<bs:page>
   <jsp:attribute name="body_include">
   <style type="text/css">
         body {
                  font-family: Verdana, arial, sans-serif;
                	background-color: white;
                	width: 60em;
                	text-align: center;
              }

         p {
             margin-bottom: 1em;
             clear: both;
             font-size: 1em;
             text-align: center;
             color: blue;
             text-shadow: 0.05em 0.05em 0.05em green;
          }

          h1{
             text-align: center;
             font-size: 1.5em;
             color: lightblue;
             text-shadow: 0.1em 0.1em 0.1em green;
          }


          caption {
             color: lightblue;
             text-shadow: 0.1em 0.1em 0.1em green;
             font-size: 1.1em;
             margin-bottom: 0.4em;
           }

           div{
             margin-left: 8em;
           }

           table{
            float: left;
            text-align: center;
            margin-bottom: 0.5em;
            margin-top: 0.5em;
            margin-left: 10em;
           }

           table#end{
             margin-top: -0.90em;
           }

           td, th  {
             padding: 1em;
             font-size: .80em;
             color: black;
           }

           th {
             color: darkgreen;
             font-size: .95em;
           }

    </style>
    <h1>${nameTest}</h1>
    <p>Test id: "${idTest}"</p>
    <p> Samples: </p>
    <div>
        <table border="1">
            <caption>Starting sample</caption>
            <tr>
              <th>Run Id</th>
              <th>Time </th>
            </tr>
            <c:forEach begin="0" end="${fn:length(timesBase) - 1}" varStatus="loop">
            <tr>
                <td>${runIdBase[loop.index]}</td>
                <td>${timesBase[loop.index]}</td>
            </tr>
           </c:forEach>
        </table>

        <br />

        <table id="end" border="1">
             <caption>Ending sample</caption>
             <tr>
                <th>Run Id</th>
                <th>Time </th>
             </tr>
             <c:forEach begin="0" end="${fn:length(timesEnd) - 1}" varStatus="loop">
                <tr>
                    <td>${runIdEnd[loop.index]}</td>
                    <td>${timesEnd[loop.index]}</td>
             </tr>
             </c:forEach>
        </table>
    </div>

    <p>So we have:</p>

    <c:forEach begin="0" end="${fn:length(timesBase) - 1}" varStatus="loop">
        <span>${timesBase[loop.index]};  </span>
    </c:forEach>

    <p>Against</p>

    <c:forEach begin="0" end="9" varStatus="loop">
        <span>${min};  </span>
    </c:forEach>

    <p>Numerical limit is: "${numericalThreShold}" </p>
    <p>Percentual limit is: "${percentualThreShold}"</p>

  </jsp:attribute>
</bs:page>
