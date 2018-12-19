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

<security:authorize access="hasRole('CUSTOMER'||'REFEREE')">
	<div>
		<a href="complaint/list.do"> <spring:message
				code="complaint.list" />
		</a>
	</div>
</security:authorize>

<!------------------->
		<h1><spring:message code="complaint.show"/></h1>
 		<p><span><spring:message code="complaint.ticker"/>:
		<jstl:out value="${complaint.ticker}"></jstl:out></span></p>

		<p><span>
		<spring:message code="report.dateFormat" var="dateFormat"/>
		<fmt:formatDate value="${report.moment}" pattern="${dateFormat}" />
		</span></p>
		<p><span>
		<spring:message code="complaint.dateFormat" var="dateFormat"/>
		<fmt:formatDate value="${complaint.moment}" pattern="${dateFormat}" />
		</span></p>
		
		<p><span>
		<spring:message code="complaint.description" />:
		<jstl:out value="${complaint.description}"></jstl:out></span></p>
		
		<p><span>
		
		<spring:message code="complaint.customer" />:
		<jstl:out value="${complaint.customer}"></jstl:out></span></p>
		
		<p><span>
		<spring:message code="complaint.attachmentURL" />:
		<jstl:forEach var="attchmentURL" items="complaint.attachmentURL" >
		<jstl:out value="${attchemntURL}"></jstl:out></jstl:forEach></span></p>
	
	<security:authorize access="hasRole('REFEREE')">
	<a href="report/referee/create.do?complaintId=${complaint.id}"><spring:message code="complaint.createReport"/></a>
	</security:authorize>	
	