<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">
<form:form action="referee/referee/edit.do" modelAttribute="referee">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount" />	
	<form:hidden path="boxes" />
	<form:hidden path="socialProfiles" />
	<form:hidden path="ban" />
	
	<form:hidden path="reports" />
	<form:hidden path="complaints" />

 
 	
	<fieldset>
	<legend align="left"><spring:message code="referee.edit.contact" /></legend>
	
		<form:label path="name">
			<spring:message code="referee.edit.label.name" />:
		</form:label>
		<form:input path="name"/>
		
		<form:errors cssClass="error" path="name" />		
		
		<br/>
		<br/>
	
		<form:label path="middleName">
			<spring:message code="referee.edit.label.middleName" />:
		</form:label>
		<form:input path="middleName"/>
		<form:errors cssClass="error" path="middleName" />		
		
		<br/>
		<br/>
		
		<form:label path="surname">
			<spring:message code="referee.edit.label.surname" />:
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname" />		
		
		<br/>
		<br/>
		
		<form:label path="address">
			<spring:message code="referee.edit.label.address" />:
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" />		
		
		<br/>
		<br/>
	
		<form:label path="email">
			<spring:message code="referee.edit.label.email" />:
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email" />	
			
		<br/>
		<br/>
		
		<form:label path="phoneNumber">
			<spring:message code="referee.edit.label.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber"/>
		<form:errors cssClass="error" path="phoneNumber" />
		
		<br/>
		<br/>
		
		<form:label path="photoURL">
			<spring:message code="referee.edit.label.photoURL" />:
		</form:label>
		<form:input path="photoURL"/>
		<form:errors cssClass="error" path="photoURL" />
		
		<br/>
		<br/>
	</fieldset>
	
	<input type="submit" name="save" value="<spring:message code="referee.edit.save.save" />" />&nbsp;
	<input type="button" name="cancel" onclick="javascript: window.location.replace('welcome/index.do')"
			value="<spring:message code="referee.edit.cancel" />" />
	</form:form>
	
	
	
</security:authorize>