<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action="note/handyworker/edit.do" modelAttribute="note">
	
	<form:hidden path="id" />

	<form:hidden path="version" />
	
	<fieldset>
	<legend align="left"><spring:message code="note.edit" /></legend>
		<form:input path="handyworkerComments" titleKey="note.comments"/>
		
		<form:errors cssClass="error" path="handyworkerComments" />		
	
	</fieldset>
	
	<input type="submit" name="save" value="<spring:message code="note.save" />" />
	<input type="button" name="cancel" onclick="javascript: window.location.replace('note/list.do')"
			value="<spring:message code="note.cancel" />" />
	</form:form>
	
</security:authorize>

<security:authorize access="hasRole('REFEREE')">
<form:form action="note/referee/edit.do" modelAttribute="note">
	
	<form:hidden path="id" />

	<form:hidden path="version" />
	
	<fieldset>
	<legend align="left"><spring:message code="note.edit" /></legend>
		<form:input path="refereeComments" titleKey="note.comments"/>
		
		<form:errors cssClass="error" path="refereeComments" />		
	
	</fieldset>
	<br/>
	<input type="submit" name="save" value="<spring:message code="note.save" />" />
	<input type="button" name="cancel" onclick="javascript: window.location.replace('note/list.do')"
			value="<spring:message code="note.cancel" />" />
	</form:form>
	
</security:authorize>

<security:authorize access="hasRole('CUSTOMER')">
<form:form action="note/customer/edit.do" modelAttribute="note">
	
	<form:hidden path="id" />

	<form:hidden path="version" />
	
	<fieldset>
	<legend align="left"><spring:message code="note.edit" /></legend>
		<form:input path="customerComments" titleKey="note.comments"/>
		
		<form:errors cssClass="error" path="customerComments" />		
	<br/>
	<br/>
	</fieldset>
	
	<input type="submit" name="save" value="<spring:message code="note.save" />" />
	<input type="button" name="cancel" onclick="javascript: window.location.replace('note/list.do')"
			value="<spring:message code="note.cancel" />" />
	</form:form>
	
	<br/><br/>
	
</security:authorize>