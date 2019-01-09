<%--
 * list.jsp
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

<h1><spring:message code="report.title"></spring:message></h1>
<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" name="reports"
	requestURI="${requestURI}" id="row">

	<!-- Action links -->

	<security:authorize access="hasRole('CUSTOMER'||'REFEREE'||'HANDYWORKER')">

	<!-- Attributes -->
	<display:column property="finalMode" titleKey="report.finalMode" />
	<spring:message code="report.dateFormat" var="dateFormat"/>
	<display:column property="moment" titleKey="report.moment"
		sortable="true" format="${dateFormat}" />

	<display:column property="description" titleKey="report.description" />

	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
	<display:column><a href="report/referee/edit.do?reportId=${row.id}"> <spring:message
				code="report.edit" />
		</a></display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('REFEREE'||'CUSTOMER'||'HANDYWORKER')">
	<display:column><a href="report/referee/showNotes.do?reportId=${row.id}"> <spring:message
				code="report.show.note" />
		</a></display:column>
	</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
	<display:column><a href="report/customer/showNotes.do?reportId=${row.id}"> <spring:message
				code="report.show.note" />
		</a></display:column>
	</security:authorize>

<security:authorize access="hasRole('HANDYWORKER')">
	<display:column><a href="report/handyworker/showNotes.do?reportId=${row.id}"> <spring:message
				code="report.show.note" />
		</a></display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('REFEREE')">
		<div>
			<a href="report/referee/create.do"> <spring:message
					code="report.create" />
			</a>
		</div>
	</security:authorize>