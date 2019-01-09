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

<h1><spring:message code="note.title"></spring:message></h1>
<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" name="notes"
	requestURI="${requestURI}" id="row">

	<!-- Action links -->

	<security:authorize access="hasRole('CUSTOMER'||'REFEREE'||'HANDYWORKER')">

	<!-- Attributes -->
	<spring:message code="note.dateFormat" var="dateFormat"/>
	<display:column property="moment" titleKey="note.moment"
		sortable="true" format="${dateFormat}" />
	<display:column>
			<a href="complaint/customer/show.do?noteId=${row.id}"> <spring:message
					code="note.show" />
			</a>
		</display:column>
	</security:authorize>

</display:table>

<security:authorize access="hasRole('CUSTOMER')">
		<div>
			<a href="note/customer/create.do"> <spring:message
					code="note.create" />
			</a>
		</div>
	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
		<div>
			<a href="note/referee/create.do"> <spring:message
					code="note.create" />
			</a>
		</div>
	</security:authorize>
	<security:authorize access="hasRole('HANDYWORKER')">
		<div>
			<a href="note/handyworker/create.do"> <spring:message
					code="note.create" />
			</a>
		</div>
	</security:authorize>