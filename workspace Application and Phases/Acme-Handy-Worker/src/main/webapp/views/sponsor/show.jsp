<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('SPONSOR')">

	<h1>
		<spring:message code="sponsor.show" />
	</h1>
	
	<p>
		<spring:message code="sponsor.edit.label.name" />
	</p>
	<jstl:out value="${sponsor.name}"></jstl:out>
	
	<p>
		<spring:message code="sponsor.edit.label.middleName" />
	</p>
	<jstl:out value="${sponsor.middleName}"></jstl:out>

	<p>
		<spring:message code="sponsor.edit.label.surName" />
	</p>
	<jstl:out value="${sponsor.surName}"></jstl:out>
	
	<p>
		<spring:message code="sponsor.edit.label.address" />
	</p>
	<jstl:out value="${sponsor.address}"></jstl:out>
	
	<p>
		<spring:message code="sponsor.edit.label.email" />
	</p>
	<jstl:out value="${sponsor.email}"></jstl:out>
	
	<p>
		<spring:message code="sponsor.edit.label.phoneNumber" />
	</p>
	<jstl:out value="${sponsor.phoneNumber}"></jstl:out>

	<p>
		<spring:message code="sponsor.edit.label.username" />
	</p>
	<jstl:out value="${sponsor.userAccount.username}"></jstl:out>




	<input type="button" name="back" onclick="javascript: window.location.replace('welcome/index.do')"
		value="<spring:message code="sponsor.back" />" />
	
</security:authorize>