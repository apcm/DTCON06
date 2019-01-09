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

<security:authorize access="hasRole('CUSTOMER'||'REFEREE'|'HANDYWORKER')">
	<div>
		<a href="note/list.do"> <spring:message
				code="note.list" />
		</a>
	</div>
</security:authorize>

<!------------------->
		<h1><spring:message code="note.show"/></h1>
		<p><span><spring:message code="note.moment"/>:
		<spring:message code="note.dateFormat" var="dateFormat"/>
		<fmt:formatDate value="${note.moment}" pattern="${dateFormat}" />
		</span></p>
		
		<p><span><spring:message code="note.customer"/>:
		<jstl:out value="${note.customer}"></jstl:out></span></p>
		
		<p><span><spring:message code="note.comments"/>:
		<jstl:out value="${note.customerComment}"></jstl:out></span></p>
		
		<p><span><spring:message code="note.handyworker"/>:
		<jstl:out value="${note.handyworker}"></jstl:out></span></p>
		
		<p><span><spring:message code="note.comments"/>:
		<jstl:out value="${note.handyworkerComment}"></jstl:out></span></p>
		
		<p><span><spring:message code="note.referee"/>:
		<jstl:out value="${note.referee}"></jstl:out></span></p>
		
		<p><span><spring:message code="note.comments"/>:
		<jstl:out value="${note.refereeComment}"></jstl:out></span></p>
		
			<security:authorize access="hasRole('REFEREE')">
	<a href="note/referee/edit.do?noteId=${note.id}"> <spring:message
				code="note.edit" />
		</a>
	</security:authorize>
	<security:authorize access="hasRole('CUSTOMER')">
	<a href="note/customer/edit.do?noteId=${note.id}"> <spring:message
				code="note.edit" />
		</a>
	</security:authorize>
	<security:authorize access="hasRole('HANDYWORKER')">
	<a href="note/handyworker/edit.do?noteId=${note.id}"> <spring:message
				code="note.edit" />
		</a>
	</security:authorize>