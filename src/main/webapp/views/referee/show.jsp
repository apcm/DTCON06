<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">

	<h1>
		<spring:message code="referee.show" />
	</h1>
	
	<p>
		<spring:message code="referee.edit.label.name" />
	</p>
	<jstl:out value="${referee.name}"></jstl:out>
	
	<p>
		<spring:message code="referee.edit.label.middleName" />
	</p>
	<jstl:out value="${referee.middleName}"></jstl:out>

	<p>
		<spring:message code="referee.edit.label.surName" />
	</p>
	<jstl:out value="${referee.surName}"></jstl:out>
	
	<p>
		<spring:message code="referee.edit.label.address" />
	</p>
	<jstl:out value="${referee.address}"></jstl:out>
	
	<p>
		<spring:message code="referee.edit.label.email" />
	</p>
	<jstl:out value="${referee.email}"></jstl:out>
	
	<p>
		<spring:message code="referee.edit.label.phoneNumber" />
	</p>
	<jstl:out value="${referee.phoneNumber}"></jstl:out>

	<p>
		<spring:message code="referee.edit.label.username" />
	</p>
	<jstl:out value="${referee.userAccount.username}"></jstl:out>




	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="referee.back" />" />
	
</security:authorize>