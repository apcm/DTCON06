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

	<h1>
		<spring:message code="handyworker.show" />
	</h1>
	
	<p>
		<spring:message code="handyworker.edit.label.name" />
	</p>
	<jstl:out value="${handyworker.name}"></jstl:out>
	
	<p>
		<spring:message code="handyworker.edit.label.middleName" />
	</p>
	<jstl:out value="${handyworker.middleName}"></jstl:out>

	<p>
		<spring:message code="handyworker.edit.label.surName" />
	</p>
	<jstl:out value="${handyworker.surName}"></jstl:out>
	
	<p>
		<spring:message code="handyworker.edit.label.address" />
	</p>
	<jstl:out value="${handyworker.address}"></jstl:out>
	
	<p>
		<spring:message code="handyworker.edit.label.email" />
	</p>
	<jstl:out value="${handyworker.email}"></jstl:out>
	
	<p>
		<spring:message code="handyworker.edit.label.phoneNumber" />
	</p>
	<jstl:out value="${handyworker.phoneNumber}"></jstl:out>

	<p>
		<spring:message code="handyworker.edit.label.username" />
	</p>
	<jstl:out value="${handyworker.userAccount.username}"></jstl:out>

	<p>
		<spring:message code="handyworker.edit.label.make" />
	</p>
	<jstl:out value="${handyworker.make}"></jstl:out>



	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="handyworker.back" />" />
	
</security:authorize>