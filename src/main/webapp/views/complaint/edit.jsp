<%--
 * create.jsp
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


<form:form action="complaint/customer/edit.do"
	modelAttribute="complaint">

	<form:hidden path="id" />

	<form:hidden path="version" />

	<form:hidden path="fixUpTask" />

	<form:textarea path="description" titleKey="complaint.description"/>
	<form:errors cssClass="error" path="description" />
	<br />
		<form:textarea path="attachments" placeholder="http://www.ejemplo.com" titleKey="complaint.attchments"/>
		<form:errors cssClass="error" path="attachments" />
	<br />
	<br />

	<spring:message code="complaint.save" var="saveComplaint" />
	<spring:message code="complaint.cancel" var="cancelComplaint" />

	<input type="submit" name="save" value="${saveComplaint}" />&nbsp; 
	
	<input type="button" name="cancel" value="${cancelComplaint}"
		onclick="javascript: relativeRedir('complaint/customer/list.do');" />
	<br />

</form:form>