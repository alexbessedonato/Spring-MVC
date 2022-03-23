<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
</head>
<body>
  <h2>User Profile</h2>
  Hello, <strong><sec:authentication property="principal.attributes['given_name']" /></strong> 
  <p>
  Here are your details from your <a href="https://developer.okta.com/docs/api/resources/oidc.html#get-user-information">Access Token</a>:

  <table>
  	<thead>
      <tr>
        <th>Claim</th>
        <th>Value</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${details}" var="a">
        <tr>
          <td>${a.key}</td>
          <td>${a.value}</td>
        </tr>
	  </c:forEach>
    </tbody>
  </table>
</body>
</html>