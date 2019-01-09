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

<h1><spring:message code="complaint.title"><img src="complaint.png" width="auto" height="40"></spring:message></h1>
<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" name="complaints"
	requestURI="${requestURI}" id="row">

	<!-- Action links -->

	<security:authorize access="hasRole('CUSTOMER'||'REFEREE')">

	<!-- Attributes -->

	<display:column property="ticker" titleKey="complaint.ticker" />
	
	<spring:message code="complaint.dateFormat" var="dateFormat"/>
	<display:column property="moment" titleKey="complaint.moment"
		sortable="true" format="${dateFormat}" />

	<display:column property="description" titleKey="complaint.description" />
	<display:column>
			<a href="complaint/customer/show.do?complaintId=${row.id}"> <spring:message
					code="complaint.show" />
			</a>
		</display:column>
	</security:authorize>
	<security:authorize access="hasRole('REFEREE')">
	<display:column><a href="complaint/referee/supervise.do?complaintId=${row.id}"> <spring:message
				code="complaint.supervise" />
		</a></display:column>
	</security:authorize>
</display:table>

<security:authorize access="hasRole('CUSTOMER')">
		<div>
			<a href="complaint/customer/create.do"> <spring:message
					code="complaint.create" />
			</a>
		</div>
	</security:authorize>