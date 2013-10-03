<%@ include file="/include.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<bs:page>
  <jsp:attribute name="body_include">

    <p>Try page "${output}", size: "${size}"</p>

    <form method="post" action="/viewType.html?buildTypeId=bt2&tab=samplePlugin">
        <input type="submit" name="boh" value="try"/>
    </form>
  </jsp:attribute>
</bs:page>
