<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">

	<h4>
		<spring:message code="handyworker.edit.label.name" />:
	</h4>
	<jstl:out value="${handyworker.name}"></jstl:out>
	
	<h4>
		<spring:message code="handyworker.edit.label.middleName" />:
	</h4>
	<jstl:out value="${handyworker.middleName}"></jstl:out>

	<h4>
		<spring:message code="handyworker.edit.label.surname" />:
	</h4>
	<jstl:out value="${handyworker.surname}"></jstl:out>
	
	<h4>
		<spring:message code="handyworker.edit.label.address" />:
	</h4>
	<jstl:out value="${handyworker.address}"></jstl:out>
	
	<h4>
		<spring:message code="handyworker.edit.label.email" />:
	</h4>
	<jstl:out value="${handyworker.email}"></jstl:out>
	
	<h4>
		<spring:message code="handyworker.edit.label.phoneNumber" />:
	</h4>
	<jstl:out value="${handyworker.phoneNumber}"></jstl:out>

	<h4>
		<spring:message code="handyworker.edit.label.username" />:
	</h4>
	<jstl:out value="${handyworker.userAccount.username}"></jstl:out>

	<h4>
		<spring:message code="handyworker.edit.label.make" />:
	</h4>
	<jstl:out value="${handyworker.make}"></jstl:out>



	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="handyworker.back" />" />
	
</security:authorize>