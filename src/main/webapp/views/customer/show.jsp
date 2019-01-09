<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('ADMIN')">

	<h1>
		<spring:message code="administrator.show" />
	</h1>
	
	<p>
		<spring:message code="administrator.edit.label.name" />
	</p>
	<jstl:out value="${administrator.name}"></jstl:out>
	
	<p>
		<spring:message code="administrator.edit.label.middleName" />
	</p>
	<jstl:out value="${administrator.middleName}"></jstl:out>

	<p>
		<spring:message code="administrator.edit.label.surName" />
	</p>
	<jstl:out value="${administrator.surName}"></jstl:out>
	
	<p>
		<spring:message code="administrator.edit.label.address" />
	</p>
	<jstl:out value="${administrator.address}"></jstl:out>
	
	<p>
		<spring:message code="administrator.edit.label.email" />
	</p>
	<jstl:out value="${administrator.email}"></jstl:out>
	
	<p>
		<spring:message code="administrator.edit.label.phoneNumber" />
	</p>
	<jstl:out value="${administrator.phoneNumber}"></jstl:out>

	<p>
		<spring:message code="administrator.edit.label.username" />
	</p>
	<jstl:out value="${administrator.userAccount.username}"></jstl:out>


	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="administrator.back" />" />
	
</security:authorize>