<%--
 * show.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Action links -->

<security:authorize access="hasRole('CUSTOMER'||'REFEREE'||'HANDYWORKER')">
	<div>
		<a href="report/list.do"> <spring:message
				code="report.list" />
		</a>
	</div>
</security:authorize>

<!------------------->
		<h1><spring:message code="report.show.one"/></h1>
			<p><span><spring:message code="report.moment"/>:
		<jstl:out value="${report.moment}"></jstl:out></span></p>
 		<p><span><spring:message code="report.description"/>:
		<jstl:out value="${report.description}"></jstl:out></span></p>

	<p><span>
		<spring:message code="report.dateFormat" var="dateFormat"/>
		<fmt:formatDate value="${report.moment}" pattern="${dateFormat}" />
		</span></p>
		
		<p><span>
		<spring:message code="report.description" />:
		<jstl:out value="${report.description}"></jstl:out></span></p>
		
		<p><span>
		
		<spring:message code="report.finalMode" />:
		<jstl:out value="${report.finalMode}"></jstl:out></span></p>
		
		<p><span>
		<spring:message code="report.attachment" />:
		<jstl:forEach var="attchment" items="report.attachment" >
		<jstl:out value="${attchment}"></jstl:out></jstl:forEach></span></p>
		
		<security:authorize access="hasRole('REFEREE')">
		<a href="report/referee/edit.do?reportId=${report.id}">
		<spring:message code="report.edit"/>
		</a>
		</security:authorize>