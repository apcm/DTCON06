<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('REFEREE')">
<form:form action="report/referee/edit.do" modelAttribute="report">
	
	<form:hidden path="id" />

	<form:hidden path="version" />
	
	<fieldset>
	<legend align="left"><spring:message code="report.edit" /></legend>

		<form:input path="description" titleKey="report.description"/>
		
		<form:errors cssClass="error" path="description" />		
		
		<br/>
		<br/>
		
		<form:textarea path="attchment" titleKey="report.attchment"/>
		
		<form:errors cssClass="error" path="attchment" />		
		
		<br/>
		<br/>
		<form:select path="finalMode" titleKey="report.finalMode">
		<form:option value="Yes"><spring:message code="report.yes"></spring:message></form:option>
		<form:option value="No"><spring:message code="report.no"></spring:message></form:option>
		</form:select>
		
		<form:errors cssClass="error" path="finalMode" />		
		
		<br/>
		<br/>
	
	</fieldset>
	
	<input type="submit" name="save" value="<spring:message code="report.save" />" />
	<input type="button" name="cancel" onclick="javascript: window.location.replace('report/list.do')"
			value="<spring:message code="report.cancel" />" />
	</form:form>

<form:form action="report/referee/edit.do?id" modelAttribute="report">
<form:hidden path="id"/>
<input type="submit" name="delete" value="<spring:message code="report.delete" />" />
</form:form>
	
	<br/><br/>
	
</security:authorize>