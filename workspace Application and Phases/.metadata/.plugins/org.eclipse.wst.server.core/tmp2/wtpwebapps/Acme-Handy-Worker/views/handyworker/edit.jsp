<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">
<form:form action="handyworker/handyworker/edit.do" modelAttribute="handyWorker">
	
	<form:hidden path="id" />
	<form:hidden path="version" /> 
	<form:hidden path="userAccount" />	
	<form:hidden path="boxes" />
	<form:hidden path="socialProfiles" />
	<form:hidden path="ban" />
	<form:hidden path="score" />
	
		
	<form:hidden path="applications" />
	<form:hidden path="plannedPhases" />
	<form:hidden path="finder" />
	<form:hidden path="curriculum" />
	
	
	<fieldset>
	<legend align="left"><spring:message code="handyworker.edit.contact" /></legend>
	
		<form:label path="name">
			<spring:message code="handyworker.edit.label.name" />:
		</form:label>
		<form:input path="name"/>
		
		<form:errors cssClass="error" path="name" />		
		
		<br/>
		<br/>
	
		<form:label path="middleName">
			<spring:message code="handyworker.edit.label.middleName" />:
		</form:label>
		<form:input path="middleName"/>
		<form:errors cssClass="error" path="middleName" />		
		
		<br/>
		<br/>
		
		<form:label path="surname">
			<spring:message code="handyworker.edit.label.surname" />:
		</form:label>
		<form:input path="surname"/>
		<form:errors cssClass="error" path="surname" />		
		
		<br/>
		<br/>
		
		<form:label path="address">
			<spring:message code="handyworker.edit.label.address" />:
		</form:label>
		<form:input path="address"/>
		<form:errors cssClass="error" path="address" />		
		
		<br/>
		<br/>
	
		<form:label path="email">
			<spring:message code="handyworker.edit.label.email" />:
		</form:label>
		<form:input path="email"/>
		<form:errors cssClass="error" path="email" />	
			
		<br/>
		<br/>
		
		<form:label path="phoneNumber">
			<spring:message code="handyworker.edit.label.phoneNumber" />:
		</form:label>
		<form:input path="phoneNumber"/>
		<form:errors cssClass="error" path="phoneNumber" />
		
		<br/>
		<br/>
		
		
		<form:label path="photoURL">
			<spring:message code="handyworker.edit.label.photoURL" />:
		</form:label>
		<form:input path="photoURL"/>
		<form:errors cssClass="error" path="photoURL" />
		
		<br/>
		<br/>
	</fieldset>
	
	<input type="submit" name="save" value="<spring:message code="handyworker.edit.save.save" />" />&nbsp;
	<input type="button" name="cancel" onclick="javascript: window.location.replace('welcome/index.do')"
			value="<spring:message code="handyworker.edit.cancel" />" />
	</form:form>
	
	<br/><br/>
	
</security:authorize>